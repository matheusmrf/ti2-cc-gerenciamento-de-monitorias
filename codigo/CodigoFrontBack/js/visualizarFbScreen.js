//pegar o nome no localstorage
function pegarNomeLS(){
    let usuario = JSON.parse(localStorage.getItem('user'));
    let nav = document.getElementById('nav-bar-type');
    if(usuario.tipo == 0){
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <a style="color: rgb(255, 255, 255);" href="./../html/initialScreen.html" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">- Minhas Atividades</a>
                <a href="./../html/addMonitoria.html">- Adicionar Aula</a>
                <a href="./../html/feedbackScreen.html">- Sistema de feedback</a>
                <a href="./../html/chatbot.html">Chatbot</a>
            </div>
        `;
    }else{
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <a style="color: rgb(255, 255, 255);" href="./../html/initialScreen.html" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">- Minhas Atividades</a>
                <a href="./../html/addMonitoria.html">- Adicionar Aula</a>
                <a href="./../html/cadastrarMonitoria.html">- Cadastrar Monitoria</a>
                <a href="./../html/feedbackScreen.html">- Sistema de feedback</a>
                <a href="#">- Visualizar Feedback</a>
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


function chamadaFb(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'https://gerenciamento-2-0.herokuapp.com/feedback/getAll');
    xhr.onload = mostraFb;
    xhr.send();
}

function mostraFb(dados){
    let tela = document.getElementById('cards-fb');
    let text = '';

    dados = JSON.parse(this.responseText);
    console.log(dados)

    for(i = 0; i < dados.length; i++){
        text+=`<div class="card text-white bg-dark mb-3">
                    <h5 class="card-header">${dados[i].id}</h5>
                    <div class="card-body">
                        <p class="card-text">${dados[i].mensagem}</p>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <br>
                        <a title="Deletar feedback" href="#" onclick="deletarFB(${dados[i].id})"><i style="color:red;" class="fas fa-times"></i>Remover Feedback</a>
                    </div>
                </div>
                `
    }

    tela.innerHTML = text;
}

function deletarFB(id){
    let xhr = new XMLHttpRequest();
    xhr.open('DELETE', `https://gerenciamento-2-0.herokuapp.com/feedback/delete?idFeedback=${id}`);
    xhr.onload = function(){
        if(xhr.status == "200"){
            alert("Deletado com sucesso!");
            window.location.reload();
        }else{
            alert("Algum erro ocorreu!");
        }
    };
    xhr.send(null);
}