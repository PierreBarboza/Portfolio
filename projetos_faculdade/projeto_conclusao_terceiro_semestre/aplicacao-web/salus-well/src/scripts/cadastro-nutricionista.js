$(document).ready(function(){
    $(".form-nutricionista").show("slow");
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
//__________________________________________________________________

var genero = "";
$('.btn-gender').click(function(event){
    $('.btn-gender').css({"background":"none"})
    $(event.target).css({"background":"rgba(193, 231, 173, 0.662)"})
    genero = $(event.target).attr("name");
})

// _______________________________________________________________________

// Validando os campos do usuário
function validarDadosNutricionista(){
    var isValido = true;

    var nome = document.getElementById("nome").value
    var email = document.getElementById("email").value
    var endereco = document.getElementById("endereco").value
    var registro = document.getElementById("registro").value
    var crn = document.getElementById("crn").value
    var senha = document.getElementById("senha").value
    var confirmSenha = document.getElementById("confirmSenha").value
    const regexEmail = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/ 
    const regexSenha = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{6,}$/

    let listDados = [nome, email, endereco, senha, confirmSenha, registro, crn]
    let listIdDados = ["#nome","#email","#endereco","#senha","#confirmSenha","#registro", "#crn"]
    
    if(
        nome == "" || email == "" || genero == "" || 
        crn == "" || registro == "" || senha == "" || confirmSenha == "" || endereco == ""
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
            'Senha inválida !',
            'Crie a senha conforme as regras estabelecidas',
            'error',
            'Ok'
        )
        $("#rules-password").css("display", "block")
        $("#senha").css({"border":"3px solid #FF5451"}).click(function(event){$(event.target).css({"border":"3px solid #679160"})})
        isValido = false
    } else if (confirmSenha != senha){// Deve confirmar a mesma senha !
        $("#rules-password").css("display", "block")
        alertar(
            '',
            'Confirme a mesma senha !',
            'warning',
            'Ok'
        )
        $("#confirmSenha").css({"border":"3px solid #FF5451"}).click(function(event){$(event.target).css({"border":"3px solid #679160"})})
        isValido = false
    } else {
        $("#rules-password").css("display", "none")
        isValido = true
    }

    if (isValido){
        alertar(
            '',
            'Cadastro realizado com sucesso !',
            'success',
            'Ok'
        )
    }
    
    
}
