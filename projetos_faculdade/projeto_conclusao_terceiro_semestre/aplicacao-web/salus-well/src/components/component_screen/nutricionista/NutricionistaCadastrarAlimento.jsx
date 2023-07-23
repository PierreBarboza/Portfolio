import React, { useState } from "react";
import style from './NutricionistaCadastrarAlimento.module.css';
import Header from '../../header/Header';
import Sweet from "../../modal/SweetAlert";

import iconAlimento from '../../../assets/icons/comida_enlatada.png';
import iconComorbidade from '../../../assets/icons/comorbidade.png';
import iconValorNutricional from '../../../assets/icons/valor_nutricional.png';
import iconNomeAlimento from '../../../assets/icons/icon-frutas.svg';
import iconTipoAlimento from '../../../assets/icons/tipo_alimento.png';
import { useNavigate } from "react-router-dom";
import axios from "axios";
import urlBase from '../../../Api';

export default function NutricionistaCadastrarAlimento() {

    const navigate = useNavigate();

    const [nomeAlimento, setNomeAlimento] = useState('');
    const [tipoAlimento, setTipoAlimento] = useState('');
    const [diabetico, setDiabetico] = useState(false);
    const [colesterol, setColesterol] = useState(false);
    const [hipertensao, setHipertensao] = useState(false);
    const [proteina, setProteina] = useState(1);
    const [carboidrato, setCarboidrato] = useState(1);
    const [gorduraTotal, setGorduraTotal] = useState(1);
    const [error, setError] = useState('');

    function submit(event) {
        event.preventDefault();

        const dataItem = {
            "nome": nomeAlimento,
            "tipo": tipoAlimento,
            "diabete": diabetico,
            "colesterol": colesterol,
            "hipertensao": hipertensao,
            "proteina": proteina,
            "carboidrato": carboidrato,
            "gorduraTotal": gorduraTotal,
        }

        axios.post(urlBase + 'alimentos', dataItem)
            .then(response => {
                setError('');
                Sweet('success', 'Alimento cadastrado com sucesso!', '', 3500)
                setTimeout(() => {
                    navigate(-1);
                }, 3500);
                console.log(response.data);
            })
            .catch(error => {
                setError(`Erro ao tentar cadastrar, alimento ${nomeAlimento.toLowerCase()} já existe!`)
                console.error(error);
            });
    }

    const handleCheckboxChange = (checkboxName) => {
        if (checkboxName === 'diabetico') {
            setDiabetico(!diabetico);
            console.log('diabets: ', diabetico)
        } else if (checkboxName === 'colesterol') {
            setColesterol(!colesterol);
            console.log('colest: ', colesterol);
        } else if (checkboxName === 'hipertensao') {
            setHipertensao(!hipertensao);
            console.log('hiper: ', hipertensao);
        }
    };

    return (
        <>
            <div className={style.main_user_home_screen}>
                <Header titulo="Alimentos" subtitulo="Cadastre um novo alimento" />
                <section className={style.formulario}>
                    <form className={style.conteudo} onSubmit={submit}>
                        <div className={style.title}>
                            <img src={iconAlimento} />
                            <p>Alimento</p>
                        </div>
                        <div className="input_wrapper">
                            <p>Nome</p>
                            <input
                                type="text"
                                id="nome"
                                value={nomeAlimento}
                                onChange={event => setNomeAlimento(event.target.value)}
                                required
                            />
                            <img src={iconNomeAlimento} alt="Ícone" />
                        </div>
                        <div class="input_wrapper">
                            <p>Tipo</p>
                            <select
                                id="tipo"
                                value={tipoAlimento}
                                onChange={event => setTipoAlimento(event.target.value)}
                                required
                            >
                                <option value="" disabled selected>* Selecione *</option>
                                <option value="Carne bovina">Carne bovina</option>
                                <option value="Frango">Frango</option>
                                <option value="Peixe">Peixe</option>
                                <option value="Fruta">Fruta</option>
                                <option value="Verdura">Verdura</option>
                                <option value="Legume">Legume</option>
                                <option value="Grão">Grão</option>
                            </select>
                            <img src={iconTipoAlimento} alt="Ícone" />
                        </div>
                        <div className={style.title}>
                            <img src={iconComorbidade} />
                            <p>Comorbidades</p>
                        </div>
                        <div className={style.group_comorbidades}>
                            <div className={style.group_input_wrapper_half}>
                                <div className="input_wrapper">
                                    <p>Indicado para Diabetico?</p>
                                    <div className={style.group_buttons_checked}>
                                        <div className={style.item_check}>
                                            <input
                                                type="checkbox"
                                                id="myCheckbox"
                                                checked={diabetico}
                                                onChange={() => handleCheckboxChange('diabetico')}
                                            />
                                            <label for="myCheckbox"></label>
                                            <p>Sim</p>
                                        </div>
                                        <div className={style.item_check}>
                                            <input
                                                type="checkbox"
                                                id="myCheckbox2"
                                                checked={!diabetico}
                                                onChange={() => handleCheckboxChange('diabetico')}
                                            />
                                            <label for="myCheckbox2"></label>
                                            <p>Não</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={style.group_input_wrapper_half}>
                                <div className="input_wrapper">
                                    <p>Indicado para Colesterol?</p>
                                    <div className={style.group_buttons_checked}>
                                        <div className={style.item_check}>
                                            <input
                                                type="checkbox"
                                                id="myCheckbox3"
                                                checked={colesterol}
                                                onChange={() => handleCheckboxChange('colesterol')}
                                            />
                                            <label for="myCheckbox3"></label>
                                            <p>Sim</p>
                                        </div>
                                        <div className={style.item_check}>
                                            <input
                                                type="checkbox"
                                                id="myCheckbox4"
                                                checked={!colesterol}
                                                onChange={() => handleCheckboxChange('colesterol')}
                                            />
                                            <label for="myCheckbox4"></label>
                                            <p>Não</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={style.group_input_wrapper_half}>
                                <div className="input_wrapper">
                                    <p>Indicado para Hipertenção?</p>
                                    <div className={style.group_buttons_checked}>
                                        <div className={style.item_check}>
                                            <input
                                                type="checkbox"
                                                id="myCheckbox5"
                                                checked={hipertensao}
                                                onChange={() => handleCheckboxChange('hipertensao')}
                                            />
                                            <label for="myCheckbox5"></label>
                                            <p>Sim</p>
                                        </div>
                                        <div className={style.item_check}>
                                            <input
                                                type="checkbox"
                                                id="myCheckbox6"
                                                checked={!hipertensao}
                                                onChange={() => handleCheckboxChange('hipertensao')}
                                            />
                                            <label for="myCheckbox6"></label>
                                            <p>Não</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={style.title}>
                            <img src={iconValorNutricional} />
                            <p>Valores nutricionais</p>
                        </div>
                        <div className={style.group_valor_nutricional}>
                            <div className={style.input_valor_nutricional}>
                                <p>Carboidrato</p>
                                <input
                                    id="carboidrato"
                                    type="number"
                                    min={1}
                                    placeholder="g"
                                    value={carboidrato}
                                    onChange={event => setCarboidrato(event.target.value)}
                                    required />
                            </div>
                            <div className={style.input_valor_nutricional}>
                                <p>Proteínas</p>
                                <input
                                    id="proteinas"
                                    type="number"
                                    min={1}
                                    placeholder="g"
                                    value={proteina}
                                    onChange={event => setProteina(event.target.value)}
                                    required />
                            </div>
                            <div className={style.input_valor_nutricional}>
                                <p>Gorduras totais</p>
                                <input
                                    id="gordurasTotais"
                                    type="number"
                                    min={1}
                                    placeholder="g"
                                    value={gorduraTotal}
                                    onChange={event => setGorduraTotal(event.target.value)}
                                    required />
                            </div>
                        </div>
                        <div className={style.error}>
                            <p>{error}</p>
                        </div>
                        <div className={style.group_buttons_cadastrar}>
                            <button type="submit" className="button_red">Cadastrar alimento</button>
                            <button className="button_white" onClick={() => navigate(-1)}>Voltar</button>
                        </div>
                    </form>
                </section>
            </div>
        </>
    );
}