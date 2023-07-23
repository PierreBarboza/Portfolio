import React, { useEffect, useState } from 'react';
import style from './HistoricoScreen.module.css';
import Header from '../../header/Header';
import CardHistorico from '../../cards/CardHistorico';
import axios from 'axios';
import urlBase from '../../../Api';

import loading_gif from '../../../assets/icons/loading.gif';

export default function HistoricoScreen() {
  const [diario, setDiario] = useState([]);
  const [filtro, setFiltro] = useState('todos');
  const [isLoading, setIsLoading] = useState(true);
  const idUsuario = sessionStorage.getItem('idUser');

  useEffect(() => {
    axios
      .get(urlBase + `diarios/historico?idCliente=${idUsuario}`)
      .then(response => {
        try {
          setIsLoading(true);
          setDiario(response.data);
          setIsLoading(false);
        } catch (error) {
          console.error(error);
        }
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  const handleFiltroChange = event => {
    setFiltro(event.target.value);
  };

  const filteredDiario =
    filtro === 'todos' ? diario : diario.filter(diarioPaciente => diarioPaciente.periodo.toLowerCase() === filtro.toLowerCase());
  console.log(filtro);
  console.log(filteredDiario);

  return (
    <>
      <div className={style.main_page}>
        <Header titulo="Histórico" subtitulo="Verifique o histórico do seu diário alimentar" />
        <div className={style.filtro}>
          <h3>Alimentos consumidos</h3>
          <div className={style.botao_filtro}>
            <p>Filtro:</p>
            <select name="filtro" id={style.combo_box} value={filtro} onChange={handleFiltroChange}>
              <option value="todos">Todos</option>
              <option value="manhã">Café da manhã</option>
              <option value="tarde">Almoço</option>
              <option value="noite">Jantar</option>
            </select>
          </div>
        </div>

        <div className={style.group_card}>
          {
            isLoading ? (
              <img src={loading_gif}
                alt="loading"
                style={{ alignSelf: "center", position: "relative", left: "40%", top: "35%", width: "12%" }}
              />
            ) :
              filteredDiario.length > 0 ? (
                filteredDiario.map(diarioPaciente => (
                  <CardHistorico
                    key={diarioPaciente.id}
                    periodo={diarioPaciente.periodo}
                    alimentos={diarioPaciente.alimentos}
                    dataHora={diarioPaciente.dataConsumida}
                  />
                ))
              ) : (
                <></>
              )}

        </div>
      </div>
    </>
  );
}
