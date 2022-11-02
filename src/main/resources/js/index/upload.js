
$(document).ready(function () {
    const form = document.querySelector('#upload-form');
    const file = document.querySelector('#upload-file');
    const button = document.querySelector('#upload-btn');

    button.onclick = function () {
        file.click();
    };

    file.onchange = function () {
        if (!file.value.length) {
            return;
        }

        // Read the file
        var files = file.files
        for (i = 0 ; i < files.length ; i++) {
            let reader = new FileReader();
            reader.onload = postJSON;
            reader.readAsText(file.files[i]);
        }
    };
});

function postJSON(event) {
    let str = event.target.result;

    $.ajax({
        type: "POST",
        url: "/upload",
        data: str,
        success: function () { },
        dataType: "json",
        contentType: "application/json"
    });
}