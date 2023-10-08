import { StyleSheet, Text } from "react-native";

export default function SubTitle(props){
  const context = useContext(Context)

  const styles = StyleSheet.create({
    subtitle: {
      fontSize: 30,
      color: context.theme == 'dark'? '#eee':'#223'
    },
  })
  
  return <Text style={[styles.subtitle, props.style]} onPress={props.onPress}>{props.children}</Text>
}
