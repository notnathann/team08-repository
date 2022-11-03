$(document).ready(function () {
    let data = localStorage.getItem("data");

    const para = $('#data');
    para.text(data);
});