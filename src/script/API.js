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


        // Create rows with sample data
        for (var j = 0; j < Object.keys(data).length-1; j++) {
            var cell = row.insertCell(j);
            var values = Object.values(data);
            console.log(values);
            cell.innerHTML = values[j];
        }



    }




    var container = document.getElementById(containerId);

    container.appendChild(tableau);
}
function createCubicZone() {
    // Create the main container
    var cubicZone = document.createElement("div");
    cubicZone.id = "cubic-zone";
    cubicZone.style.width = "30%";
    cubicZone.style.backgroundColor = "transparent";
    cubicZone.style.padding = "20px";
    cubicZone.style.marginTop = "10px";
    cubicZone.style.borderRadius = "8px";

    // Create the black-border-square container
    var blackBorderSquare = document.createElement("div");
    blackBorderSquare.className = "black-border-square";
    blackBorderSquare.style.width = "300px";
    blackBorderSquare.style.height = "300px";
    blackBorderSquare.style.border = "2px solid #000";
    blackBorderSquare.style.display = "flex";
    blackBorderSquare.style.alignItems = "center";
    blackBorderSquare.style.justifyContent = "center";

    // Create the image element
    var productImage = document.createElement("img");
    productImage.id = "product-image";
    productImage.src = "C:\\Users\\josue\\Java_Project_2023_2024\\application_web\\resources\\";
    productImage.alt = "";

    // Append elements to the DOM
    blackBorderSquare.appendChild(productImage);
    cubicZone.appendChild(blackBorderSquare);

    // Append the whole component to the body or any other desired parent element
    document.body.appendChild(cubicZone);
}


/*function TableauArticle() {
    var tableau = document.createElement("tableau");
    tableau.className = "Tableau";

    var Titlehead = document.createElement("thead");
    tableau.appendChild(Titlehead);

    var Hrow = Titlehead.insertRow();

    var Hcell = document.createElement("th");
    Hcell.innerHTML = "id";
    Hrow.appendChild(Hcell);

    Hcell = document.createElement("th");
    Hcell.innerHTML = "Article";
    Hrow.appendChild(Hcell);

    Hcell = document.createElement("th");
    Hcell.innerHTML = "Prix";
    Hrow.appendChild(Hcell);

    Hcell = document.createElement("th");
    Hcell.innerHTML = "Quantité";
    Hrow.appendChild(Hcell);



}*/

