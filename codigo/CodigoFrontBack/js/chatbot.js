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
                <a href="./../html/feedbackScreen.html">Sistema de feedback</a>
                <a href="#">Chatbot</a>
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
                <a href="./../html/feedbackScreen.html">Sistema de feedback</a>
                <a href="./../html/visualizarFbScreen.html">Visualizar Feedback</a>
                <a href="#">Sistema de feedback</a>
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