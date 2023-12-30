var xhr = new XMLHttpRequest();
xhr.open("GET", "http://localhost:8081/api/jo", true);

xhr.responseType = "json";
console.log("Bonjour !!");
xhr.send();


xhr.addEventListener('readystatechange', function() {
    if ( xhr.readyState == 4 && xhr.status === 200) {

        console.log(xhr.response);
        TableauArticle("table-stock");
        //createCubicZone();
    }
    else  {
        console.log("Erreur");
    }
});


function TableauArticle(containerId) {
    var tableau = document.createElement("table");
    tableau.className = "Tableau";

    var Titlehead = document.createElement("thead");
    tableau.append(Titlehead);

    var Hrow = Titlehead.insertRow();

    var headers = ["id", "Article", "Prix", "Quantité"];

    headers.forEach(function (headerText) {
        var Hcell = document.createElement("th");
        Hcell.innerHTML = headerText;
        Hrow.appendChild(Hcell);
    });

    var tbody = document.createElement("tbody");
    tableau.appendChild(tbody);

    for(var i = 0; i < xhr.response.length; i++) {
        var row = tbody.insertRow();

        var data = xhr.response[i];



        for (var j = 0; j < Object.keys(data).length-1; j++) {
            var cell = row.insertCell(j);
            var values = Object.values(data);
            console.log(values);
            cell.innerHTML = values[j];
        }

        row.onclick = function (event) {
            var rowIndex = event.target.parentNode.rowIndex;

            // Désélectionner toutes les lignes sauf celle cliquée
            var allRows = document.querySelectorAll(".Tableau tbody tr");
            allRows.forEach(function (otherRow) {
                if (otherRow.rowIndex !== rowIndex) {
                    otherRow.classList.remove("selected");
                }
            });

            var cells = Array.from(event.target.parentNode.cells);
            var rowData = cells.map(cell => cell.innerHTML);

            // Inverser la classe "selected" pour la ligne cliquée
            this.classList.toggle("selected");
            console.log(this);
            console.log(rowData);

            // Mettre à jour l'image du produit
            var productImage = document.getElementById("product-image");
            productImage.src = "../resources/" + rowData[1] + ".png";
            console.log(productImage.src);

            var productid = document.getElementById("idArticle");
            productid.value = rowData[0];
            var productname = document.getElementById("article");
            productname.value = rowData[1];
            var productprice = document.getElementById("prix");
            productprice.value = rowData[2];
            var productquantity = document.getElementById("quantite");
            productquantity.value = rowData[3];


        };




    }

    var container = document.getElementById(containerId);

    container.appendChild(tableau);
}


