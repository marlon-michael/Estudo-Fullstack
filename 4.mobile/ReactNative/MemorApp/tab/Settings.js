import { useContext } from "react"
import { StyleSheet, View } from "react-native"
import Button from "../component/primal/Button"
import Title from "../component/primal/text/Title"
import Context from "../hook/Context"



export default function Settings() {
  const style = styles()
  const app = useContext(Context)


  toggleDarkMode = () => {
    app.darkmode = !app.darkmode
    app.update()
  }

  return (
    <View style={style.container}>
      <Title> {app.theme} </Title>
      <Button onPress={() => toggleDarkMode()}>toggle darkmode</Button>
    </View>
  )
}



function styles() {
  const app = useContext(Context)
  return StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: app.darkmode ? app.primaryBackgroundDarkColor : app.primaryBackgroundLightColor,
      alignItems: 'center',
      justifyContent: 'center',
    }
  });
}