$(document).ready(function () {
    const button = $('.search-button .btn');
    const modal_overlay = $('.modal-overlay');

    button.click(showResults);
    modal_overlay.click(hideResults);
});

function showResults() {
    const modal_overlay = $('.modal-overlay');
    const modal = $('.results-modal');

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