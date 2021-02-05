if(localStorage.length != 0) {
    let x = localStorage.getItem('user');
    let dados = JSON.parse(x);
    window.location.replace("./../html/initialScreen.html");
    
}



window.onload = () =>{
    btn.disabled = true;
    let validaForm = () =>{
        if(nome.value.length > 0 && senha.value.length > 0){
            btn.disabled = false;
        }else btn.disabled = true;
    };
    nome.oninput = validaForm;
    senha.oninput = validaForm;
    btn.onclick = (evento) =>{
        obtemDadosAJAX();
        evento.preventDefault();
        //var x = evento;
    }
}


function obtemDadosAJAX(){
    //executar chamada AJAX para a API do JSONSERVER
    let xhr = new XMLHttpRequest();
    xhr.onload = verificaLogin;
    xhr.open('GET', 'https://gerenciamento-2-0.herokuapp.com/aluno/getAll');
    xhr.send();
}

function verificaLogin(dados){
    dados = JSON.parse(this.responseText);

    for(i = 0; i < dados.length; i++){
        let nomeAluno = dados[i].nome;
        let idAluno = dados[i].id;
        let tipoAluno = dados[i].is_monitor;
        let senhaAluno = dados[i].senha;
        if(nome.value == idAluno){
            i = dados.length;
            
            if(senha.value == senhaAluno){
                login(idAluno, nomeAluno, tipoAluno);
            }else alert("Usuário ou senha incorretos!");
        }
    }
}

function login(id, nomeAluno, type){
    let user = {nome:nomeAluno, userId:id, tipo:type};
    localStorage.setItem('user', JSON.stringify(user));
    window.location.replace("./../html/initialScreen.html");
}
 