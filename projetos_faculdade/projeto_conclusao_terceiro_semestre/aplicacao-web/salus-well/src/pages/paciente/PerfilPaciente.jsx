import React from 'react'
import SideBar from '../../components/side_bar/PacienteSideBar'
import PerfilScreen from '../../components/component_screen/paciente/PacientePerfil'

export default function PerfilPaciente() {
  return (
    <>
      <body className='body_home'>
        <SideBar />
        <PerfilScreen />
      </body>
    </>
  )
}
