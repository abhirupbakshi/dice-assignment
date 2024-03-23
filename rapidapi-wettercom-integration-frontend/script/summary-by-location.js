import { BASE_URL } from "./constant.mjs";

const tbody = document.querySelector("#tbody");
const search = document.querySelector("#search");
const searchForm = document.querySelector("#search-from");

async function fetchSummaryByLocation(location) {
    let res = await fetch(`${BASE_URL}/summary/${location}`);
    let status = res.status
    let body = await res.json();

    return [status, body];
}

function createRow(item) {
    let weather = item.weather.text;
    weather = weather.charAt(0).toLocaleUpperCase() + weather.substring(1);

    return `
    <tr>
        <td>${item.date}</td>
        <td>${weather}</td>
        <td>${item.prec.probability}%</td>
        <td>${item.temperature.min}</td>
        <td>${item.temperature.max}</td>
        <td>${item.wind.direction} at ${item.wind.max} ${item.wind.unit}</td>
    </tr>`
}

async function refresh(location) {
    let [status, res] = await fetchSummaryByLocation(location);

    if (status != 200) {
        let details = res.details ? res.details : null;

        if (status == 500) {
            details = "Something went wrong!";
        }
       
        if (Array.isArray(details)) {
            details = details.map(d => d["message"]).join("\n");
        }
        
        alert(details);
        return;
    }

    tbody.innerHTML = res.forecast.items.map(item => createRow(item)).join("");
}

searchForm.addEventListener("submit", event => {
    event.preventDefault();
    refresh(search.value);
})