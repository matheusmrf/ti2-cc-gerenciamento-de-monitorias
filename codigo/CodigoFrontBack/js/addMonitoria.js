//pegar o nome no localstorage
function pegarNomeLS() {
    let usuario = JSON.parse(localStorage.getItem('user'));
    let nav = document.getElementById('nav-bar-type');
    if (usuario.tipo == 0) {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="./../html/initialScreen.html" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">Minhas Atividades</a>
                <a href="#">Adicionar Aula</a>
                <a href="./../html/feedbackScreen.html">Sistema de feedback</a>
                <a href="./../html/chatbot.html">Chatbot</a>
            </div>
        `;
    } else {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="./../html/initialScreen.html" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">Minhas Atividades</a>
                <a href="#">Adicionar Aula</a>
                <a href="./../html/cadastrarMonitoria.html">Cadastrar Monitoria</a>
                <a href="./../html/feedbackScreen.html">Sistema de feedback</a>
                <a href="./../html/visualizarFbScreen.html">Visualizar Feedback</a>
                <a href="./../html/chatbot.html">Chatbot</a>
            </div>
        `;
    }
    document.getElementById('nomeUsuarioField').innerHTML = `<i class="far fa-user"></i> ${usuario.nome}`;
}
/*Navigation bar*/
/* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
}

/*Fim Navigation bar*/


function obtemInfos() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'https://gerenciamento-2-0.herokuapp.com/monitoria/getAll');
    xhr.onload = exibeCadastroAtividades;
    xhr.send();
}

function exibeCadastroAtividades(dados) {
    let eleHtml = document.getElementById('list-ativ');

    dados = JSON.parse(this.responseText);

    let textHtml = '';

    for (i = 0; i < dados.length; i++) {
        textHtml += `
          <div class="col-sm-6">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">${dados[i].materia}</h5>
                <p class="card-text">${dados[i].dia} - Prédio:${dados[i].local_monitoria}</p>
                <a href="#" class="btn btn-primary" id="btn-adicionar" onclick="postaInfos(${dados[i].id})">Entrar Monitoria</a>
              </div>
            </div>
          </div>
          `;
    }

    eleHtml.innerHTML = textHtml;
}

function postaInfos(monId) {
    let usuario = JSON.parse(localStorage.getItem('user'));

    let xhr = new XMLHttpRequest();
    xhr.open('POST', `https://gerenciamento-2-0.herokuapp.com/favoritos/add?userId=${usuario.userId}&idMonitoria=${monId}`);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.onload = function() {
        if (xhr.status == "200") {
            alert("Monitoria adicionado a sua lista com sucesso!");
        } else {
            alert("Algum erro ocorreu!");
        }
    }
    xhr.send(null);
}