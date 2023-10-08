import { StatusBar } from 'expo-status-bar';
import { useContext, useEffect, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import Button from './component/Button';
import SubTitle from './component/text/SubTitle';
import Title from './component/text/Title';
import Context from './hook/Context';


export default function App() {
  const context = useContext(Context)
  const [theme, setTheme] = useState(context.theme)

  toggleTheme = (theme)=>{
    if (theme === 'light') context.theme = 'light'
    else if (theme === 'dark') context.theme = 'dark'
    else if (context.theme === 'dark') context.theme = 'light'
    else if (context.theme === 'light') context.theme = 'dark'
    setTheme(x => x+1)
  }

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: context.theme == 'dark'? '#223':'#fff',
      alignItems: 'center',
      justifyContent: 'center',
    }
  });

  return (
    <View style={styles.container}>
      <StatusBar backgroundColor={context.theme == 'dark'?'#112':"#eee"}/>
      <Title> {context.theme} </Title>
      <Button onPress={() => toggleTheme('light')}>light</Button>
      <Button onPress={() => toggleTheme('dark')}>dark</Button>
      <Button onPress={()=> toggleTheme()}>toggle</Button>
    </View>
  );
}

