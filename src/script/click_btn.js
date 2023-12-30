var button = document.getElementById("btn");

button.addEventListener("click", function () {


    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8081/api/jo", true);


    xhr.responseType = "text";

    var id = document.getElementById("idArticle").value;
    var prix = document.getElementById("prix").value;
    var quantite = document.getElementById("quantite").value;


    var data = {
        "id": id,
        "prix": prix,
        "quantite": quantite
    };


    var requete = JSON.stringify(data);
    xhr.send(requete);


    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status === 200) {

            console.log(xhr.response);
            console.log("Article mis à jour avec succès");
            var tableRows = document.getElementsByClassName("Tableau");

            for (var i = 0; i < tableRows.length; i++) {
                var table = tableRows[i];
                var row = table.getElementsByTagName('tr');
                for (var j = 0; j < row.length; j++) {
                    var col = row[j].getElementsByTagName('td');
                    if (col.length > 0) {
                        var cellvalue = col[0].innerHTML || col[0].textContent;
                        if(cellvalue === id)
                        {
                            console.log(row[j]);
                            col[0].innerHTML = id
                            col[2].innerHTML = prix;
                            col[3].innerHTML = quantite;
                            break;
                        }


                    }
                }

            }

        }
    }

});