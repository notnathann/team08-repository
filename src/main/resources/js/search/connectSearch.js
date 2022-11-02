/**
 * Implements generalised search-field functionality.
 */

/**
 * Attach an event handler for search input.
 * 
 * The event handler execution is delayed until user stops typing for 200ms.
 * The event will request matching data from the database using the search-field value, then display the results to the user.
 */
$(document).ready(function () {
    // The input event is called for every typed character
    $('.search-field').on('input', function () {
        // Clear the current timer
        clearTimeout(this.delay);
        // Create a new timer
        this.delay = setTimeout(function () {
            updateSearchContents(this, this.value);
        }.bind(this), 200);
    });

    $('.search-field').on('click blur', toggleSearchContents);
    $('.search').on('search-invalid', onInvalidChange);
    $('.search').on('search-change', onChange);
});

/**
 * Event handler for the search-field click and blur events.
 * 
 * Shows the search contents panel when a click events occurs.
 * Hides the search contents panel when a blur event (lost focus) occurs.
 * 
 * Checks if the blur event was the result of a selection being made on the search contents panel.
 * Then updates the search-field value and triggers a custom "search-change" event for the selected result.
 * 
 * If the blur event was not the result of a selection, checks if the user's search text is a complete match to a database value.
 * Then triggers a custom "search-change" event for the matching database value.
 * 
 * If the user's search text was not a complete match, triggers a custom "search-invalid" event to indicate the search text is invalid.
 * 
 * @param {Event} event 
 */
function toggleSearchContents(event) {
    let search = $(this).closest(".search")
    let searchContent = search.find(".search-content");
    let searchContentItems = searchContent.find("p");

    if (event.type === "click") {
        searchContent.show();
    } else {
        searchContent.hide();

        // Check if blur occurred on search-content item
        if (searchContentItems.is(event.relatedTarget)) {
            let relatedTarget = $(event.relatedTarget);

            $(this).val(relatedTarget.text());
            search.attr("data-value", relatedTarget.attr("data-value"));
            search.trigger("search-change", [relatedTarget.attr("data-value")]);
            searchContent.empty();
        } // Check if a non-empty current search value has not changed
        else if ($(this).val() && $(this).val() === search.attr("data-value")) {
            return;
        } // Check if the current search value is a complete match to a database value
        else if (searchContentItems.length && $(searchContentItems[0]).attr("data-value") === $(this).val()) {
            search.attr("data-value", $(searchContentItems[0]).attr("data-value"));
            search.trigger("search-change", $(this).val());
            searchContent.empty();
        } else // If the current search value is does not match to a database value
        {
            search.trigger("search-invalid", $(this).val());
        }
    }
}

/**
 * Event handler for the search-field input event.
 * 
 * Makes a request to the database to get all datapoints that have a partial match against the `searchValue`.
 * Assumes the requested data will be returned as an object with the following format:
 *    {
 *        'values': [...],
 *    }
 * 
 * The returned values will be inserted into the search-content panel and displayed to the user.
 * 
 * @param {String} searchValue The search-field input used to match values from the database.
 */
function updateSearchContents(searchField, searchValue) {
    let search = $(searchField).closest(".search")
    let searchContent = search.find(".search-content");

    if (!searchValue) {
        $(".search-content").empty();
        return;
    }

    $.ajax({
        method: 'GET',
        url: search.attr("data-request") + searchValue,
        dataType: 'json'
    }).done(function (data) {
        searchContent.empty();

        if (!data.values) {
            return;
        }

        for (var i = 0; i < data.values.length; i++) {
            let searchResultTemplate = $("#search-result-template");
            let searchResultTemplateFragment = searchResultTemplate[0].content;
            let searchResultItem = searchResultTemplateFragment.cloneNode(true).children[0];
            $(searchResultItem).text(data.values[i]);
            $(searchResultItem).attr("data-value", data.values[i]);
            searchContent.append(searchResultItem);
        }
    });
}

function onInvalidChange(event, value) {
    if (value) {
        $(this).attr("data-value", "");
        $(this).addClass("invalid");
    } else {
        $(this).attr("data-value", "");
        $(this).removeClass("invalid");
    }
}

function onChange(event, value) {
    $(this).removeClass("mandatory");
    $(this).removeClass("invalid");
}