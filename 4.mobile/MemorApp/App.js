import { StatusBar } from 'expo-status-bar';
import { useState } from 'react';
import { StyleSheet, View } from 'react-native';
import Button from './component/Button';
import SubTitle from './component/text/SubTitle';
import Title from './component/text/Title';

export default function App() {
  const [counter, setCounter] = useState(0)

  return (
    <View style={styles.container}>
      <Title>Hello world</Title>
      <SubTitle>{counter}</SubTitle>
      <Button onPress={()=>setCounter(old => old+1)}>increment</Button>
      <StatusBar backgroundColor='#ddd'/>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    fontSize: 20,
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  }
});
