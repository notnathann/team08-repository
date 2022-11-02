$(document).ready(function () {
    const button = $('.search-button .btn');
    const modal_overlay = $('.modal-overlay');

    button.click(showResults);
    modal_overlay.click(hideResults);
});

function showResults() {
    const modal_overlay = $('.modal-overlay');
    const modal = $('.results-modal');

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