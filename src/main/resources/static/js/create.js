function createNewComment() {
    console.log("here");
    let newComment = {};
    newComment.content = document.getElementById('commentContent').value;  // Corrected function name
    sendData(newComment);
}

function sendData(data) {
    const userCredentialString = sessionStorage.getItem("userCredential");
    const userCredential = JSON.parse(userCredentialString);

    const auth = btoa(userCredential.username + ':' + userCredential.password);
    fetch('/comments', {
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
