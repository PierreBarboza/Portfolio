import React from 'react'
import SideBar from '../../components/side_bar/NutricionistaSideBar'
import ScreenPage from '../../components/component_screen/nutricionista/ListaClientesScreen';

export default function listaClientes() {
  return (
    <>
      <body className='body_home'>
        <SideBar />
        <ScreenPage />
      </body>
    </>
  )
}