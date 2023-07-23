/* eslint-disable jsx-a11y/anchor-is-valid */
/* eslint-disable jsx-a11y/anchor-has-content */
import React, { useEffect, useState } from 'react'
import style from "./ListaClientesScreen.module.css";
import axios from 'axios';
import urlBase from '../../../Api';

import Header from "../../header/Header";
import CardCliente from "../../cards/CardCliente";

import lupaIcon from "../../../assets/icons/lupa.png";
import loading_gif from '../../../assets/icons/loading.gif';

import Sweet from '../../modal/SweetAlert'
export default function ListaClienteScreen() {

  // var buscaNome = '';
  const [listaClientes, setListaClientes] = useState([])
  const [isLoading, setIsLoading] = useState(true);
  const [procurarCliente, setProcurarCliente] = useState([]);
  const [alternarBotaoPaciente, setAlternarBotaoPaciente] = useState(false);
  const [verificarFiltro, setVerficarFiltro] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchPacientes = async () => {
      try {
        let response;
        setIsLoading(true);

        response = await axios.get(urlBase + `comorbidades/todos-de-um-nutricionista?idNutricionista=${sessionStorage.getItem('idUser')}`);
        setListaClientes(response.data);
        setVerficarFiltro(true)

        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    }
    fetchPacientes();
  }, []);

  function procurarPaciente() {
    const fetchPacientes = async () => {
      try {
        let response;
        setIsLoading(true);
        setError('');

        response = await axios.get(urlBase + `clientes/busca-por-nome?nome=${procurarCliente}`);
        setListaClientes(response.data);

        setIsLoading(false);
      } catch (error) {
        setIsLoading(false);
        setError("Ops! " + procurarCliente + " não foi encontrado.");
      }
    }
    fetchPacientes();
  }

  const downloadFile = () => {
    axios({
      url: urlBase + `clientes/download`,
      method: 'GET',
      responseType: 'blob', // Indica que a resposta será um objeto Blob
    })
      .then(response => {
        Sweet('success', 'Aguarde...', 'Seu arquivo está sendo gerado', 4000)
        // Crie um URL temporário para o objeto Blob
        const url = window.URL.createObjectURL(new Blob([response.data]));

        // Crie um link <a> para o URL temporário
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'arquivo.txt');

        // Adicione o link ao DOM e clique nele para iniciar o download
        document.body.appendChild(link);
        link.click();

        // Limpe o URL temporário quando o download for concluído
        link.parentNode.removeChild(link);
      })
      .catch(error => {
        Sweet('warning', '', 'Houve um erro ao gerar o arquivo, tente novamente mais tarde', 5000)
        // Lógica para lidar com erros
        console.error(error);
      });
  }

  return (
    <>
      <div className={style.main_user_home_screen}>
        <Header titulo={"Clientes"} subtitulo="Consulte a lista de clientes" />
        <div className={style.areaSearch}>
          <span className={style.inputSearch}>
            <span>
              <input
                id='buscaNome'
                placeholder='Busque pelo nome'
                value={procurarCliente}
                onChange={event => setProcurarCliente(event.target.value)}
                type="text"
                style={{ border: 'none' }}
              />
            </span>
            <span><img src={lupaIcon} style={{ width: '20px' }} onClick={procurarCliente.length > 0 ? procurarPaciente : null} /></span>
          </span>
          <div className={style.group_buttons}>
            <button className={'button_red'} onClick={downloadFile}>Baixar dados dos pacientes</button>
          </div>
        </div>

        <p style={{ fontWeight: "bolder", color: "var(--black)", fontSize: "large" }}>
          {alternarBotaoPaciente === true ? "Todos pacientes" : "Meus pacientes"}
        </p>

        <div className={style.group_cards}>

          {
            isLoading ? (
              <img src={loading_gif}
                alt="loading"
                style={{ alignSelf: "center", position: "fixed", left: "50%", bottom: "35%", width: "10%" }}
              />
            ) :
              error.length > 1 ? (<><p style={{ fontSize: "x-large", fontWeight: "bold", color: "var(--red)" }}>{error}</p></>) :
                listaClientes.length > 0 ? (
                  verificarFiltro === true ? (
                    listaClientes.map(user => (
                      <CardCliente
                        key={user.cliente.id}
                        nome={user.cliente.nome}
                        telefone={user.cliente.telefone}
                        endereco={user.cliente.endereco}
                        avatar={user.cliente.avatar}
                        diabete={user.diabete}
                        colesterol={user.colesterol}
                        hipertensao={user.hipertensao}
                        filtro={verificarFiltro}
                      />
                    ))
                  ) :
                    listaClientes.map(cliente => (
                      <CardCliente
                        key={cliente.id}
                        nome={cliente.nome}
                        telefone={cliente.telefone}
                        endereco={cliente.endereco}
                        avatar={cliente.avatar}
                        hasComorbidade={cliente.comorbidade}
                        filtro={verificarFiltro}
                      />
                    ))
                ) : (
                  <></>

                )
          }

        </div>
      </div>
    </>
  )
}