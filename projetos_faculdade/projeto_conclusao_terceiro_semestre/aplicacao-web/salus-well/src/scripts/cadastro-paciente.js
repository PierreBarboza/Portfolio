$(document).ready(function(){
    $(".form-paciente").show("slow");
    $("#btn-cadastrar").delay(500).show("slow");
})

// _____________________Lidando Com a seleção de Avatar_____________
var srcAvatarOld = "./assets/avatar/avatar-default.svg";
var srcAvatarSelected;
var modalAvatar = $('#modal-avatar')

$(document).ready(function(){
    $('#avatarSelection').click(function(){
        modalAvatar.modal({closable:false})
        modalAvatar.modal('show')

            $('.avatar').click(function(event){
                $('.avatar').css({"border":"none", "border-radius":"none"})
                srcAvatarSelected = $(event.target).attr('src')

                $(event.target).css({"border":"3px solid green", "border-radius":"30px"})
                $('#avatarPage').attr('src', srcAvatarSelected)
            });
        ; 
    })
})
$('#cancelar').click(function(){
    $('#avatarPage').attr('src', srcAvatarOld)
    $('.avatar').css({"border":"none", "border-radius":"none"})
})
$('#salvar').click(function(){
    $('#avatarPage').attr('src', srcAvatarSelected)
    srcAvatarOld = $('#avatarPage').attr('src')
    $('.ui.modal').modal('hide')
})
//__________________________________________________________________________
// Transformando os botões em checkBox
var genero = "";
var hasComorbidade = "";

$('.btn-gender').click(function(event){
    $('.btn-gender').css({"background":"none"})
    $(event.target).css({"background":"rgba(193, 231, 173, 0.662)"})
    genero = $(event.target).attr("name");
})
$('.btn-comorbidade').click(function(event){
    $('.btn-comorbidade').css({"background":"none"})
    $(event.target).css({"background":"rgba(193, 231, 173, 0.662)"})
    hasComorbidade = $(event.target).attr("value");
})
// _______________________________________________________________________
const regexMin6 = /.{6,}/gm
const regexCaps = /[A-Z]/
const regexNoCaps = /[a-z]/
const regexEspecial = /(?=.*?[#?!@$%^&*-])/gm
var value;

$("#senha").keyup(function(t){
    value = document.getElementById("senha").value;
    if (regexCaps.test(value)){
        $("#valid-2").css("display", "inherit")
        $(".rule-2").css({"font-size":"15px", "color":"green"})
    } else {
        $("#valid-2").css("display", "none")
        $(".rule-2").css({"font-size":"13px", "color":"rgb(67, 67, 67)"})
    }
    
    if (regexNoCaps.test(value)){
        $("#valid-3").css("display", "inherit")
        $(".rule-3").css({"font-size":"15px", "color":"green"})
    } else {
        $("#valid-3").css("display", "none")
        $(".rule-3").css({"font-size":"13px", "color":"rgb(67, 67, 67)"})
    }

    if (value.length >= 6){
        $("#valid-1").css("display", "inherit")
        $(".rule-1").css({"font-size":"15px", "color":"green"})

    } else if (value.length < 6){
        $("#valid-1").css("display", "none")
        $(".rule-1").css({"font-size":"13px", "color":"rgb(67, 67, 67)"})
    }
    
    if(t.keyCode == 8){
        if (value.length >= 6){
            $("#valid-1").css("display", "inherit")
            $(".rule-1").css({"font-size":"15px", "color":"green"})
        } else if (value.length < 6){
            $("#valid-1").css("display", "none")
            $(".rule-1").css({"font-size":"13px", "color":"rgb(67, 67, 67)"})
        }
    }

    
    if (regexEspecial.test(value)){
        $("#valid-4").css("display", "inherit")
        $(".rule-4").css({"font-size":"15px", "color":"green"})
    } else {
        $("#valid-4").css("display", "none")
        $(".rule-4").css({"font-size":"13px", "color":"rgb(67, 67, 67)"})
    }
    
})



// Validando os campos do usuário
function validarDadosPaciente(){

    var isValido = true;

    var nome = document.getElementById("nome").value
    var email = document.getElementById("email").value
    var endereco = document.getElementById("endereco").value
    var senha = document.getElementById("senha").value
    var confirmSenha = document.getElementById("confirmSenha").value
    var dtNascimento = document.getElementById("dtNascimento").value
    const regexEmail = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/ 
    const regexSenha = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{6,}$/
    const regexMin6 = /.{6,}/gm
    const regexCaps = /(?=.*?[AZ])/gm
    const regexNoCaps = /(?=.*?[az])/gm
    const regexEspecial = /(?=.*?[# ?!@ $%^&*-])/gm

    let listDados = [nome, email, endereco, senha, confirmSenha, dtNascimento]
    let listIdDados = ["#nome","#email","#endereco","#senha","#confirmSenha","#dtNascimento"]

    var idadeDoUsuario;

    
    if(
        nome == "" || email == "" || endereco == "" || genero == "" || 
        hasComorbidade == "" || dtNascimento == "" || senha == "" || confirmSenha == ""
    ){// Os campos não podem estar vazios
        alertar(
            '',
            'Preencha todos os campos !',
            'warning',
            'Ok'
        )  
        for (var i = 0; i < listDados.length; i++){
            if (listDados[i] == ""){
                $(listIdDados[i]).css({"border":"3px solid #FF5451"}).click(function(event){$(event.target).css({"border":"3px solid #679160"})})  
            }
        }
        if (genero == ""){
            $(".btn-gender").css({"border":"3px solid #FF5451"}).click(function(){$(".btn-gender").css({"border":"3px solid #679160"})})
        } 
        if (hasComorbidade == ""){
            $(".btn-comorbidade").css({"border":"3px solid #FF5451"}).click(function(){$(".btn-comorbidade").css({"border":"3px solid #679160"})})
        } 
        isValido = false
        
    } else if (!regexEmail.test(email)){// Email tem que ser válido !
        alertar(
            '',
            'Email inválido !',
            'error',
            'Ok'
        )
        $("#email").css({"border":"3px solid #FF5451"}).click(function(event){$(event.target).css({"border":"3px solid #679160"})})
        isValido = false

    } else if (!regexSenha.test(senha)){// Senha tem que ser válida !
        alertar(
            '',
            'Senha inválida',
            'error',
            'Ok'
        )
        $("#rules-password").css("display", "block")
        $("#senha").css({"border":"3px solid #FF5451"}).click(function(event){$(event.target).css({"border":"3px solid #679160"})})
        isValido = false

    } else if (confirmSenha != senha){// Deve confirmar a mesma senha !
        alertar(
            '',
            'Confirme a mesma senha !',
            'warning',
            'Ok'
        )
        $("#confirmSenha").css({"border":"3px solid #FF5451"}).click(function(event){$(event.target).css({"border":"3px solid #679160"})})
        isValido = false

    } else if (dtNascimento != ""){// Deve ser maior de idade !
        $("#rules-password").css("display", "none")
        var dtSplitada = dtNascimento.split("-")
        var dataAtual = new Date();
        var anoAtual = dataAtual.getFullYear();
        var anoInformado = dtSplitada[0];
        idadeDoUsuario = anoAtual - anoInformado;
        if (idadeDoUsuario < 18){
            alertar(
                '',
                'Você não possuí idade maior de 18 Anos para criar uma conta',
                'warning',
                'Ok'
            )
            isValido = false
        } else {
            $("#rules-password").css("display", "none")
            isValido = true
        }
    }

    if (isValido){
        
        const token = ''
        const endpoint = '';
        fetch (endpoint, {
            method: 'POST',
            headers:{
                'Accept' : 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : `Bearer ${token}`
            },
            body: JSON.stringify({
                nome : nome,
                email : email,
                senha : senha,
                endereco : endereco,
                idade : idade
            })
        }).then((response) => {
            return response.json();

        }).then((data) => {
            console.log(data);
            alertar(
                '',
                'Cadastro realizado com sucesso !',
                'success',
                'Ok'
            )
        });
        
        // alertar(
        //     '',
        //     'Cadastro realizado com sucesso !',
        //     'success',
        //     'Ok'
        // )
    }
    
    
}


