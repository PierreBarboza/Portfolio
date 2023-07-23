import React from 'react'
import SideBar from '../../components/side_bar/NutricionistaSideBar'
import HomeScreen from '../../components/component_screen/nutricionista/NutricionistaHomeScreen'

export default function HomeNutricionista() {
  return (
    <>
      <body className='body_home'>
        <SideBar />
        <HomeScreen />
      </body>
    </>
  )
}
