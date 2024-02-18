import { useContext, useState } from "react"
import { Dimensions, ScrollView, StyleSheet, View } from "react-native"
import Title from "../component/root/text/Title"
import applicationContext from "../hook/context/applicationContext"
import themeContext from "../hook/context/themeContext"



export default function Home() {
  const style = getStyle()
  const app = useContext(applicationContext)

  return (
    <View style={style.container}>
      <Title>home</Title>
    </View>
  )
}



function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: theme.darkmode ? theme.backgroundDarkColor : theme.backgroundLightColor,
      alignItems: 'center',
      justifyContent: 'center',
    }
  })
}