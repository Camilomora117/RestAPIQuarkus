// Div donde se añadiran los Tweets
const contenedorH1 = document.getElementById('content');
const username = document.getElementById('username');

//Setear Nombre Usuario
function setNameUser() {
  username.innerHTML = User.name;
}


// Creando los elementos de Tweets
fetch("http://localhost:8080/tweet").then(data => data.json()).then(data => createTweets(data));
function createTweets(data) {
  data.forEach(tweet => {
  const div = document.createElement('div');
  div.classList.add('tweet');
  const userInfoDiv = document.createElement('div');
  userInfoDiv.classList.add('user-info');
  const img = document.createElement('img');
  img.src = 'https://img2.freepng.es/20180720/ivv/kisspng-computer-icons-user-profile-avatar-job-icon-5b521c567f49d7.5742234415321078625214.jpg';
  const nameTimeDiv = document.createElement('div');
  nameTimeDiv.classList.add('name-time');
  const nameH2 = document.createElement('h2');
  nameH2.classList.add('name');
  nameH2.textContent = tweet.user.name;
  const timeSpan = document.createElement('span');
  timeSpan.classList.add('time');
  timeSpan.textContent = tweet.date;
  nameTimeDiv.appendChild(nameH2);
  nameTimeDiv.appendChild(timeSpan);
  userInfoDiv.appendChild(img);
  userInfoDiv.appendChild(nameTimeDiv);
  const textDiv = document.createElement('div');
  textDiv.classList.add('text');
  const p = document.createElement('p');
  p.textContent = tweet.texto;
  textDiv.appendChild(p);
  div.appendChild(userInfoDiv);
  div.appendChild(textDiv);
  contenedorH1.appendChild(div);
  })
}

function handleClickForm() {
  window.location = "FormTweet.html";
}

function handleClickCloseSession() {
  window.location = "/index.html";
}
