// Load comments of logged in user
function loadUserComments() {
    const userCredentialString = sessionStorage.getItem("userCredential");
    const userCredential = JSON.parse(userCredentialString);
    const username = userCredential.username;
    const auth = btoa(userCredential.username + ':' + userCredential.password);

    fetch('/api/tmdb/apikey', {
        method: 'GET',
        headers: {
            'Authorization': 'Basic ' + auth  // Falls Authentifizierung nötig
        }
    })
        .then(response => response.text())
        .then(tmdbApiKey => {
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
                    if (comments.length === 0) {
                        commentsList.innerHTML = '<p class="text-center">You have not written any comments/ratings yet.</p>';
                    } else {
                        comments.forEach(comment => {
                            // Anfrage an die TMDB API für den Filmtitel basierend auf movieId
                            fetch(`https://api.themoviedb.org/3/movie/${comment.movieId}?api_key=${tmdbApiKey}`)
                                .then(response => response.json())
                                .then(movieData => {
                                    const movieTitle = movieData.title || 'Unknown Movie'; // Fallback, falls kein Titel gefunden wird
                                    const listItem = document.createElement('li');
                                    listItem.className = 'list-group-item';

                                    // HTML für die Darstellung mit Bootstrap
                                    listItem.innerHTML = `
                                        <div class="movie-comment">
                                            <h5 class="movie-title mb-2">${movieTitle}</h5>
                                            <div class="ratings mb-2">
                                                <strong>Actors:</strong> <span class="badge bg-primary">${comment.ratingActors}/5</span>
                                                <strong>Story:</strong> <span class="badge bg-primary">${comment.ratingStory}/5</span>
                                                <strong>Visuals:</strong> <span class="badge bg-primary ">${comment.ratingVisuals}/5</span>
                                            </div>
                                            <p class="comment-text mb-3"><em>${comment.comment}</em></p>
                                            <button class="btn btn-danger btn-sm float-end" onclick="deleteComment(${comment.commentId})">Delete</button>
                                        </div>
                                    `;

                                    // Hinzufügen des Listenelements zur Kommentarliste
                                    commentsList.appendChild(listItem);
                                })
                                .catch(error => {
                                    console.error('Error fetching movie title:', error);
                                    // Falls ein Fehler auftritt, die Movie-ID anzeigen
                                    const listItem = document.createElement('li');
                                    listItem.className = 'list-group-item';
                                    listItem.innerHTML = `
                                        <div class="movie-comment">
                                            <h5 class="movie-title mb-2">Movie ID: ${comment.movieId}</h5>
                                            <div class="ratings mb-2">
                                                <strong>Actors:</strong> <span class="badge bg-primary">${comment.ratingActors}/5</span>
                                                <strong>Story:</strong> <span class="badge bg-primary">${comment.ratingStory}/5</span>
                                                <strong>Visuals:</strong> <span class="badge bg-primary">${comment.ratingVisuals}/5</span>
                                            </div>
                                            <p class="comment-text mb-3"><em>${comment.comment}</em></p>
                                            <button class="btn btn-danger btn-sm float-end" onclick="deleteComment(${comment.commentId})">Delete</button>
                                        </div>
                                    `;
                                    commentsList.appendChild(listItem);
                                });
                        });
                    }
                })
                .catch(error => console.error('Error fetching user comments:', error));
        })
        .catch(error => console.error('Error fetching TMDB API key:', error));
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