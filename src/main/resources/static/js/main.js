"use strict";

var searchButton = document.getElementById("search-btn");
var searchInput = document.getElementById("search-txt");
var cityName = document.getElementById("city-name");
var temperature = document.getElementById("temp");
var url = "weather/city/";

searchButton.addEventListener("click", findWeatherDetails);
searchInput.addEventListener("keyup", enterPressed);

function enterPressed(event) {
    if (event.key === "Enter") {
        findWeatherDetails();
    }
}

function findWeatherDetails() {
    if (searchInput.value === "") {

    }else {
        httpRequestAsync( url + searchInput.value, responseHandler);
    }
}

function responseHandler(response) {
    var jsonObject = JSON.parse(response);
    cityName.innerHTML = searchInput.value[0].toUpperCase() + searchInput.value.substring(1).toLowerCase();
    temperature.innerHTML = jsonObject.temperature + "°";
}

function httpRequestAsync(url, callback)
{
    console.log("hello");
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState == 4 && httpRequest.status == 200) {
            callback(httpRequest.responseText);
        }
        if (httpRequest.readyState == 4 && httpRequest.status == 405) {
            alert(JSON.parse(httpRequest.responseText).message);
        }
        if (httpRequest.readyState == 4 && httpRequest.status == 404) {
            alert("Ошибка: 404 not found");
        }
    }
    httpRequest.open("GET", url, true); // true for asynchronous
    httpRequest.send();
}
