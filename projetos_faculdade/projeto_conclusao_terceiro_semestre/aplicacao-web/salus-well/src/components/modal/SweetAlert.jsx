import Swal from 'sweetalert2';

function Alert(icon, title, text, timer) {
    return (
        Swal.fire({
            position: 'center',
            icon: icon,
            title: title,
            text: text,
            showConfirmButton: false,
            timer: timer
        })
    )
}
export default Alert;


