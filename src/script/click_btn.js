var button = document.getElementById("btn");

button.onclick = (function () {


    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8081/api/jo", true);


    xhr.responseType = "text";

    var id = document.getElementById("idArticle").value;
    var prix = document.getElementById("prix").value;
    var quantite = document.getElementById("quantite").value;


    var data = [
    {
                "id": id,
                "prix": prix,
                "quantite": quantite
            }
    ];

    var requete = JSON.stringify(data);
    console.log("send data");
    xhr.send(requete);


    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status === 200) {

            console.log(xhr.response);
            console.log("Article mis à jour avec succès");
            var tableRows = document.querySelectorAll(".Tableau tbody tr");

            // Assuming your table structure matches the data structure
            tableRows.forEach(function (row, rowIndex) {
                var cells = row.cells;
                cells[0].innerHTML = id;
                cells[2].innerHTML = prix;
                cells[3].innerHTML = quantite;
            });

        } else {
            console.log("Erreur lors de la mise à jour de l'article");
        }
    }

});