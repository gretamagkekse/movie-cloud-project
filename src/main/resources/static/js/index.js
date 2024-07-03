
function showComments(data) {
    if(data.length === 0){
    return;
    }
    const contentDiv = document.getElementById('content');
    let comments = '';
    data.forEach(comment => {
        comments += '<div><h3>' + comment.author.userName + ' commented: </h3></div>';
        comments += '<div><p>' + comment.content + '</p></div>';

    });
    contentDiv.innerHTML = comments;

}



fetch('/comments')
    .then(resp => resp.json())
    .then(data => showComments(data))
    .catch(err => console.error(err))