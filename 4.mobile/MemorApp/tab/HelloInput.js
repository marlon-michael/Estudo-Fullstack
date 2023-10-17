import { useContext, useState } from "react"
import { StyleSheet, View } from "react-native"
import Title from "../component/basic/text/Title"
import Input from "../component/basic/Input"
import Context from "../hook/Context"
import Button from "../component/basic/Button"
import file from '../hook/File'



export default function HelloInput() {
  const style = styles()
  const [input, setInput] = useState('')

  function saveInput() {
    file.save('input.txt', input)
  }

  async function loadInput() {
    alert('Selecione a pasta onde se encotra o arquivo "input.txt"')
    setTimeout(() => file.load('input.txt').then(content => setInput(content)), 5000)
  }

  return (
    <View style={style.container}>
      <Title> Hello Memo </Title>
      <Input
        onChangeText={setInput}
        value={input}
        placeholder="digite aqui"
      />
      <View style={style.inLine}>
        <Button style={{ height: 40 }} onPress={() => saveInput()}>save</Button>
        <Button style={{ height: 40 }} onPress={() => loadInput()}>load</Button>
      </View>
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
    },
    inLine: {
      flex: 1,
      flexDirection: 'row',
      maxHeight: 60,
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: app.darkmode ? app.secondaryBackgroundDarkColor : app.secondaryBackgroundLightColor,
      borderRadius: 10,
      width: 150
    }
  })
}