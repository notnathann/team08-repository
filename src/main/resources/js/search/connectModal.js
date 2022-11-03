$(document).ready(function () {
    const button = $('.search-button .btn');
    const modal_overlay = $('.modal-overlay');

    button.click(showResults);
    modal_overlay.click(hideResults);
});

function getRequestURL() {
    const isOperatorNameChecked = $('#operator-name .checkbox').hasClass("checked");
    const isLoadNumberChecked = $('#load-number .checkbox');
    const isEquipmentChecked = $('#equipment .checkbox');
    const isRunRecipeChecked = $('#run-recipe .checkbox');

    let url = "/searchData";
    url += "/" + ((isOperatorNameChecked ? $("#operator-name .search").attr("data-value") : "null") || "null");
    url += "/" + ((isLoadNumberChecked ? $("#load-number .search").attr("data-value") : "null") || "null");
    url += "/" + ((isEquipmentChecked ? $("#equipment .search").attr("data-value") : "null") || "null");
    url += "/" + ((isRunRecipeChecked ? $("#run-recipe .search").attr("data-value") : "null") || "null");

    return url;
}

function showResults() {
    const modal_overlay = $('.modal-overlay');
    const modal = $('.results-modal');
    const modal_contents = $('.results-modal-contents');

    const checkbox_operatorName = $('#operator-name .checkbox');
    const checkbox_loadNumber = $('#load-number .checkbox');
    const checkbox_equipment = $('#equipment .checkbox');
    const checkbox_runRecipe = $('#run-recipe .checkbox');

    const search_operatorName = $('#operator-name .search');
    const search_loadNumber = $('#load-number .search');
    const search_equipment = $('#equipment .search');
    const search_runRecipe = $('#run-recipe .search');

    const operatorNameChecked = checkbox_operatorName.hasClass("checked");
    const loadNumberChecked = checkbox_loadNumber.hasClass("checked");
    const equipmentChecked = checkbox_equipment.hasClass("checked");
    const runRecipeChecked = checkbox_runRecipe.hasClass("checked");

    const operatorNameValue = search_operatorName.attr("data-value");
    const loadNumberValue = search_loadNumber.attr("data-value");
    const equipmentValue = search_equipment.attr("data-value");
    const runRecipeValue = search_runRecipe.attr("data-value");

    // If something checked, must have value
    if (operatorNameChecked && !operatorNameValue) {
        return;
    }

    if (loadNumberChecked && !loadNumberValue) {
        return;
    }

    if (equipmentChecked && !equipmentValue) {
        return;
    }

    if (runRecipeChecked && !runRecipeValue) {
        return;
    }

    // If nothing checked, return
    if (!operatorNameChecked && !loadNumberChecked && !equipmentChecked && !runRecipeChecked) {
        return;
    }

    $.ajax({
        method: 'GET',
        url: getRequestURL(),
        dataType: 'json'
    }).done(function (data) {
        console.log(data);

        modal_contents.empty();

        if (!data.values) {
            return;
        }

        for (var i = 0; i < data.values.length; i++) {
            let searchResultTemplate = $("#modal-link-template");
            let searchResultTemplateFragment = searchResultTemplate[0].content;
            let searchResultDiv = searchResultTemplateFragment.cloneNode(true).children[0];
            let searchResultAnchor = searchResultDiv.children[0];
            $(searchResultAnchor).text(data.values[i]["fileName"]);
            $(searchResultAnchor).attr("data-value", JSON.stringify(data.values[i]));
            modal_contents.append(searchResultDiv);
            localStorage.setItem("data", JSON.stringify(data.values[i]))

            $(searchResultAnchor).click(updateLocalStorage);
        }
    });

    modal_overlay.css('display', 'block');
    modal_overlay.addClass("modal-overlay-opacity-anim");

    modal.css('display', 'block');
    modal.addClass("modal-opacity-anim");
}

function hideResults() {
    const modal_overlay = $('.modal-overlay');
    const modal = $('.results-modal');

    modal_overlay.css('display', 'none');
    modal_overlay.removeClass("modal-opacity-anim");

    modal.css('display', 'none');
    modal.removeClass("modal-opacity-anim");
}


function updateLocalStorage() {
    data = $(this).attr("data-value");
    localStorage.setItem("data", data);
}