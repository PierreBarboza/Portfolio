
// Alerta em formatado MODAL Central
// Customizado de acordo com os parametros recebidos
// Basta Apenas informar os parametros na chamada da função, EM ORDEM !
function alertar(titulo = '', texto = '', icone ='', confirmButtonText = '', showButtonCancel = ''){
    var timerCustom = ''

    if(icone == 'warning'){
        iconColor = '#FF5451'
        timerCustom = '10000'
        buttonConfirmColor = '#FF5451'

    } else if (icone == 'error'){
        iconColor = '#FF5451'
        timerCustom = '10000'
        buttonConfirmColor = '#FF5451'

    } else if (icone == 'success'){
        iconColor = 'green'
        timerCustom = '8000'
        buttonConfirmColor = '#679160';
    }

    return Swal.fire({
        title: titulo,
        text: texto,
        icon: icone,
        iconColor: iconColor,
        showCancelButton: showButtonCancel,
        confirmButtonText: confirmButtonText,
        confirmButtonColor: buttonConfirmColor,
        timer: timerCustom,
        timerProgressBar: true
    })
}

// Alerta em formatado TOAST
// Customizado de acordo com os parametros recebidos
// Basta Apenas informar os parametros na chamada da função, EM ORDEM !
function alertaToast(titulo = '', icone = '', tempo = ''){
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: tempo,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })
      
      Toast.fire({
        icon: icone,
        title: titulo
      })
}
