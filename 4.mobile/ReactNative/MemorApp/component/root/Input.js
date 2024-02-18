import { useContext } from "react"
import { StyleSheet, TextInput } from "react-native"
import themeContext from "../../hook/context/themeContext";


export default function Input(props) {
  const style = getStyle()

  return (
    <TextInput
      style={style.input}
      placeholderTextColor={'grey'}
      numberOfLines={5}
      {...props}
    />
  )
}

function hexToInt(hex) {
  return parseInt(hex, 16)
}

function intToHex(int) {
  return int.toString(16)
}

function colorSum(color, sum) {
  color = color.replace('#', '')
  let colorSplit = []
  let colorChanging = '#'
  if (color.length > 3) colorSplit = [color.substring(0, 2), color.substring(2, 4), color.substring(4, 6)]
  if (color.length == 3) colorSplit = [color.substring(0, 1), color.substring(1, 2), color.substring(2, 3)]
  colorSplit.forEach(hex => {
    colorChanging += intToHex(hexToInt(hex) + sum)
  })
  return colorChanging
}


function getStyle() {
  colorSum('#202025', - 1)
  const theme = useContext(themeContext)
  return (StyleSheet.create({
    input: {
      backgroundColor: theme.darkmode ? colorSum(theme.backgroundDarkColor, - 2) : colorSum(theme.backgroundColor, -1),
      color: theme.darkmode ? theme.fontDarkColor : theme.fontColor,
      fontSize: 20,
      padding: 10,
      margin: 10,
      borderRadius: 10,
      width: 400
    }
  }))
}