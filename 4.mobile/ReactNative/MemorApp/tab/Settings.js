import { useContext } from "react"
import { StyleSheet, View } from "react-native"
import Button from "../component/root/Button"
import Title from "../component/root/text/Title"
import Label from "../component/root/text/Label"
import applicationContext from "../hook/context/applicationContext"
import themeContext from "../hook/context/themeContext"
import * as fs from 'expo-file-system'


export default function Settings() {
  const style = getStyle()
  const theme = useContext(themeContext)
  const aplication = useContext(applicationContext)

  toggleDarkMode = async () => {
    theme.darkmode = !theme.darkmode
    await fs.writeAsStringAsync(fs.documentDirectory + 'settings', JSON.stringify(theme))
    aplication.update()
  }

  return (
    <View style={style.container}>
      <Title> {theme.darkmode} </Title>
      <Button onPress={() => toggleDarkMode()}><Label contrast>toggle darkmode</Label></Button>
    </View>
  )
}

function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: theme.darkmode ? theme.backgroundDarkColor : theme.backgroundColor,
      alignItems: 'center',
      justifyContent: 'center',
    }
  });
}