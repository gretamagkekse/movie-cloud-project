// Load comments of logged in user
function loadUserComments() {
    const userCredentialString = sessionStorage.getItem("userCredential");
    const userCredential = JSON.parse(userCredentialString);
    const username = userCredential.username;
    const auth = btoa(userCredential.username + ':' + userCredential.password);

    fetch(`/api/comments/user-comments/${username}`, {
        method: 'GET',
        headers: {
            'Authorization': 'Basic ' + auth
        }
    })
        .then(response => response.json())
        .then(comments => {
        const commentsList = document.getElementById('user-comments-list');
        commentsList.innerHTML = '';  // Clear previous comments
        if(comments.length === 0){
            commentsList.innerHTML = '<p class="text-center">You have not written any comments/ratings yet.</p>';
        }else{
            comments.forEach(comment => {
                const listItem = document.createElement('li');
                listItem.className = 'list-group-item';
                listItem.innerHTML = `
                        <strong>Actors:</strong> (${comment.ratingActors}/5),
                        <strong>Story:</strong> (${comment.ratingStory}/5),
                        <strong>Visuals:</strong> (${comment.ratingVisuals}/5): ${comment.comment}
                        <button class="btn btn-danger btn-sm float-end" onclick="deleteComment(${comment.commentId})">Delete</button>
                    `;
                commentsList.appendChild(listItem);
            })};
    })
        .catch(error => console.error('Error fetching user comments:', error));
}

// Delete comment which belongs to the logged in user
function deleteComment(commentId) {
    // Retrieve user credentials from session storage
    const userCredentialString = sessionStorage.getItem("userCredential");

    if (!userCredentialString) {
        console.error('User is not logged in');
        return;
    }

    const userCredential = JSON.parse(userCredentialString);
    const auth = btoa(userCredential.username + ':' + userCredential.password);  // Base64 encode username and password for basic auth

    // Send DELETE request to the server to delete the comment by commentId
    fetch(`/api/comments/user-comments/${commentId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Basic ' + auth,
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            console.log('Comment deleted successfully');
            loadUserComments();  // Reload the user's comments after deletion
        } else {
            throw new Error('Failed to delete comment. Status: ' + response.status);
        }
    })
        .catch(error => {
        console.error('Error deleting comment:', error);
    });
}

// Load comments when the page loads
loadUserComments();