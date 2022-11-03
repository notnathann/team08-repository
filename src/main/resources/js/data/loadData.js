$(document).ready(function () {
    let data = JSON.parse(localStorage.getItem("data"));

    var tbody = document.getElementById('tbody');

    for (const [key, value] of Object.entries(data)) {
        var tr = "<tr>";

        tr += "<th>" + key + "</th>" + "<td>" + value.toString() + "</td></tr>";

        tbody.innerHTML += tr;
    }
});