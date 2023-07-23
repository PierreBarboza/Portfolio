import React from "react";
import { Routes, Route } from 'react-router-dom';
// INDEX
import Index from "./pages/Index";

// PACIENTE
import Historico from "./pages/paciente/Historico";
import CadastroPaciente from './pages/paciente/CadastroPaciente';
import HomePaciente from "./pages/paciente/HomePaciente";
import PerfilPaciente from "./pages/paciente/PerfilPaciente";
import SearchFood from "./pages/paciente/SearchFood"
import ListaNutricionistas from "./pages/paciente/ListaNutricionistas"
import DiarioAlimentarPaciente from "./pages/paciente/DiarioAlimentarPaciente";
// NUTRICIONISTA
import HomeNutricionista from "./pages/nutricionista/HomeNutricionista"
import CadastroNutricionista from './pages/nutricionista/CadastroNutricionista';
import PerfilNutricionista from "./pages/nutricionista/PerfilNutricionista";
import ListaClientes from './pages/nutricionista/ListaClientes';
import ListarAlimentos from './pages/nutricionista/ListarAlimentos';
import SelecionarAlimentos from './pages/nutricionista/SelecionarAlimentos';
import CriarRotinaAlimentar from './pages/nutricionista/CriarRotinaAlimentar';
import CadastrarAlimento from './pages/nutricionista/CadastrarAlimento';
// CHAT
import ChatRoom from "./pages/chat/ChatRoom";
// NOT FOUND(404)
import NotFound from './components/component_screen/NotFound';

function Rotas() {

    return (
        <Routes>
            {/* INDEX */}
            <Route path="/" element={<Index />} />
            {/* PACIENTE */}
            <Route path="/cadastroPaciente" element={<CadastroPaciente />} />
            <Route path="/homePaciente" element={<HomePaciente />} />
            <Route path="/perfilPaciente" element={<PerfilPaciente />} />
            <Route path="/listaNutricionistas" element={<ListaNutricionistas />} />
            <Route path="/diarioAlimentarPaciente" element={<DiarioAlimentarPaciente />} />
            <Route path="/searchFood" element={<SearchFood />} />
            <Route path="/historico" element={<Historico />} />
            {/* NUTRICIONISTA */}
            <Route path="/cadastroNutricionista" element={<CadastroNutricionista />} />
            <Route path="/homeNutricionista" element={<HomeNutricionista />} />
            <Route path="/perfilNutricionista" element={<PerfilNutricionista />} />
            <Route path="/listarAlimentos" element={<ListarAlimentos/>} />
            <Route path="/selecionarAlimentos" element={<SelecionarAlimentos />} />
            <Route path="/criarRotina" element={<CriarRotinaAlimentar />} />
            <Route path="/cadastrarAlimento" element={<CadastrarAlimento />} />
            <Route path="/listaClientes" element={<ListaClientes />} />
            {/* NOT FOUND */}
            <Route path="*" element={<NotFound />} />
            {/* CHAT */}
            <Route path="/Chat" element={<ChatRoom />} />
        </Routes>
    );
}

export default Rotas;
