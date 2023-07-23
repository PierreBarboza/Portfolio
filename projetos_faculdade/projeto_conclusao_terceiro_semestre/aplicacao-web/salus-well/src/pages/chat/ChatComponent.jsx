//Inicio Chat
import React, { useEffect, useState } from 'react'
import { over } from 'stompjs';
import SockJS from 'sockjs-client';
import { useNavigate } from "react-router-dom";
import retangle from "../../assets/icons/retangle-title.svg"
import Sweet from '../../components/modal/SweetAlert'
import filtrarPalavroes from '../../filterPalavroes';

//Avatares 
import avatarDefault from "../../assets/avatar/avatar-default.svg";

import avatarFemale1 from "../../assets/avatar/menina-avatar-1.png";
import avatarFemale2 from "../../assets/avatar/menina-avatar-2.png";
import avatarFemale3 from "../../assets/avatar/menina-avatar-3.png";
import avatarFemale4 from "../../assets/avatar/menina-avatar-4.png";
import avatarFemale5 from "../../assets/avatar/menina-avatar-5.png";
import avatarFemale6 from "../../assets/avatar/menina-avatar-6.png";
import avatarFemale7 from "../../assets/avatar/menina-avatar-7.png";

import avatarMale1 from "../../assets/avatar/menino-avatar-1.png";
import avatarMale2 from "../../assets/avatar/menino-avatar-2.png";
import avatarMale3 from "../../assets/avatar/menino-avatar-3.png";
import avatarMale4 from "../../assets/avatar/menino-avatar-4.png";
import avatarMale5 from "../../assets/avatar/menino-avatar-5.png";
import avatarMale6 from "../../assets/avatar/menino-avatar-6.png";
import avatarMale7 from "../../assets/avatar/menino-avatar-7.png";


var stompClient = null;

const ChatRoom = () => {

    const navigate = useNavigate()

    let avatares = [
        avatarDefault, avatarFemale1, avatarFemale2, avatarFemale3, avatarFemale4, avatarFemale5, avatarFemale6, avatarFemale7,
        avatarMale1, avatarMale2, avatarMale3, avatarMale4, avatarMale5, avatarMale6, avatarMale7
    ]

    var idAvatar = sessionStorage.getItem('AVATAR_msg')
    var srcAvatar = avatares[idAvatar]


    const [privateChats, setPrivateChats] = useState(new Map());
    const [publicChats, setPublicChats] = useState([]);
    const [tab, setTab] = useState("CHATROOM");
    const [userData, setUserData] = useState({
        username: '',
        receivername: '',
        connected: false,
        message: ''
    });
    useEffect(() => {
        console.log(userData);
    }, [userData]);

    const connect = () => {
        let Sock = new SockJS('http://20.163.242.9:3001/websocket');

        // let Sock = new SockJS('http://localhost:3001/websocket');

        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const onConnected = () => {
        setUserData({ ...userData, "connected": true });
        stompClient.subscribe('/chatroom/public', onMessageReceived);
        stompClient.subscribe('/user/' + userData.username + '/private', onPrivateMessage);
        userJoin();
    }

    const userJoin = () => {
        var chatMessage = {
            senderName: userData.username,
            status: "JOIN"
        };
        stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
    }

    const onMessageReceived = (payload) => {
        var payloadData = JSON.parse(payload.body);
        switch (payloadData.status) {
            case "JOIN":
                if (!privateChats.get(payloadData.senderName)) {
                    privateChats.set(payloadData.senderName, []);
                    setPrivateChats(new Map(privateChats));
                }
                break;
            case "MESSAGE":
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
        }
    }

    const onPrivateMessage = (payload) => {
        console.log(payload);
        var payloadData = JSON.parse(payload.body);
        if (privateChats.get(payloadData.senderName)) {
            privateChats.get(payloadData.senderName).push(payloadData);
            setPrivateChats(new Map(privateChats));
        } else {
            let list = [];
            list.push(payloadData);
            privateChats.set(payloadData.senderName, list);
            setPrivateChats(new Map(privateChats));
        }
    }

    const onError = (err) => {
        console.log(err);

    }

    const handleMessage = (event) => {
        const { value } = event.target;
        setUserData({ ...userData, "message": value });
    }
    const sendValue = () => {
        var mensagemEscrita = document.querySelector('#inputMSG').value
        mensagemEscrita = mensagemEscrita.toLowerCase()

        if (mensagemEscrita === '') {
            Sweet(
                'warning',
                'Atenção',
                'Preencha o campo.',
                2000)
        } else {
            if (filtrarPalavroes(mensagemEscrita)) {
                Sweet(
                    'warning',
                    'Atenção',
                    'Existem palavras melhores para uma conversa',
                    6000,
                )
            } else {

                var datetime = new Date();
                var time = datetime.getHours() + ":" + datetime.getMinutes();
                if (stompClient) {
                    var chatMessage = {
                        senderName: userData.username,
                        message: userData.message,
                        time: time,
                        status: "MESSAGE"
                    };
                    console.log(chatMessage);
                    stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
                    setUserData({ ...userData, "message": "" });
                }
            }
        }

    }

    const sendPrivateValue = () => {
        if (stompClient) {
            var chatMessage = {
                senderName: userData.username,
                receiverName: tab,
                message: userData.message,
                status: "MESSAGE"
            };

            if (userData.username !== tab) {
                privateChats.get(tab).push(chatMessage);
                setPrivateChats(new Map(privateChats));
            }
            stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
            setUserData({ ...userData, "message": "" });
        }
    }

    const handleUsername = (event) => {
        const { value } = event.target;
        setUserData({ ...userData, "username": value });
    }

    // const registerUser = () => {
    //     connect();
    // }

    useEffect(() => {
        connect();
    }, []);

    /////////////////////// HTML //////////////////////////////////////////////////////////////////
    return (
        <>
            <div className="container">
                {userData.connected ?
                    <>
                        <div className='HeaderChat'>
                            <span className='title-chat'>
                                {

                                    sessionStorage.getItem('keyPerfil') === `Nutricionista` ?
                                        `Paciente ${sessionStorage.getItem('NOME_msg')}`
                                        :
                                        `Nutricionista ${sessionStorage.getItem('NOME_msg')}`
                                }
                                <img className='retangle-title' src={retangle} />
                            </span>
                            <button className='button_red' onClick={() => navigate(-1)} style={{ width: '230px' }}> Sair da conversa</button>

                        </div>
                        <img src={srcAvatar} className='avatar-img-area' />

                        <div className="chat-box">
                            <div className="member-list">
                                <ul>
                                    <li onClick={() => { setTab("CHATROOM"); }} className={`member ${tab === "CHATROOM" && "active"}`}>Chatroom</li>
                                    {[...privateChats.keys()].map((name, index) => (
                                        <li onClick={() => { setTab(name); }} className={`member ${tab === name && "active"}`} key={index}>{name}</li>
                                    ))}
                                </ul>
                            </div>
                            {tab === "CHATROOM" && <div className="chat-content">
                                <ul className="chat-messages">
                                    <div className="area-msg">
                                        {publicChats.map((chat, index) => (
                                            <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                                                {chat.senderName !== userData.username && <div className="avatar">{chat.time}</div>}
                                                <div><p className="message-data">{chat.message}</p></div>
                                                {chat.senderName === userData.username && <div className="avatar self">{chat.time}</div>}
                                            </li>
                                        ))}
                                    </div>
                                </ul>
                                <div className='area-send'>
                                    <div className="send-message">

                                        <input id='inputMSG' type="text" className="input-message" placeholder="Envie uma mensagem." value={userData.message} onChange={handleMessage} />

                                        <button type="button" className="send-button" onClick={sendValue}></button>
                                    </div>
                                </div>
                            </div>}
                            {tab !== "CHATROOM" && <div className="chat-content">
                                <ul className="chat-messages">
                                    <div className="area-msg">
                                        {[...privateChats.get(tab)].map((chat, index) => (
                                            <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                                                {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
                                                <div><p className="message-data">{chat.message}</p></div>
                                                {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
                                            </li>
                                        ))}
                                    </div>
                                </ul>

                                <div className="send-message">
                                    <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} />
                                    <button type="button" className="send-button" onClick={sendPrivateValue}></button>
                                </div>
                            </div>}
                        </div></>
                    :
                    <div className="register" style={{ display: 'none' }}>
                        <input
                            id="user-name"
                            placeholder="Digite seu nome"
                            name="userName"
                            value={userData.username = sessionStorage.getItem('NOME_msg')}
                            onChange={handleUsername}
                            margin="normal"
                        />
                    </div>}
            </div>

        </>

    )
}
//Fim Chat
export default ChatRoom
