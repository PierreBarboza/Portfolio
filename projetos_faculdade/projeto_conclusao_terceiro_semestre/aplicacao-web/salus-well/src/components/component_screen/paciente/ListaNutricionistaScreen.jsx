import React, { useEffect, useState } from 'react';
import style from "./ListaNutricionistaScreen.module.css";
import CardNutricionista from "../../cards/CardNutricionista";
import Header from "../../header/Header";
import lupaIcon from "../../../assets/icons/lupa.png";
import axios from 'axios';
import urlBase from '../../../Api';
import loading_gif from '../../../assets/icons/loading.gif';

export default function ListaNutricionistaScreen() {
  const [listaNutricionistas, setListaNutricionistas] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [hasNutricionista, setHasNutricionista] = useState(0);
  const [possuiNutricionista, setPossuiNutricionista] = useState(false);
  const [nomeNutricionista, setNomeNutricionista] = useState('');

  function buscarNutricionista() {
    const fetchNutricionistas = async () => {
      try {
        setIsLoading(true);

        const response = await axios.get(urlBase + `nutricionistas/search/${nomeNutricionista}`);
        setListaNutricionistas(response.data);

        setIsLoading(false);
      } catch (error) {
        setIsLoading(false);
      }
    };

    fetchNutricionistas();
  }

  useEffect(() => {
    const fetchNutricionista = async () => {
      try {
        setIsLoading(true);

        if (hasNutricionista === 0) {
          const response = await axios.get(urlBase + `clientes/busca-unico-nutricionista?idCliente=${sessionStorage.getItem('idUser')}`);
          setHasNutricionista(1);
          setPossuiNutricionista(true);
          setListaNutricionistas([response.data]);
        } else if (hasNutricionista === 2) {
          const response = await axios.get(urlBase + `nutricionistas`);
          setHasNutricionista(1);
          setPossuiNutricionista(false);
          setListaNutricionistas(response.data);
        }

        setIsLoading(false);
      } catch (error) {
        setHasNutricionista(2);
        setPossuiNutricionista(false);
        console.log(error);
        setIsLoading(false);
      }
    };

    fetchNutricionista();
  }, [hasNutricionista]);

  useEffect(() => {
    if (nomeNutricionista.length <= 0) {
      setHasNutricionista(2);
    }
  }, [nomeNutricionista]);

  return (
    <>
      <div className={style.main_user_home_screen}>
        <Header titulo={"Nutricionistas"} subtitulo="Procure um nutricionista" />
        <div className={style.areaSearch}>
          {possuiNutricionista ? (
            <></>
          ) : (
            <span className={style.inputSearch}>
              <span>
                <input
                  id='search'
                  placeholder='Busque pelo nome'
                  value={nomeNutricionista}
                  onChange={(event) => setNomeNutricionista(event.target.value)}
                  type="text"
                  style={{ border: 'none' }}
                />
              </span>
              <span>
                <img src={lupaIcon}
                  onClick={buscarNutricionista}
                  style={{ width: '20px' }}
                />
              </span>
            </span>
          )}
        </div>
        <div className={style.group_card}>
          {isLoading ? (
            <img
              src={loading_gif}
              alt="loading"
              style={{ alignSelf: "center", position: "fixed", left: "50%", bottom: "35%", width: "10%" }}
            />
          ) : (
            listaNutricionistas.length > 0 ? (
              listaNutricionistas.map((nutricionista) => (
                <CardNutricionista
                  key={nutricionista.id}
                  id={nutricionista.id}
                  nome={nutricionista.nome}
                  telefone={nutricionista.telefone}
                  avatar={nutricionista.avatar}
                  vinculado={possuiNutricionista}
                />
              ))
            ) : (
              <p style={{ fontSize: "large", color: "var(--red)", fontWeight: "bold" }}>Ops! Nenhum nutricionista encontrado.</p>
            )
          )}
        </div>
      </div>
    </>
  );
}
