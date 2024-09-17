document.addEventListener('DOMContentLoaded', function() {
    const userId = getUserIdFromSession();

    if (!userId) {
        alert('Please login to view your favorites.');
        window.location.href = 'login.html';
        return;
    }

    fetch(`/api/favorites/list?userId=${userId}`)
        .then(response => response.json())
        .then(favorites => {
            const favoritesList = document.getElementById('favorites-list');

            if (favorites.length === 0) {
                favoritesList.innerHTML = '<p class="text-center">You have no favorite movies.</p>';
            } else {
                favorites.forEach(movie => {
                    console.log(movie.id)
                    const movieCard = createMovieCard(movie);
                    favoritesList.appendChild(movieCard);
                });
            }
        })
        .catch(error => {
            console.error('Error fetching favorites:', error);
            alert('Failed to load your favorite movies.');
        });
});

function createMovieCard(movie) {
    if (!movie.id) {
        console.error('Movie ID is missing for:', movie);
        return null;
    }

    const colDiv = document.createElement('div');
    colDiv.className = 'col-md-2 mb-4';

    colDiv.innerHTML = `
        <a href="movie-details.html?id=${movie.id}" class="text-decoration-none">
            <div class="card h-100">
                <img src="${movie.fullPosterPath}" class="card-img-top" alt="${movie.title}">
                <div class="card-body text-center">
                    <h5 class="card-title">${movie.title}</h5>
                </div>
            </div>
        </a>
    `;

    return colDiv;
}


function getUserIdFromSession() {
    const userCredentialString = sessionStorage.getItem("userCredential");
    const userCredential = JSON.parse(userCredentialString);
    return userCredential.username;
}
