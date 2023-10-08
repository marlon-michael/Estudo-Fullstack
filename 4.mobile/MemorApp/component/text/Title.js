import { useContext, useEffect, useMemo, useState } from "react";
import { StyleSheet, Text } from "react-native";
import Context from "../../hook/Context";

export default function Title(props){
  const context = useContext(Context)

  const styles = StyleSheet.create({
    title: {
      fontSize: 50,
      color: context.theme == 'dark'? '#eee':'#223'
    },
  });

  return <Text style={[styles.title, props.style]} onPress={props.onPress}>{props.children}</Text>
}
