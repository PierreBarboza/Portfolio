import React, { useEffect, useState } from 'react'
import style from '../paciente/PacienteHomeScreen.module.css';
import Header from "../../header/Header";
import img_main_card from "../../../assets/icons/img-home-card-nutricionista.svg"
import icon_diario_alimentar from "../../../assets/icons/datas.png"
import icon_alimento from "../../../assets/icons/frutas.png"
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import urlBase from '../../../Api';

export default function UserHomeScreen() {
    const navigate = useNavigate();
    const [getDataUser, setDataUser] = useState('');

    useEffect(() => {
        const dataUserToJson = JSON.parse(sessionStorage.getItem('dataUser'));
        if (dataUserToJson) {
            setDataUser(dataUserToJson);
        }
    }, []);

    function getCurrentDate() {
        const currentDate = new Date();
        const day = String(currentDate.getDate()).padStart(2, '0');
        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
        const year = currentDate.getFullYear();
        return `${year}-${month}-${day}`;
    }
    const currentDate = getCurrentDate();

    const [qtdItensRotina, setQtdItensRotina] = useState(0);
    useEffect(() => {
        const fetchDiarios = async () => {
            try {
                let response;
                response = await axios.get(urlBase + `diarios/listar-consumir?id=${sessionStorage.getItem('idUser')}&data=${currentDate}`);
                setQtdItensRotina(response.data.length);
            } catch (error) {
                console.log(error);
                setQtdItensRotina(0);
            }
        }
        fetchDiarios();
    }, [qtdItensRotina]);

    return (
        <>
            <div className={style.main_user_home_screen}>
                <Header titulo={"Olá " + getDataUser.nome} subtitulo="Seja bem-vindo(a)" />
                <div className={style.main_card_user_home}>
                    <div className={style.title_main_card}>
                        <p className={style.title}>Nutricionistas</p>
                        <p className={style.sub_title}>Encontre um nutricionista e começe agora mesmo a sua rotina alimentar. 🥙</p>
                        <button className="button_red" onClick={() => navigate('/listaNutricionistas')}>Consultar</button>
                    </div>
                    <div className={style.img_main_card}>
                        <img src={img_main_card} alt="img_main" />
                    </div>
                </div>
                <div className={style.main_group_card_user_home}>
                    {
                        qtdItensRotina <= 0 ?
                            (
                                <div className={style.card}>
                                    <p>Diário alimentar</p>
                                    <img src={icon_diario_alimentar} alt="icon_diario" />
                                    <p>Você ainda não possui um diário Alimentar</p>
                                </div>
                            )
                            :
                            (
                                <div className={style.card}>
                                    <p>Diário alimentar</p>
                                    <img src={icon_diario_alimentar} alt="icon_diario" />
                                    <button className='button_red'>Consultar rotina</button>
                                </div>
                            )
                    }

                    <div className={style.card}>
                        <p>Alimentos</p>
                        <img src={icon_alimento} alt="icon_alimento" />
                        <p>Veja detalhes específicios de cada alimento e veja seus benefícios e pripriedades</p>
                        <button className="button_red" onClick={() => navigate('/searchFood')}>Consultar alimentos</button>
                    </div>
                </div>
            </div>
        </>
    )
}