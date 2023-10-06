import { StyleSheet, Text } from "react-native";

export default function SubTitle(props){
  return <Text style={[styles.subtitle, props.style]} onPress={props.onPress}>{props.children}</Text>
}

const styles = StyleSheet.create({
  subtitle: {
    fontSize: 30,
  },
});
