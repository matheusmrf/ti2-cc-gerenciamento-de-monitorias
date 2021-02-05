//pegar o nome no localstorage
function pegarNomeLS(){
    let usuario = JSON.parse(localStorage.getItem('user'));
    let nav = document.getElementById('nav-bar-type');
    if (usuario.tipo == 0) {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="#" id="nomeUsuarioField"></a><br>
                <a href="#">Minhas Atividades</a>
                <a href="./../html/addMonitoria.html">Adicionar Aula</a>
                <a href="./../html/feedbackScreen.html">Sistema de feedback</a>
                <a href="./../html/chatbot.html">Chatbot</a>
            </div>
        `;
    } else {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="#" id="nomeUsuarioField"></a><br>
                <a href="#">Minhas Atividades</a>
                <a href="./../html/addMonitoria.html">Adicionar Aula</a>
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
/*AJAX pra pegar as atividades do db.json*/

function obtemInfos(){
    let usuario = JSON.parse(localStorage.getItem('user'));

    let xhr = new XMLHttpRequest();
    xhr.open('GET', `https://gerenciamento-2-0.herokuapp.com/favoritos/getAllAluno?userId=${usuario.userId}`);
    xhr.onload = exibeAtividades;
    xhr.send();
}

function exibeAtividades(dados){
    let elemTela = document.getElementById('lista-ativ');

    dados = JSON.parse(this.responseText);

    let textHtml = '';

    for(i = 0; i < dados.length;i++){
        let id = dados[i].id;
        textHtml += `<tr><td>${id}</td>
        <td>${dados[i].materia}</td>
        <td>${dados[i].dia} - ${dados[i].hora}</td>
        <td>${dados[i].local_monitoria}</td>
        <td><a title="Remover aula" href="#" onclick="deletarFavorito(${id});"><i style="color:red;" class="fas fa-times"></i></a></td>
        </tr>`;
    }
    elemTela.innerHTML = textHtml;
}
//
function deletarFavorito(id){
    console.log(id);
    let xhr = new XMLHttpRequest();
    xhr.open('DELETE', `https://gerenciamento-2-0.herokuapp.com/favoritos/delete?idMonitoria=${id}`);
    xhr.onload = function(){
        if(xhr.status == "200"){
            alert("Removido com sucesso!");
            window.location.reload();
        }else{
            alert("Algum erro ocorreu!");
        }
    };
    xhr.send(null);
}