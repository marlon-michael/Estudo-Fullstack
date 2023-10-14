import { useContext } from "react";
import { StyleSheet, Text } from "react-native";
import Context from "../../../hook/Context";



export default function TextTag(props) {
  const style = styles()
  
  return (
    <Text
      {...props}
      style={[props.error ? style.error : style.text, {
        fontSize: props.style?.fontSize ?? props.fontSize ?? (props.error ? style.error.fontSize : style.text.fontSize)
      }]}
    >{props.children}</Text>
  )
}



function styles() {
  const app = useContext(Context)
  return StyleSheet.create({
    text: {
      fontSize: 50,
      color: app.darkmode ? '#fff' : app.negativeLightColor,
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