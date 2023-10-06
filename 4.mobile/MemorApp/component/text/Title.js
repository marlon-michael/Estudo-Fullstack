import { StyleSheet, Text } from "react-native";

export default function Title(props){
  return <Text style={[styles.title, props.style]} onPress={props.onPress}>{props.children}</Text>
}

const styles = StyleSheet.create({
  title: {
    fontSize: 50,
  },
});
