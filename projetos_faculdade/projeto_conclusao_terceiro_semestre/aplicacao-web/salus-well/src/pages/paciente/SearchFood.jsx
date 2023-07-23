import React from "react";
import SideBar from '../../components/side_bar/PacienteSideBar'
import PacienteSearchFood from "../../components/component_screen/paciente/PacienteSearchFood";

export default function SearchFood() {
    return (
        <>
            <body className='body_home'>
                <SideBar />
                <PacienteSearchFood />
            </body>
        </>
    )
}