import { useContext } from "react";
import { StyleSheet, Text } from "react-native";
import Context from "../../../hook/Context";

export default function SubTitle(props) {
  const style = styles()

  return (
    < Text
      style={[style.subtitle, props.style]}
      onPress={props.onPress}
    >{props.children}</Text >
  )
}



function styles() {
  const app = useContext(Context)
  return StyleSheet.create({
    subtitle: {
      fontSize: 30,
      color: app.darkmode ? app.negativeDarkColor : app.negativeLightColor,
    },
  });
}