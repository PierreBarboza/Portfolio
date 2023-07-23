import axios from 'axios';
import React, { useState } from 'react';
import style from "./CardDiarioAlimentarPaciente.module.css";
import modal from '../modal/SweetAlert';

import cafe_da_manha from '../../assets/alimentos/pratos/cafe_da_manha.png';
import almoco from '../../assets/alimentos/pratos/almoco.jpg';
import jantar from '../../assets/alimentos/pratos/jantar.png';
import alimento_default from '../../assets/alimentos/pratos/default.png';

import alimento_consumido from '../../assets/alimentos/pratos/refeicao-concluida.png';

import urlBase from '../../Api';

export default function CardDiarioAlimentarPaciente(props) {

  const idUsuario = sessionStorage.getItem('idUser');

  function consumirRefeicao() {
    axios.put(urlBase + `diarios/consumido?idCliente=${idUsuario}&idDiario=${props.idDiario}`)
      .then(response => {
        console.log(response.data)
        modal('success', 'Alimento consumido com sucesso.', false, 2500)
        setTimeout(() => {
          window.location.reload(true);
        }, 2500);
      })
      .catch(error => {
        console.log("Erro " + error);
      });
  }

  function imagemCard(value) {
    if (value && value === "Manhã") {
      return cafe_da_manha;
    } else if (value && value === "Tarde") {
      return almoco;
    } else if (value && value === "Noite") {
      return jantar;
    } else {
      return alimento_default;
    }
  }

  const [descricaoButton, setDescricaoButton] = useState(true);

  const handleDescricao = () => {
    setDescricaoButton(!descricaoButton);
    console.log(descricaoButton)
  };

  return (
    <>
      <div className={style.div_geral}>
        <div className={style.main_user_home_screen}>
          <div className={style.main_card_user_home}>
            <div className={style.img_alimento}>
              {
                props.hasConsumido ?
                  <img src={alimento_consumido} alt="img_main" />
                  :
                  <img src={imagemCard(props.periodo)} alt="img_main" />
              }
            </div>
            <div className={style.title_main_card}>
              <div className={style.title_meal}>
                <p id={style.title}>
                  {props.periodo === "Manhã" ? 'Café da manhã' : null}
                  {props.periodo === "Tarde" ? 'Almoço' : null}
                  {props.periodo === "Noite" ? 'Jantar' : null}
                </p>
                {
                  descricaoButton ?
                    <p style={{ fontSize: "large" }} id={style.sub_title}>{props.alimentos}</p>
                    :
                    <p style={{ fontSize: "large" }} id={style.sub_title}>{props.descricao}</p>
                }
              </div>

              <div className={style.group_button}>

                <div className={style.button_consumo}>
                  {
                    props.hasConsumido ?
                      null :
                      <button className="button_red" onClick={consumirRefeicao}>Consumido</button>
                  }
                  <button
                    className={descricaoButton ? 'button_white' : 'button_red'}
                    onClick={handleDescricao}>
                    {descricaoButton ? 'Detalhes' : 'Ocultar detalhes'}
                  </button>
                </div>
              </div>
            </div>
            <div className={style.img_main_card}></div>
          </div>
        </div>
      </div>
    </>
  )
}
