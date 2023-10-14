# Como desenvolver em React Native

- [similaridades e diferenças com React JS](#similaridades-e-diferenças)
- [estrutura padrão de um projeto React Native](#exemplo-de-app-com-react-native)
- [animações em react native - Animated](#animações)
- [utilizando o tamanho da tela na estilização de componentes - Dimensions](#dimensions---como-ler-tamanho-da-tela)
- [persistência com react native - Async Storage](#asyncstorage---como-armazenar-valores-em-banco-local)
- [manipulando arquivos em dispositivos móveis - expo-file-system](#expo-file-system---manipulando-arquivos)
- [componente de visualização com rolagem - ScrollView](#scroll-view---componentes-de-visualização-com-rolagem)

---
## similaridades e diferenças
- programação e regras de negócio (Javascript)
React Native possui algumas deferenças em comparação a versão web, mas muito ainda se mantém na versão mobile. Todo programação relativa ao javascript continua presente na versão mobile da ferramenta, como os hooks: useState, useEffect, useContext, e por ai vai. 
---
- estilização (styled components)
Porem toda parte de estilização foi substituida por componentes estilidados, que funcionam da seguinte forma
    - os atributos tiveram o ifem (-) substituidos pela letra seguinte em camel case. Ex: background-color -> backgroundColor
    - os números deixaram de ter as métricas definidas para serem definidas apenas pelo número. Ex: 20px, 20% -> 20
    - deve ser passado no componente por meio do atributo "style" o objeto que contem a estilização, e informar qual tag/atributo que utilizará para receber os estilos
    - deve-se declarar os estilos por meio do StyleSheet do react-native
---
- templates e interface (componentes)
    - as tags HTML foram substituidas por componentes simples como <Text> para mostrar texto, e containers para englobar os componentes, como <View>. Não incluindo qualquer componente pronto, como button, h1, p, etc
    - algumas funções mudaram de nome. Ex: onClick -> onPress
---
## exemplo de app com React Native
```javascript

import { SafeAreaView, StyleSheet, View} from 'react-native'
import { StatusBar } from 'expo-status-bar'

export default function App() {
    // << REGRAS DE NEGÓCIO >>
    const [contador, setContador] = useState(0)

    // << ESTILIZAÇÃO >>
    const styles = StyleSheet.create({
        container: {
            flex: 1,
            backgroundColor: '#fff',
            alignItems: 'center',
            justifyContent: 'center',
        },
        Title: {
            fontSize: 20
        },
        button: {
            fontSize: 20,
            backgroundColor: '#46f',
            color: 'white',
            borderRadius: 7,
            padding: 10,
            margin: 10
        }
    })

    // << INTERFACE E TEMPLATES >>
    return(
      // componente de tela que exclui areas não uteis para uso, como barra de status e notch
      <SafeAreaView>
        // componente padrão que engloba o aplcativo usando o estilo de styles.container
        <View style={styles.container}>
            // definição da cor da barra de status
            <StatusBar backgroundColor={"#eee"}/>
            // textos só podem ser postos em tela por meio do componente Text, usando o estilo de styles.title
            <Text style={styles.title}>Cliques {contador}</Text>
            // ações de clique ou toque podem ser acionadas por meio da propriedade "onPress", usando o estilo de styles.button
            <Text style={styles.button} onPress={() => setContador(x => x+1)}> +1 </Text>
        </View>
      </SafeAreaView>
    )
}
```
---
## Animated - como fazer animações em react native
- é necessário usar os componentes com suporte a animação de Animated do 'react-native', como AnimatedView, Animated.Text entre outros, do contrário a animação não acontece
- será reenderizado na chamada do componente, tal como qualquer outro
```javascript
import { useState } from "react"
import { Animated, Easing } from "react-native"


export default function AnimatedComponent(props) {
  const backgroundColorValue = new Animated.Value(0)
  const colorValue = new Animated.Value(0)

  // defini animação que vai do estágio atual(0) para estágio 1 (da cor #ccc para #333) em 500ms
  const backgroundColorAnimation = Animated.timing(backgroundColorValue, {
    toValue: 1,
    duration: 500,
    easing: Easing.linear,
    useNativeDriver: false
  })
  // defini animação que vai do estágio atual(0) para estágio 1 (da cor #222 para #ccc) em 250ms
  const colorAnimation = Animated.timing(colorValue, {
    toValue: 1,
    duration: 250,
    easing: Easing.linear,
    useNativeDriver: false
  })

  // defini estágios de cores 0:#ccc, 1:#333, 2:#aaa, 3:#333
  var backgroundColorInterpolated = backgroundColorValue.interpolate({
    inputRange: [0, 1, 2, 3],
    outputRange: ['#ccc', '#333', '#aaa', '#333'],
  });
  // defini estágios de cores 1: #222 > 2: #ccc
  var colorInterpolated = colorValue.interpolate({
    inputRange: [0, 1],
    outputRange: ['#222', '#ccc'],
  });

  const startAnimations = () => {
    // inicia animação de background da cor
    backgroundColorAnimation.start(() => {
      // após a execução da primeira animação de backgroundColor

      // redefini animação que vai do estágio atual (1) para eságio 3 (da cor #333 > #aaa > #333) em 500ms
      backgroundColorAnimation = Animated.timing(backgroundColorValue, {
        toValue: 3,
        duration: 500,
        easing: Easing.linear,
        useNativeDriver: false
      });

      // inicia animação Color e backgroundColor ao mesmo tempo
      colorAnimation.start();
      backgroundColorAnimation.start();
    });
  };


  return (
    <Animated.Text
      style={[ props.style, { color: colorInterpolated, backgroundColor: backgroundColorInterpolated } ]} // passa os valores atuais da animação para o componente
      onPress={startAnimations} // inicia as animações ao pressionar o componente
    >START ANIMATION</Animated.Text>
  )
}

```
---
## Dimensions - como ler tamanho da tela
```javascript
import { Dimensions } from 'react-native'

Dimensions.get('window').width // largura da tela
Dimensions.get('window').height // altura da tela
```
---
## AsyncStorage - como armazenar valores em banco local
- requerimentos
  - async-storage

### como instalar Async Storage
```console
npm install @react-native-async-storage/async-storage
```
- as funções de AsyncStorage são assincronas, o que significa que se você precisar de algum feedback da função vai precisar usar await
```javascript
import storage from '@react-native-async-storage/async-storage'

storage.setItem('chave', 'valor') // salvar valor
const valor = await storage.getItem('chave') // buscar valor
storage.removeItem('chave') // deletar valor da memoria
```
---
## expo-file-system - manipulando arquivos
```javascript
import * as fs from 'expo-file-system'

// diretório atual + arquivo de uso
const path = `${fs.documentDirectory}arquivo.txt`

// escrevendo arquivos
fs.writeAsStringAsync(path, 'este texto foi escrito pelo app')
.then(alert('salvo'))
.catch(err => alert(err))

// lendo arquivos
fs.readAsStringAsync(path)
.then(content => alert(content))
.catch(err => alert(err))

// deletando arquivos
fs.deleteAsync(path)

// listando arquivos do diretório atual
fs.readDirectoryAsync(fs.documentDirectory)
.then(v => alert(v))
.catch(err => alert(err))

// pede permissão para manipulação de arquivos
fs.StorageAccessFramework.requestDirectoryPermissionsAsync('file://storage/emulated/0/')
  .then(async permission => {
    // se não receber permissão retorna com alerta
    if (!permission.granted) {
      return alert('no permission garanted')
    }
    // le arquivos no diretório selecionado e exclui arquivos anteriores
    await fs.StorageAccessFramework.readDirectoryAsync(permission.directoryUri).then(files => {
      files.map(file => { if (file.search('file.json')) fs.deleteAsync(file).then(v => v) })
    })
    // recria arquivo 
    const file = await fs.StorageAccessFramework.createFileAsync(permission.directoryUri, 'file.json', 'application/json')
    // grava as novas informações no arquivo
    fs.writeAsStringAsync(file, 'content')
  })
```
---
## scroll view - componentes de visualização com rolagem
```html
// componente Pai
<SafeAreaView style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>

  // componente para limitar o espaço que a scroll view ocupa
  <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center', width: 400, minHeight: 400 }}>

    // componente de rolagem
    <ScrollView scrollEnabled contentContainerStyle={{
      width: 400,
      minHeight: 400,
      alignItems: 'center',
      justifyContent: 'center',
    }}>

      // componentes grandes de mais para caber na tela e que farão a rolagem
      <ComponenteDeTextoGigante>

    <ScrollView>

  <View>

<SafeAreaView>
```