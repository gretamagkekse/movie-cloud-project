function createNewComment() {
    console.log("here");
    let newComment = {};
    newComment.content = document.getElementById('commentContent').value;  // Corrected function name
    sendData(newComment);
}

function sendData(data) {
    fetch('/comments', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(e => location.href = 'index.html')
        .catch(e => console.error(e));
}
