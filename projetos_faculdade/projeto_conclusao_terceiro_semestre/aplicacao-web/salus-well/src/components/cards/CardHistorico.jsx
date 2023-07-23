import React from 'react';
import style from "./CardHistorico.module.css";
import moment from 'moment';
import img_cafe_manha from "../../assets/img/img-cafe-da-manha.svg";
import img_almoco from "../../assets/img/img-almoco.svg";
import img_jantar from "../../assets/img/img-janta.svg";
import img_calendario from "../../assets/icons/icon-calendario.svg";

export default function CardHistorico(props) {

    function receberImagem(periodoDiario){
        if(periodoDiario && periodoDiario.toLowerCase() === "manhã"){
            return img_cafe_manha;
        } else if(periodoDiario && periodoDiario.toLowerCase() === "tarde"){
            return img_almoco;
        } else if(periodoDiario && periodoDiario.toLowerCase() === "noite"){
            return img_jantar;
        }
    }

    function verificarDataHora(dataHora){
        if (dataHora) {
          return formattedDateTime;
        } else {
          return "Refeição não consumida"
        }
    }

    function verificarPeriodo(periodo) {
        if (periodo.toLowerCase() === "manhã") {
            return "Café da manhã";
        } else if (periodo.toLowerCase() === "tarde") {
            return "Almoço";
        } else if (periodo.toLowerCase() === "noite") {
            return "Jantar";
        }
    }

    function formatarAlimentos(alimentos) {
        if (alimentos) {
          return alimentos.replace(/,/g, " | ");
        }
        return "";
    }

    const formattedDateTime = moment(props.dataHora).format('DD/MM/YYYY - HH:mm');

    return (
        <>
            <div className={style.div_geral}>
                <div className={style.main_user_home_screen}>
                    <div className={style.main_card_user_home}>
                        <div className={style.img_alimento}>
                            <img src={receberImagem(props.periodo)} alt="img_main" />
                        </div>
                        <div className={style.title_main_card}>
                            <div className={style.title_meal}>
                                <p id={style.title}>{verificarPeriodo(props.periodo)}</p>
                                <p id={style.sub_title}>{formatarAlimentos(props.alimentos)}</p>
                            </div>
                            <div className={style.button_consumption}>
                                <img src={img_calendario} alt="img_calendario" />
                                <p>{verificarDataHora(props.dataHora)}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </>
  )
}
