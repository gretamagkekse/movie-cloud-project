function createNewRating() {
    console.log("here");
    let newRating = {};
    newRating.actors = document.getElementById('ratingActors').value;
    newRating.story = document.getElementById('ratingStory').value;
    newRating.visuals = document.getElementById('ratingVisuals').value;
    newRating.comment = document.getElementById('ratingComment').value;
    sendData(newRating);
}

function sendData(data) {
    const userCredentialString = sessionStorage.getItem("userCredential");
    const userCredential = JSON.parse(userCredentialString);

    const auth = btoa(userCredential.username + ':' + userCredential.password);
    fetch('/ratings', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + auth
        },
        body: JSON.stringify(data)
    }).then(e => location.href = 'index.html')
        .catch(e => console.error(e));
}

if (userCredentialString === null || typeof userCredentialString === undefined) {
    location.href = "index.html";
}
