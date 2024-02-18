import { useContext } from "react";
import { StyleSheet, Text } from "react-native";
import themeContext from "../../../hook/Context/themeContext";

export default function SubTitle(props) {
  const style = getStyle()

  return (
    < Text
      style={[style.subtitle, props.style]}
      onPress={props.onPress}
    >{props.children}</Text >
  )
}



function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    subtitle: {
      fontSize: 30,
      color: theme.darkmode ? theme.fontDarkColor : theme.fontColor,
    },
  });
}