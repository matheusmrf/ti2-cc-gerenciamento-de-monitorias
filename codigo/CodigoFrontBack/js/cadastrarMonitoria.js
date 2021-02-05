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
                <a href="./../html/addMonitoria.html">Adicionar Aula</a>
                <a href="#">Cadastrar Monitoria</a>
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

let btnAdd = document.getElementById('btn-adicionar')

let nomeAtiv = document.getElementById('atividade')
let dataDia = document.getElementById('dataDia')
let dataMes = document.getElementById('dataMes')
let dataAno = document.getElementById('dataAno')
let dataHora = document.getElementById('dataHora')
let dataMin = document.getElementById('dataMinuto')
let campoSala = document.getElementById('sala')
let campoPredio = document.getElementById('predio')
let campoPresencial = document.getElementById('checkbox')

btnAdd.onclick = (e) =>{
    if(campoPresencial.checked){
        if(nomeAtiv.value.length == 0 || dataDia.value.length == 0 || dataMes.value.length == 0
            || dataAno.value.length == 0 || dataHora.value.length == 0 || dataMin.value.length == 0 || campoSala.value.length == 0
            || campoPredio.value.length == 0){
                alert("Algum campo não foi preenchido!");
                e.preventDefault()
        }else {
            cadastrarMon(nomeAtiv.value, dataDia.value, dataMes.value, dataAno.value, dataHora.value, dataMin.value, campoSala.value, campoPredio.value, true);
        }
    }else{
        if(nomeAtiv.value.length == 0 || dataDia.value.length == 0 || dataMes.value.length == 0
            || dataAno.value.length == 0 || dataHora.value.length == 0 || dataMin.value.length == 0){
                alert("Algum campo não foi preenchido!");
                e.preventDefault()
        }else{
            cadastrarMon(nomeAtiv.value, dataDia.value, dataMes.value, dataAno.value, dataHora.value, dataMin.value, '-', '-', false);
        }
    }
    
}

function cadastrarMon(nome, dia, mes, ano, hra, min, sala, predio, presencial){
    let usuario = JSON.parse(localStorage.getItem('user'));
    let params = `monDia=${dia}/${mes}/${ano}&monHora=${hra}:${min}&monLocal=Predio ${predio}, Sala ${sala}&monMateria=${nome}&monIdMonitor=${usuario.userId}&monPresencial=${presencial}`
    let xhr = new XMLHttpRequest();
    xhr.open('POST', `https://gerenciamento-2-0.herokuapp.com/monitoria/add?`+params);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.onload = function(){
        if(xhr.status == "200"){
            alert("Enviado com sucesso!");
            window.location.reload();
        }
    }
    xhr.send(null);
}