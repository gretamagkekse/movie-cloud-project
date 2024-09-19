
    $(document).ready(function() {
    function appendMovie(movie, container) {
        const img = new Image();
        img.src = `https://image.tmdb.org/t/p/w200${movie.poster_path}`;
        img.alt = movie.title;

        img.onload = function() {
            const movieHtml = `
            <div class="col-md-2">
                  <a href="/movie-details.html?id=${movie.id}">
                    <div class="card mb-4">
                        <img src="${img.src}" class="card-img-top" alt="${movie.title}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${movie.title}</h5>
                        </div>
                    </div>
                </a>
            </div>
        `;
            $(container).append(movieHtml);
        };

        img.onerror = function() {
            console.log('Image not found for movie: ' + movie.title);
        };
    }


    $.get('/api/movies/popular', function(data) {
    const movies = JSON.parse(data).results;
    movies.forEach(movie => {
    appendMovie(movie, '#movies');
});
});


    function updateUrl(query) {
    const newUrl = `${window.location.origin}?query=${encodeURIComponent(query)}`;
    window.history.pushState({ path: newUrl }, '', newUrl);
}

    $('#search-form').submit(function(event) {
    event.preventDefault();
    const query = $('#search-query').val();

    $('#movies').empty();
    $('#popular-header').hide();

    $('#search-results').empty();
    $('#search-header').show();

    updateUrl(query);

    $.get(`/api/movies/search?query=${query}`, function(data) {
    const results = JSON.parse(data).results;
    results.forEach(movie => {
    appendMovie(movie, '#search-results');
});
});
});


    window.onpopstate = function(event) {
    const params = new URLSearchParams(window.location.search);
    const query = params.get('query');

    if (query) {
    $('#search-query').val(query);
    $('#search-form').submit();
} else {
    $('#movies').empty();
    $('#search-results').empty();
    $('#popular-header').show();
    $('#search-header').hide();

    $.get('/api/movies/popular', function(data) {
    const movies = JSON.parse(data).results;
    movies.forEach(movie => {
    appendMovie(movie, '#movies');
});
});
}
};
});
