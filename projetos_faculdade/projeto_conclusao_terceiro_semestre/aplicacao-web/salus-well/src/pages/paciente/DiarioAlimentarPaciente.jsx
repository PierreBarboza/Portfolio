import React from 'react'
import { useLocation } from 'react-router'
import SideBar from '../../components/side_bar/PacienteSideBar'
import DiarioAlimentarPacienteScreen from '../../components/component_screen/paciente/DiarioAlimentarPacienteScreen';

export default function DiarioAlimentarPaciente() {
  const locationURL = useLocation();
  console.log(locationURL);

  return (
    <>
      <body className='body_home'>
        <SideBar />
        <DiarioAlimentarPacienteScreen />
      </body>
    </>
  )
}
