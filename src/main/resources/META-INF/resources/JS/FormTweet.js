
const formContainer = document.querySelector('.container');
const form = document.querySelector('form');
const textarea = document.querySelector('textarea');

form.addEventListener('submit', (event) => {
	event.preventDefault();
    postTweet();
});

function cancelPost() {
    window.location = "Home.html";
}

function postTweet() {
    const formData = {
        "user": {
            "id": "643f77b5377c8249955228f6",
            "email": "yesid@mail.com",
            "name": "Yesid Mora",
            "password": "12345"
        },
        "texto": textarea.value,
        "date": new Date()
    }
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    fetch("http://localhost:8080/tweet", {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(formData)
    })
    .then(response => {
        window.location = "Home.html";
    })
    .catch(error => {
        console.error(error);
    });
}
