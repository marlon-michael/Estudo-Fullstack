import { useContext, useState } from "react"
import { Dimensions, ScrollView, StyleSheet, View } from "react-native"
import Title from "../component/basic/text/Title"
import Context from "../hook/Context"



export default function Home() {
  const style = styles()
  const app = useContext(Context)

  return (
    <View style={style.container}>
      <Title onPress={() => app.tabHistory.length && app.setTab(app.tabHistory.pop())}>home</Title>
    </View>
  )
}



function styles() {
  const app = useContext(Context)
  return StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: app.darkmode ? app.backgroundDarkColor : app.backgroundLightColor,
      alignItems: 'center',
      justifyContent: 'center',
    }
  })
}