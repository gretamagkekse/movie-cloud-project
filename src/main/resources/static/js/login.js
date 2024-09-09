function login() {
    let credentials = {};
    credentials.username = document.getElementById('inputUsername').value;
    credentials.password = document.getElementById('inputPassword').value;

    const auth = btoa(credentials.username + ':' + credentials.password);

    fetch('/me', {
        method: 'GET',
        headers: {
            'Authorization': 'Basic ' + auth,
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error(response.statusText)
        }
        const userCredentials = {
            username: credentials.username,
            password: credentials.password
        };

        sessionStorage.setItem("userCredential", JSON.stringify(userCredentials));
        console.log("Credentials stored successfully");

        return response.json();
    }).then(e => location.href = "index.html")
        .catch(e => console.error(e));

}