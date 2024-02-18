import { useContext } from "react";
import { StyleSheet, Text } from "react-native";
import themeContext from "../../../hook/context/themeContext";

export default function Title(props) {
  const style = getStyle()

  return (
    <Text
      style={[style.title, props.style]}
      onPress={props.onPress}>{props.children}
    </Text>
  )
}



function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    title: {
      fontSize: 50,
      color: theme.darkmode? theme.fontDarkColor : theme.fontColor,
      margin: 18
    },
  });
}