import { useContext, useState } from "react"
import { StyleSheet, View } from "react-native"
import Title from "../component/root/text/Title"
import Input from "../component/root/Input"
import Button from "../component/root/Button"
import File from '../hook/File'
import themeContext from "../hook/context/themeContext"
import Label from "../component/root/text/Label"



export default function Editor() {
  const style = getStyle()
  const [input, setInput] = useState('')
  const [filename, setFilename] = useState('input.txt')

  function saveInput() {
    File.save(filename, input)
  }

  async function loadInput() {
    File.load(filename).then(content => setInput(content)).catch(x => alert(x))
  }

  return (
    <View style={style.container}>

      <Title> File editor </Title>
      <Input
        onChangeText={setFilename}
        value={filename}
        placeholder="filename"
      />
      <Input
        onChangeText={setInput}
        value={input}
        placeholder="type here"
        lines={10}
      />
      <View style={style.inLine}>
        <Button style={{ height: 40 }} onPress={() => saveInput()}><Label contrast>save</Label></Button>
        <Button style={{ height: 40 }} onPress={() => loadInput()}><Label contrast>load</Label></Button>
      </View>

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
    },
    inLine: {
      flex: 1,
      flexDirection: 'row',
      maxHeight: 60,
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: theme.darkmode ? theme.lessBackgroundDarkColor : theme.lessBackgroundColor,
      borderRadius: 10,
    }
  })
}