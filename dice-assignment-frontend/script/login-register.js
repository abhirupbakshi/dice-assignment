import { BASE_URL } from "./constant.mjs";

if (localStorage.getItem("AUTH_TOKEN")) {
    window.location.href = "/index.html";
}

const title = document.getElementById("title");
const mode = document.getElementById("mode");
const registerFormSubmitBtn = document.getElementById("registerFormSubmitBtn");
const loginform = document.getElementById("registeruser");
const username = document.getElementById("user_name");
const userpass = document.getElementById("user_pass");

let _mode = "login";

async function registerNewUser() {
    let res = await fetch(BASE_URL + "/user", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: username.value,
            password: userpass.value
        })
    });
    
    return await res.json();
}

async function userLogin() {
    let res = await fetch(BASE_URL + "/auth/login", {
        method: "POST",
        headers: {
            Authorization: `Basic ${btoa(username.value + ":" + userpass.value)}`
        }
    });
    
    return await res.json();
}

function change() {
    if (_mode == "login") {
        _mode = "register";
        registerFormSubmitBtn.innerText = "Register";
        mode.innerText = "Login";
        title.innerText = "Register Form";
    } else {
        _mode = "login";
        registerFormSubmitBtn.innerText = "Login";
        mode.innerText = "Register";
        title.innerText = "Login Form";
    }
}

mode.addEventListener("click", event => {
    change();
});

loginform.addEventListener("submit", async event => {
    event.preventDefault();

    if (_mode == "login") {
        let res = await userLogin();
        localStorage.setItem("AUTH_TOKEN", res.AUTH_TOKEN);

        window.location.href = "/index.html";
    } else {
        let body = await registerNewUser();

        if (body.username) {
            alert("Register complete");
            change();
        } else if (body.type && body.type == "user-exists") {
            alert("Choose another username");
        } else {
            alert("Something went wrong");
        }
    }
})