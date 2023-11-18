import { useContext } from "react"
import { StyleSheet, TextInput } from "react-native"
import Context from "../../hook/Context";


export default function Input(props) {
  const style = styles()

  return (
    <TextInput
      style={style.input}
      placeholderTextColor={'grey'}
      {...props}
    />
  )
}


function styles() {
  const app = useContext(Context)
  return (StyleSheet.create({
    input: {
      backgroundColor: app.darkmode? app.contrastLightColor : app.contrastDarkColor,
      color: app.darkmode? app.contrastDarkColor: app.contrastLightColor,
      fontSize: 20,
      padding: 10,
      margin: 10,
      borderRadius: 10,
      width: 400
    }
  }))
}