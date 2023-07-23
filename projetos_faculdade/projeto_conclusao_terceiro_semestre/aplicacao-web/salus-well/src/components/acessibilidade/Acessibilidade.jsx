import React, { useEffect, useState } from "react";
import style from './Acessibilidade.module.css';

import icon_acessibilidade from '../../assets/icons/acessibilidade.png';

export default function Acessibilidade(props) {

    const [fontSize, setFontSize] = useState(16);

    function aumentarLetra() {
        if(fontSize < 19) {
            setFontSize(fontSize + 1);
        }
    }

    function diminuirLetra() {
        if(fontSize > 13) {
            setFontSize(fontSize - 1);
        }
    }

    function handleChangeFontSize() {
        if (props.onChangeFontSize) {
          props.onChangeFontSize(fontSize);
        }
      }
    
      // Chame handleChangeFontSize sempre que fontSize for atualizado
      useEffect(() => {
        handleChangeFontSize();
      }, [fontSize]);

    return (
        <>
            <div className={style.group_itens}>
                <div className={style.icon}>
                    <img src={icon_acessibilidade} />
                </div>
                <div className={style.itens_hidden}>
                    <p>Alterar tamanho das letras</p>
                    <div className={style.group_buttons}>
                        <button className="button_red" onClick={diminuirLetra}>- A</button>
                        <button className="button_red" onClick={aumentarLetra}>+ A</button>
                    </div>
                </div>
            </div>
        </>
    );
}