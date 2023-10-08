import { StyleSheet, Text } from "react-native";

export default function Button(props){
  return <Text style={[styles.button, props.style]} onPress={props.onPress}>{props.children}</Text>
}

const styles = StyleSheet.create({
  button: {
    fontSize: 20,
    backgroundColor: '#46f',
    color: 'white',
    borderRadius: 7,
    padding: 10,
    margin: 10
  }
});
