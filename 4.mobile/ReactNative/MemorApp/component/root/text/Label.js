import { useContext } from "react";
import { StyleSheet, Text } from "react-native";
import themeContext from "../../../hook/context/themeContext";



export default function Label(props) {
  const style = getStyle()
  const theme = useContext(themeContext)
  
  return (
    <Text
      {...props}
      style={[props.error ? style.error : style.text, {
        fontSize: props.style?.fontSize ?? props.fontSize ?? (props.error ? style.error.fontSize : style.text.fontSize),
        color: props.contrast ? theme.contrastHighlightColor : style.text.color
      }]}
    >{props.children}</Text>
  )
}



function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    text: {
      fontSize: 15,
      color: theme.darkmode ? theme.fontDarkColor : theme.fontColor,
      margin: 5
    },
    error: {
      fontSize: 20,
      color: '#f33',
      backgroundColor: '#111',
      padding: 15,
      margin: 5,
      borderRadius: 10
    }
  });
}