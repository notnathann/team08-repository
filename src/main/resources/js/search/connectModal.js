$(document).ready(function () {
    const button = $('.search-button .btn');
    const modal_overlay = $('.modal-overlay');
    const modal = $('.results-modal');

    button.click(function () {
        modal_overlay.css('display', 'block');
        modal_overlay.addClass("modal-overlay-opacity-anim");

        modal.css('display', 'block');
        modal.addClass("modal-opacity-anim");
    });

    modal_overlay.click(function () {
        modal_overlay.css('display', 'none');
        modal_overlay.removeClass("modal-opacity-anim");

        modal.css('display', 'none');
        modal.removeClass("modal-opacity-anim");
    });
});