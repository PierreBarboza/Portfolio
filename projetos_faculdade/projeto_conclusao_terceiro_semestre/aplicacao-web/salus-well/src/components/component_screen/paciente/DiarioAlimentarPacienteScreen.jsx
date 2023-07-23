import React, { useEffect, useState } from 'react'
import style from "./DiarioAlimentarPacienteScreen.module.css";
import CardDiarioAlimentarPaciente from "../../cards/CardDiarioAlimentarPaciente"
import Header from "../../header/Header";
import axios from 'axios';
import urlBase from '../../../Api';
import modalSweetConfirm from '../../modal/SweetAlertConfirm';
import modal from '../../modal/SweetAlert';

import loading_gif from '../../../assets/icons/loading.gif';

export default function DiarioAlimentarPacienteScreen() {

  const currentDate = new Date();
  const year = currentDate.getFullYear();
  const month = String(currentDate.getMonth() + 1).padStart(2, '0');
  const day = String(currentDate.getDate()).padStart(2, '0');
  const formattedDate = `${year}-${month}-${day}`;

  const idUsuario = sessionStorage.getItem('idUser');
  const [diarioAtual, setDiarioAtual] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  function confirmarDesfazerUltimoAlimento() {
    modalSweetConfirm('question', false, 'Tem certeza que deseja desfazer o último alimento?')
      .then((result) => {
        if (result) {
          // Lógica a ser executada quando o usuário confirmar
          axios.put(urlBase + "diarios/desfazer-consumido")
            .then(response => {
              console.log(response.data)
              modal('success', 'Alimento retornado para seu diário alimentar.', false, 2500)
              setTimeout(() => {
                window.location.reload(true);
              }, 2500);
            })
            .catch(error => {
              console.log("Erro " + error);
              modal('error', 'OPS!', 'Você não tem mais refeições a reverter.', 2500)
            });

        } else {
          // Lógica a ser executada quando o usuário negar
        }
      })
      .catch((error) => {
        
        console.log('Erro:', error);
      });
  }

  useEffect(() => {
    const fetchAlimentos = async () => {
      try {
        setIsLoading(true);
        let response;

        response = await axios.get(urlBase + `diarios/listar-consumir?id=${idUsuario}&data=${formattedDate}`);

        setDiarioAtual(response.data);
        setIsLoading(false);
        console.log(response.data);
      } catch (error) {
        console.log(error);
      }
    }
    fetchAlimentos();
  }, []);

  return (
    <>
      <div className={style.main_user_home_screen}>
        <Header titulo={"Diário alimentar"} subtitulo="Verifique suas refeições para consumir" />
        <button onClick={confirmarDesfazerUltimoAlimento} className='button_red'>Desfazer último consumido.</button>

        <div className={style.group_card}>
          {
            isLoading ? (
              <img src={loading_gif}
                alt="loading"
                style={{ alignSelf: "center", position: "relative", left: "40%", top: "35%", width: "12%" }}
              />
            ) :
              diarioAtual.length > 0 ? (
                diarioAtual.map((refeicao) => (
                  <CardDiarioAlimentarPaciente
                    alimentos={refeicao.alimentos}
                    periodo={refeicao.periodo}
                    dataConsumir={refeicao.dataConsumir}
                    descricao={refeicao.descricao}
                    idDiario={refeicao.id}
                    hasConsumido={refeicao.consumido}
                  />
                ))
              ) : (<></>)
          }
        </div>
      </div>
    </>
  )
}