import { BASE_URL } from "./constant.mjs";

const tbody = document.querySelector("#tbody");
const location = document.querySelector("#location");
const searchForm = document.querySelector("#search-from");

async function fetchThreeHourIntervalForecast(location) {
    let res = await fetch(`${BASE_URL}/forecast/3h/${location}`, {
        headers: {
            "ngrok-skip-browser-warning": "none",
            "Authorization": `Bearer ${localStorage.getItem("AUTH_TOKEN")}`
        }
    });
    let status = res.status
    let body = await res.json();

    return [status, body];
}

function convertDate(raw) {
    return new Date(raw).toISOString().split("T")[0];
}

function createRow(item) {
    let weather = item.weather[0].description;
    weather = weather.charAt(0).toUpperCase() + weather.substring(1);

    return `
    <tr>
        <td>${convertDate(item.dt_txt)}</td>
        <td>${weather}</td>
        <td>${(item.pop * 100).toFixed(2)}%</td>
        <td>${item.main.temp_min}</td>
        <td>${item.main.temp_max}</td>
        <td>${item.wind.speed} meter/sec</td>
    </tr>`
}

async function refresh(location) {
    let [status, res] = await fetchThreeHourIntervalForecast(location);

    if (status == 500) {
        alert("Something went wrong!");
        return;
    }

    tbody.innerHTML = res.list.map(item => createRow(item)).join("");
}

searchForm.addEventListener("submit", event => {
    event.preventDefault();
    refresh(location.value);
})