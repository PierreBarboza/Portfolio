import React from "react";
import style from './CardAlimentoQtd.module.css';

//ICONS
import iconLixeira from '../../assets/icons/lixeira_icon.png';
// FOTOS DOS ALIMENTOS
import fotoDefault from '../../assets/alimentos/default.jpg';
//GRÃOS
import fotoArroz from '../../assets/alimentos/arroz.png';
import fotoFeijao from '../../assets/alimentos/feijao.png';
//FRUTAS/LEGUMES/VERDURAS
import fotoBanana from '../../assets/alimentos/banana.png';
import fotoMaca from '../../assets/alimentos/maca.png';
//CARNES
import fotoFileFrango from '../../assets/alimentos/fileDeFrango.png';
import fotoPicanha from '../../assets/alimentos/picanha.png';

export default function CardAlimentoQtd(props) {

    function receberImagem(nomeAlimento) {
        //GRÃOS
        if (nomeAlimento.toLowerCase() === 'arroz') {
            return fotoArroz;
        } else if (nomeAlimento.toLowerCase() === 'feijão') {
            return fotoFeijao;
        }

        //FRUTAS/VERDURAS/LEGUMES
        if (nomeAlimento.toLowerCase() === 'banana') {
            return fotoBanana;
        } else if (nomeAlimento.toLowerCase() === 'maça') {
            return fotoMaca;
        }

        //CARNES
        if (nomeAlimento.toLowerCase() === 'filé de frango') {
            return fotoFileFrango;
        } else if (nomeAlimento.toLowerCase() === 'picanha') {
            return fotoPicanha;
        } else {
            return fotoDefault;
        }
    }

    const removerRegistro = () => {
        props.remover();
    }

    return (
        <>
            <div className={style.card}>
                <div className={style.alimento}>
                    <img src={receberImagem(props.fotoAlimento)} />
                    <div className={style.group_title}>
                        <p>{props.nomeAlimento}</p>
                        <p>{props.categoriaAlimento}</p>
                    </div>
                </div>
                <img src={iconLixeira} className={style.button_lixeira} onClick={removerRegistro} />
            </div>
        </>
    );
}