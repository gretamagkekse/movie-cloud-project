const params = new URLSearchParams(window.location.search);
const movieId = params.get("id");

document.addEventListener('DOMContentLoaded', function() {
    function loadMovieDetails() {
        const movieId = getMovieIdFromUrl();

        fetch(`/api/movie/${movieId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error fetching movie details: ${response.status}`);
                }
                return response.json();
            })
            .then(movie => {
                document.getElementById("movie-title").innerText = movie.title;
                document.getElementById("movie-overview").innerText = movie.overview;

                if (movie.vote_average !== undefined && movie.vote_average !== null) {
                    document.getElementById("movie-vote").innerText = movie.vote_average.toFixed(1);
                } else {
                    document.getElementById("movie-vote").innerText = "No vote average available";
                }

                document.getElementById("movie-poster").src = movie.fullPosterPath;
            })
            .catch(error => {
                console.error("Error fetching movie details:", error);
                alert("Failed to load movie details.");
            });
    }

    function checkIfMovieIsFavorite() {
        const movieId = getMovieIdFromUrl();
        const userId = getUserIdFromSession();

        if (!userId) {
            console.log('No user logged in, skipping favorite check.');
            return;
        }

        fetch(`/api/favorites/is-favorite?userId=${userId}&movieId=${movieId}`)
            .then(response => response.json())
            .then(isFavorite => {
                updateFavoriteButton(isFavorite);
            })
            .catch(error => console.error('Error checking if movie is favorite:', error));
    }

    function updateFavoriteButton(isFavorite) {
        const favoriteBtn = document.getElementById('favorite-btn');
        if (isFavorite) {
            favoriteBtn.textContent = 'Remove from Favorites';
            favoriteBtn.classList.add('remove');
            favoriteBtn.classList.remove('btn-primary');
            favoriteBtn.classList.add('btn-danger');
        } else {
            favoriteBtn.textContent = 'Add to Favorites';
            favoriteBtn.classList.remove('remove');
            favoriteBtn.classList.add('btn-primary');
            favoriteBtn.classList.remove('btn-danger');
        }
    }

    function addToFavorites(userId, movieId) {
        $.ajax({
            url: '/api/favorites/add',
            method: 'POST',
            data: {
                userId: userId,
                movieId: movieId
            },
            success: function() {
                alert('Movie added to favorites!');
                updateFavoriteButton(true);
            },
            error: function(xhr, status, error) {
                alert('Error adding movie to favorites.');
                console.error(xhr, status, error);
            }
        });
    }

    function removeFromFavorites(userId, movieId) {
        $.ajax({
            url: '/api/favorites/remove',
            method: 'POST',
            data: {
                userId: userId,
                movieId: movieId
            },
            success: function() {
                alert('Movie removed from favorites!');
                updateFavoriteButton(false);
            },
            error: function(xhr, status, error) {
                alert('Error removing movie from favorites.');
                console.error(xhr, status, error);
            }
        });
    }

    function getMovieIdFromUrl() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('id');
    }

    function getUserIdFromSession() {
        const userCredentialString = sessionStorage.getItem("userCredential");
        if (!userCredentialString) {
            return null;
        }
        const userCredential = JSON.parse(userCredentialString);
        return userCredential.username;
    }

    loadMovieDetails();
    checkIfMovieIsFavorite();

    const favoriteBtn = document.getElementById('favorite-btn');
    const movieId = getMovieIdFromUrl();
    const userId = getUserIdFromSession();

    favoriteBtn.addEventListener('click', function() {
        if (!userId) {
            alert('Please login to add movies to your favorites.');
            return;
        }

        if (favoriteBtn.classList.contains('remove')) {
            // Remove from favorites
            removeFromFavorites(userId, movieId);
        } else {
            // Add to favorites
            addToFavorites(userId, movieId);
        }
    });
});

function loadCommentsAndCalculateAverage() {
    fetch(`/api/comments/${movieId}`)
        .then(response => response.json())
        .then(comments => {
            const commentsList = document.getElementById("comments-list");
            commentsList.innerHTML = ''; // Clear previous comments

            let totalRatingActors = 0, totalRatingStory = 0, totalRatingVisuals = 0, commentCount = 0;

            if(comments.length === 0){
                commentsList.innerHTML = '<p class="text-center">There are no comments/ratings yet.</p>';
            }else{
            comments.forEach(comment => {
                commentCount++;

                // sum ratings for each category
                totalRatingActors += comment.ratingActors;
                totalRatingStory += comment.ratingStory;
                totalRatingVisuals += comment.ratingVisuals;

                // comment list item
                const listItem = document.createElement("li");
                listItem.className = "list-group-item";

                // Display stars
                const actorsStars = generateStarRating(comment.ratingActors);
                const storyStars = generateStarRating(comment.ratingStory);
                const visualsStars = generateStarRating(comment.ratingVisuals);

                listItem.innerHTML = `
                    <strong>Actors:</strong> ${actorsStars}<br>
                    <strong>Story:</strong> ${storyStars}<br>
                    <strong>Visuals:</strong> ${visualsStars}<br>
                    <p>${comment.comment}</p>
                `;

                commentsList.appendChild(listItem);
            })};

            // Calculate averages if there are comments
            if (commentCount > 0) {
                const avgRatingActors = (totalRatingActors / commentCount).toFixed(1);
                const avgRatingStory = (totalRatingStory / commentCount).toFixed(1);
                const avgRatingVisuals = (totalRatingVisuals / commentCount).toFixed(1);
                const overallAverage = ((totalRatingActors + totalRatingStory + totalRatingVisuals) / (commentCount * 3)).toFixed(1);

                // Update the averages in the HTML
                document.getElementById("average-actors").innerText = `Actors: ${avgRatingActors} / 5`;
                document.getElementById("average-story").innerText = `Story: ${avgRatingStory} / 5`;
                document.getElementById("average-visuals").innerText = `Visuals: ${avgRatingVisuals} / 5`;
                document.getElementById("movie-average-rating").innerText = `Average Rating: ${overallAverage} / 5`;
            } else {
                // No comments, so no ratings available
                document.getElementById("movie-average-rating").innerText = "No ratings available";
            }
        })
        .catch(error => console.error("Error fetching comments:", error));
}


//generate stars on previous comments
function generateStarRating(rating) {
    let starHtml = '';
    for (let i = 1; i <= 5; i++) {
        if (i <= rating) {
            starHtml += '<span class="display-star">&#9733;</span>'; // Filled star
        } else {
            starHtml += '<span class="display-star" style="color: #ddd;">&#9733;</span>'; // Empty star
        }
    }
    return starHtml;
}

//create stars for comment form
function setupStarRating() {
    const ratingGroups = ['Actors', 'Story', 'Visuals'];

    ratingGroups.forEach(group => {
        const radios = document.querySelectorAll(`#rating${group} input`);
        const hiddenInput = document.getElementById(`rating${group}Value`);

        radios.forEach(radio => {
            radio.addEventListener('change', function () {
                hiddenInput.value = this.value; // Set the hidden input to the selected value
            });
        });
    });
}

// Comment/Rating form
document.getElementById("comment-form").addEventListener("submit", function(event) {
    const userCredentialString = sessionStorage.getItem("userCredential");
    const userCredential = JSON.parse(userCredentialString);
    const auth = btoa(userCredential.username + ':' + userCredential.password);

    event.preventDefault();

    const commentData = {
        ratingActors: parseInt(document.getElementById("ratingActorsValue").value, 10),
        ratingStory: parseInt(document.getElementById("ratingStoryValue").value, 10),
        ratingVisuals: parseInt(document.getElementById("ratingVisualsValue").value, 10),
        comment: document.getElementById("content").value
    };

    fetch(`/api/comments/${movieId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            'Authorization': 'Basic ' + auth
        },
        body: JSON.stringify(commentData)
    })
        .then(response => {
            if (response.status === 401) {
                alert("Unauthorized: Please log in.");
                window.location.href = 'login.html';
            } else if (!response.ok) {
                throw new Error(`Error: ${response.status}`);
            }
            return response.json();
        })
        .then(() => {
            loadCommentsAndCalculateAverage();  // Reload the comments
            document.getElementById("comment-form").reset();  // Clear form
        })
        .catch(error => console.error("Error submitting comment:", error));
});


// Load movie details and comments when the page loads
// Initialize the star rating system for each category
setupStarRating();
loadCommentsAndCalculateAverage();