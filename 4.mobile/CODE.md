# Como programar em React Native

- programação (Javascript)
React Native possui algumas deferenças em comparação a versão web, mas muito ainda se mantém na versão mobile. Todo programação relativa ao javascript continua presente na versão mobile da ferramenta, como os hooks: useState, useEffect, useContext, e por ai vai. 
- estilização (styled components)
Porem toda parte de estilização foi substituida por componentes estilidados, que funcionam da seguinte forma
    - os atributos tiveram o ifem (-) substituidos pela letra seguinte em camel case. Ex: background-color -> backgroundColor
    - os números deixaram de ter as métricas definidas para serem definidas apenas pelo número. Ex: 20px, 20% -> 20
    - deve ser passado no componente por meio do atributo "style" o objeto que contem a estilização, e informar qual tag/atributo que utilizará para receber os estilos
    - deve-se declarar os estilos por meio do StyleSheet do react-native
- templates e interface (componentes)
    - as tags HTML foram substituidas por componentes simples como <Text> para mostrar texto, e containers para englobar os componentes, como <View>. Não incluindo qualquer componente pronto, como button, h1, p, etc
    - algumas funções mudaram de nome. Ex: onClick -> onPress
---
### exemplo de app com React Native
```javascript
import { StyleSheet, View } from 'react-native';
import { StatusBar } from 'expo-status-bar';

export default function App() {
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

    return(
        // componente padrão que engloba o aplcativo usando o estilo de styles.container
        <View style={styles.container}>
            // definição da cor da barra de status
            <StatusBar backgroundColor={"#eee"}/>
            // textos só podem ser postos em tela por meio do componente Text, usando o estilo de styles.title
            <Text style={styles.title}>Hello World</Text>
            // ações de clique ou toque podem ser acionadas por meio da propriedade "onPress", usando o estilo de styles.button
            <Text style={styles.button} onPress={() => console.log('comando')}> botão </Text>
        </View>
    )
}
```
