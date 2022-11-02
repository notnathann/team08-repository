$(document).ready(function () {
    $(".checkbox").on("click", toggleCheckbox);
    $(".checkbox").on("checkbox-change", updateSearchMandatoryClass);
});

function toggleCheckbox() {
    $(this).toggleClass("checked");
    $(this).trigger("checkbox-change", $(this).hasClass("checked"));
}

function updateSearchMandatoryClass(event, checked) {
    const search = $(this).parent(".options-grid-item").find(".search");

    // If checkbox is checked and there is no dropdown selection
    if (checked && !search.attr("data-value")) {
        search.addClass("mandatory");
    } else {
        search.removeClass("mandatory");
    }
}
