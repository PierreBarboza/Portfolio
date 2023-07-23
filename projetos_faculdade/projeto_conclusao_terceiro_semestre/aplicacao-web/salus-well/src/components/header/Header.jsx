import React from 'react'
import style from './Header.module.css';
import icon_settings from '../../assets/icons/icon-settings.svg';
import { useNavigate } from 'react-router-dom';

export default function UserHeader(props) {

    const navigate = useNavigate();

    const navigateConfig = () => {
        const perfil = sessionStorage.getItem('keyPerfil');

        if (perfil === 'Nutricionista') {
            navigate('/perfilNutricionista');
        } else if (perfil === 'Paciente') {
            navigate('/perfilPaciente');
        } else {
            navigate('*');
        }
    }

    return (
        <>
            <div className={style.header_component}>
                <div className={style.group_title}>
                    <p>{props.titulo}</p>
                    <p>{props.subtitulo}</p>
                </div>
                <div className={style.group_icons}>
                    <img src={icon_settings} alt='icone configurações' onClick={navigateConfig} />
                </div>
            </div>
        </>
    )
}
