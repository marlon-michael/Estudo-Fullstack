import { useContext } from "react";
import { StyleSheet, TouchableWithoutFeedback, View } from "react-native";
import themeContext from "../../hook/context/themeContext";



export default function Button(props) {
  const style = getStyle()

  return (
    <TouchableWithoutFeedback onPress={props.onPress}>
      <View style={[style.button, props.style]}>
        {props.children}
      </View>
    </TouchableWithoutFeedback >
  )
}


function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    button: {
      flexDirection: "row",
      alignContent: "center",
      alignItems: "center",
      fontSize: 20,
      backgroundColor: theme.darkmode ? theme.highlightDarkColor : theme.highlightColor,
      color: 'whitesmoke',
      borderRadius: 7,
      padding: 5,
      margin: 10
    }
  });
}