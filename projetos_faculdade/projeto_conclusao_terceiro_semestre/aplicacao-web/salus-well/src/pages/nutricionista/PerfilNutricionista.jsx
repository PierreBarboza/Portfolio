import React from 'react'
import SideBar from '../../components/side_bar/NutricionistaSideBar'
import NutricionistaPerfil from '../../components/component_screen/nutricionista/NutricionistaPerfil'
import { useLocation } from 'react-router'

export default function PerfilNutricionista() {
  const locationURL = useLocation();
  console.log(locationURL);

  return (
    <>
      <body className='body_home'>
        <SideBar />
        <NutricionistaPerfil />
      </body>
    </>
  )
}
