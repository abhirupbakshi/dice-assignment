const logout = document.getElementById("logout");

logout.addEventListener("click", event => {
    localStorage.removeItem("AUTH_TOKEN");
    location.reload();
});