import Swal from 'sweetalert2';

function AlertConfirm(icon, title, text, timer) {
  return new Promise((resolve, reject) => {
    Swal.fire({
      position: 'center',
      icon: icon,
      title: title,
      text: text,
      showDenyButton: true,
      confirmButtonText: 'Confirmar',
      denyButtonText: `Cancelar`,
    }).then((result) => {
      if (result.isConfirmed) {
        resolve(true);
      } else if (result.isDenied) {
        resolve(false);
      } else {
      }
    }).catch((error) => {
      reject(error);
    });
  });
}

export default AlertConfirm;


