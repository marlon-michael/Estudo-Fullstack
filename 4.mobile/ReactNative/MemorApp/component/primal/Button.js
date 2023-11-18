import { useContext } from "react";
import { StyleSheet, Text } from "react-native";
import Context from "../../hook/Context";



export default function Button(props) {
  const style = styles()
  
  return (
    <Text
      style={[style.button, props.style]}
      onPress={props.onPress}
    >{props.children}</Text>
  )
}



function styles() {
  const app = useContext(Context)
  return StyleSheet.create({
    button: {
      fontSize: 20,
      backgroundColor: app.darkmode ? app.primaryAccentDarkColor : app.primaryAccentLightColor,
      color: 'whitesmoke',
      borderRadius: 7,
      padding: 10,
      margin: 10
    }
  });
}