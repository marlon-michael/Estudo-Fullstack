import { useContext } from "react";
import { StyleSheet, Text } from "react-native";
import Context from "../../../hook/Context";

export default function Title(props) {
  const style = styles()

  return (
    <Text
      style={[style.title, props.style]}
      onPress={props.onPress}>{props.children}
    </Text>
  )
}



function styles() {
  const app = useContext(Context)
  return StyleSheet.create({
    title: {
      fontSize: 50,
      color: app.darkmode? app.contrastDarkColor : app.contrastLightColor,
      margin: 18
    },
  });
}