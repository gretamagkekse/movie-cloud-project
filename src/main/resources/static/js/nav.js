const userCredentialString = sessionStorage.getItem("userCredential");

if (userCredentialString !== null) {
    // User is logged in
    document.getElementById("loginLink").hidden = true;
    document.getElementById("logoutLink").hidden = false;
    document.getElementById("favoritesLink").hidden = false;
    document.getElementById("profileLink").hidden = false;  // Show the profile link
    document.getElementById("comment-section").hidden = false;  // Optional, hide comment-section if not needed
    document.getElementById("comment-section-alternative").hidden = true;
    document.getElementById("add-favorite-button").hidden = false;

} else {
    // User is not logged in
    document.getElementById("loginLink").hidden = false;
    document.getElementById("logoutLink").hidden = true;
    document.getElementById("favoritesLink").hidden = true;
    document.getElementById("profileLink").hidden = true;  // Hide the profile link
    document.getElementById("comment-section").hidden = true;  // Optional, hide comment-section if not needed
    document.getElementById("comment-section-alternative").hidden = false;
    document.getElementById("add-favorite-button").hidden = true;
}

function logout() {
    sessionStorage.clear();
    location.href = "index.html";
}
