const userCredentialString = sessionStorage.getItem("userCredential");

if (userCredentialString !== null) {
    document.getElementById("loginLink").hidden = true;
    document.getElementById("logoutLink").hidden = false;
    document.getElementById("comment-section").hidden = false;

} else {
    document.getElementById("loginLink").hidden = false;
    document.getElementById("logoutLink").hidden = true;
    document.getElementById("comment-section").hidden = true;
}

function logout() {
    sessionStorage.clear();
    location.href = "index.html";
}
