import { BASE_URL } from "./constant.mjs";

const tbody = document.querySelector("#tbody");
const lat = document.querySelector("#lat");
const lon = document.querySelector("#lon");
const searchForm = document.querySelector("#search-from");

async function fetchThreeHourIntervalForecast(lat, lon) {
    let res = await fetch(`${BASE_URL}/3h/${lat}/${lon}`, {
        headers: {
            "ngrok-skip-browser-warning": "none"
        }
    });
    let status = res.status
    let body = await res.json();

    return [status, body];
}

function createRow(item) {
    let weather = item.weather[0].description;
    weather = weather.charAt(0).toUpperCase() + weather.substring(1);

    return `
    <tr>
        <td>${item.dt_txt}</td>
        <td>${weather}</td>
        <td>${(item.pop * 100).toFixed(2)}%</td>
        <td>${item.main.temp_min}</td>
        <td>${item.main.temp_max}</td>
        <td>${item.wind.speed} meter/sec</td>
    </tr>`
}

async function refresh(lat, lon) {
    let [status, res] = await fetchThreeHourIntervalForecast(lat, lon);

    if (status == 500) {
        alert("Something went wrong!");
        return;
    }

    tbody.innerHTML = res.list.map(item => createRow(item)).join("");
}

searchForm.addEventListener("submit", event => {
    event.preventDefault();
    let latV = lat.value.trim()
    let lonV = lon.value.trim()

    if (isNaN(Number(latV)) || isNaN(Number(lonV))) {
        alert("Wrong latitude or longitude!");
        return
    }

    refresh(latV, lonV);
})