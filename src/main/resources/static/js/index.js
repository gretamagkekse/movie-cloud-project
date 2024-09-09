//TODO: change to movie overview
function showRatings(data) {
    if (data.length === 0) {
        return;
    }
    const contentDiv = document.getElementById('content');
    let ratings = '';
    data.forEach(rating => {
        ratings += '<div><h3>' + rating.author.userName + ' rated: </h3></div>';
        ratings += '<div><p> Actors: ' + rating.actors + '</p></div>';
        ratings += '<div><p> Story: ' + rating.story + '</p></div>';
        ratings += '<div><p> Visuals: ' + rating.visuals + '</p></div>';
        ratings += '<div><p> Comment: ' + rating.comment + '</p></div>';

    });
    contentDiv.innerHTML = ratings;

}

fetch('/ratings')
    .then(resp => resp.json())
    .then(data => showRatings(data))
    .catch(err => console.error(err))