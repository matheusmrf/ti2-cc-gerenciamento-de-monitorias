//pegar o nome no localstorage
function pegarNomeLS(){
    let usuario = JSON.parse(localStorage.getItem('user'));
    let nav = document.getElementById('nav-bar-type');
    if (usuario.tipo == 0) {
        nav.innerHTML = `
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <h1 style="color:white;">Menu</h1>
                <a style="color: rgb(255, 255, 255);" href="./../html/initialScreen.html" id="nomeUsuarioField"></a><br>
                <a href="./../html/initialScreen.html">Minhas Atividades</a>
                <a href="./../html/addMonitoria.html">Adicionar Aula</a>
                <a href="#">Sistema de feedback</a>
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
                <a href="./../html/addMonitoria.html">Adicionar Aula</a>
                <a href="./../html/cadastrarMonitoria.html">Cadastrar Monitoria</a>
                <a href="#">Sistema de feedback</a>
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

let campoTexto = document.getElementById('campoTexto');
let btnEnviar = document.getElementById('btn-enviar');
//btnEnviar.disabled = true;
    
btnEnviar.onclick = ()=>{
    console.log("pça")
    if(campoTexto.value.length == 0){
        alert("Campos vazios")
    }else{
        enviarFb(campoTexto.value);
    }
}

function enviarFb(campo){
    let usuario = JSON.parse(localStorage.getItem('user'));
  
    let xhr = new XMLHttpRequest();
    xhr.open('POST', `https://gerenciamento-2-0.herokuapp.com/feedback/add?fbDescricao=${campo}&userId=${usuario.userId}`);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.onload = function(){
        if(xhr.status == "200"){
            alert("Enviado com sucesso!");
            window.location.reload();
        }
    }
    xhr.send(null);
}