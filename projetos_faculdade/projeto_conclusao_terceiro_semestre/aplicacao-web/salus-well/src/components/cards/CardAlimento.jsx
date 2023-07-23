import React, { useState, useEffect } from "react";
import axios from "axios";
import style from './CardAlimento.module.css';

//ICONS
import icon_checked from '../../assets/icons/valid-icon.png'
import icon_unchecked from '../../assets/icons/invalid-icon.png'
import loading_gif from '../../assets/icons/loading.gif';

// FOTOS DOS ALIMENTOS
import fotoDefault from '../../assets/alimentos/default.jpg';
//GRÃOS
import fotoArroz from '../../assets/alimentos/arroz.png';
import fotoFeijao from '../../assets/alimentos/feijao.png';
import fotoLentilha from '../../assets/alimentos/lentilha.jpg';
//FRUTAS/LEGUMES/VERDURAS
import fotoBanana from '../../assets/alimentos/banana.png';
import fotoMaca from '../../assets/alimentos/maca.png';
import fotoMamao from '../../assets/alimentos/mamao.png';
import fotoBrocolis from '../../assets/alimentos/brocolis.jpg';
import fotoChuchu from '../../assets/alimentos/chuchu.jpg';
import fotoPepino from '../../assets/alimentos/pepino.jpg';
import fotoAlface from '../../assets/alimentos/alface.jpg';
//CARNES
import fotoFileFrango from '../../assets/alimentos/fileDeFrango.png';
import fotoPicanha from '../../assets/alimentos/picanha.png';
import fotoContraFile from '../../assets/alimentos/contra_file.jpg';
import fotoBifeVazio from '../../assets/alimentos/bife_do_vazio.jpg';
import fotoSalmao from '../../assets/alimentos/salmao.jpg';
import fotoFrango from '../../assets/alimentos/frango.jpg';
import { useLocation } from "react-router-dom";

//UTILIZADO PARA FAZER REQUISÃO LOCAL/REMOTO
import urlBase from '../../Api';

function AlimentoItem({ alimento, funcaoAlimento }) {
    const location = useLocation();
    const currentURL = location.pathname;

    const [getClicado, setClicado] = useState(true);
    const [contador, setContador] = useState(0);

    const perfilDoCliente = sessionStorage.getItem('keyPerfil');

    const click = () => {
        if (!getClicado) {
            setContador(contador + 1);
        } else {
            setContador(contador - 1);
        }
        setClicado(!getClicado);
    };

    function receberImagem(alimento) {
        //GRÃOS
        if (alimento.nome.toLowerCase() === 'arroz') {
            return fotoArroz;
        } else if (alimento.nome.toLowerCase() === 'feijão') {
            return fotoFeijao;
        } else if (alimento.nome.toLowerCase() === 'lentilha') {
            return fotoLentilha;
        }

        //FRUTAS/VERDURAS/LEGUMES
        if (alimento.nome.toLowerCase() === 'banana') {
            return fotoBanana;
        } else if (alimento.nome.toLowerCase() === 'maçã') {
            return fotoMaca;
        } else if (alimento.nome.toLowerCase() === 'mamão') {
            return fotoMamao;
        } else if (alimento.nome.toLowerCase() === 'alface') {
            return fotoAlface;
        } else if (alimento.nome.toLowerCase() === 'pepino') {
            return fotoPepino;
        } else if (alimento.nome.toLowerCase() === 'brocólis') {
            return fotoBrocolis;
        } else if (alimento.nome.toLowerCase() === 'chuchu') {
            return fotoChuchu;
        } 

        //CARNES
        if (alimento.nome.toLowerCase() === 'filé de frango') {
            return fotoFileFrango;
        } else if (alimento.nome.toLowerCase() === 'picanha') {
            return fotoPicanha;
        } else if (alimento.nome.toLowerCase() === 'contra filé') {
            return fotoContraFile;
        } else if (alimento.nome.toLowerCase() === 'salmão') {
            return fotoSalmao;
        } else if (alimento.nome.toLowerCase() === 'frango') {
            return fotoFrango;
        } else if (alimento.nome.toLowerCase() === 'bife do vazio') {
            return fotoBifeVazio;
        } 
        
        else {
            return fotoDefault;
        }
    }

    return (
        <div
            key={alimento.id}
            className={getClicado === true ? style.card : style.card_selecionado}
            onClick={perfilDoCliente !== 'Nutricionista' ? null : () => {
                if (currentURL !== '/listarAlimentos') {
                    funcaoAlimento(alimento, getClicado);
                    click();
                }
            }}>
            <div className={style.alimento} style={{ backgroundImage: `url(${receberImagem(alimento)})` }}>
                <p className={style.title}>{((alimento.nome).toLowerCase()).replace(/^[a-z]/, match => match.toUpperCase())}</p>
                <p>{((alimento.tipo).toLowerCase()).replace(/^[a-z]/, match => match.toUpperCase())}</p>
            </div>

            <div className={style.propriedades}>
                <p><b>Carboidratos: </b>{(alimento.carboidrato).toLocaleString('pt-BR')}g</p>
                <p><b>Proteínas: </b>{(alimento.proteina).toLocaleString('pt-BR')}g</p>
                <p><b>Gorduras totais: </b>{(alimento.gorduraTotal).toLocaleString('pt-BR')}g</p>
                <p><b>Calorias: </b>{(alimento.calorias).toLocaleString('pt-BR')}</p>
            </div>
            <div className={style.comorbidade}>
                <p>Indicado para diabetes:
                    {alimento.diabete === true ? (<img src={icon_checked} />) : (<img src={icon_unchecked} />)}
                </p>
                <p>Indicado para colesterol:
                    {alimento.colesterol === true ? (<img src={icon_checked} />) : (<img src={icon_unchecked} />)}
                </p>
                <p> Indicado para hipertensão:
                    {alimento.hipertensao === true ? (<img src={icon_checked} />) : (<img src={icon_unchecked} />)}
                </p>
            </div>
        </div>
    );
}

export default function CardAlimento(props) {
    const [getAlimento, setAlimento] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [tipoComorbidade, setTipoComorbidade] = useState('');
    const [clickLupa, setClickLupa] = useState(false);
    const [erro, setErro] = useState("");

    useEffect(() => {
        const fetchAlimentos = async () => {
            try {
                setIsLoading(true);
                let response;

                if (tipoComorbidade === "hipertensao") {
                    response = await axios.get(urlBase + "alimentos/hipertensao");
                } else if (tipoComorbidade === "diabete") {
                    response = await axios.get(urlBase + "alimentos/diabete");
                } else if (tipoComorbidade === "colesterol") {
                    response = await axios.get(urlBase + "alimentos/colesterol");
                } else {
                    response = await axios.get(urlBase + "alimentos");
                }

                setAlimento(response.data);
                setIsLoading(false);
            } catch (error) {
                console.log(error);
            }
        }
        fetchAlimentos();
    }, [tipoComorbidade]);

    useEffect(() => {
        const fetchAlimentos = async () => {
            try {
                setIsLoading(true);
                setErro('');
                let response;
                let urlResultado;

                if (props.nomeAlimento === '') {
                    response = await axios.get(urlBase + "alimentos");
                    urlResultado = true;
                }
                else if (clickLupa === false) {
                    response = await axios.get(urlBase + "alimentos");
                    urlResultado = true;
                }
                else {
                    response = await axios.get(urlBase + `alimentos/nome-alimento/${props.nomeAlimento}`)
                    urlResultado = false;
                }

                setAlimento(urlResultado ? response.data : [response.data]);
                setIsLoading(false);
            } catch (error) {
                console.log(error);
                setIsLoading(false);
                setErro("Alimento " + props.nomeAlimento + " não encontrado!")
            }
        };

        fetchAlimentos();
    }, [clickLupa]);

    useEffect(() => {
        setClickLupa(true);
    }, [props.nomeAlimento])

    useEffect(() => {
        if (props.filtroBuscaAlimento.length <= 0) {
            setClickLupa(false);
        }
    }, [props.filtroBuscaAlimento])

    useEffect(() => {
        setTipoComorbidade(props.tipoComorbidade);
    }, [props.tipoComorbidade]);

    return (
        <>
            {
                isLoading ? (
                    <img src={loading_gif}
                        alt="loading"
                        style={{ alignSelf: "center", position: "relative", left: "40%", width: "12%" }}
                    />
                ) :
                    erro.length > 0 ?
                        <p style={{ fontSize: "x-large", fontWeight: "bold", color: "var(--red)" }}>{erro}</p>
                        :
                        getAlimento.length > 0 ?
                            getAlimento.map(alimento => (
                                <AlimentoItem
                                    key={alimento.id}
                                    alimento={alimento}
                                    funcaoAlimento={props.funcaoAlimento}
                                    tipoComorbidade={props.tipoComorbidade}
                                />)) :
                            (<></>)
            }
        </>
    );
}

