# Como contruir um web app com React + javascript

### indice
- [criando componente](#criando-componente)
- [armazenando valores - useState](#usestate-variaveis-arrays-e-objetos---armazenamento-de-valores)
- [eventos e temporizadores - useEffect](#useeffect---execução-de-comandos-por-evento-ou-tempo-determinado)
- [input](#lendo-texto-de-inputs)
- [armazenamento de dados local - localStorage / sessionStorage](#local-storage---banco-de-dados-local)
- [passando dados para componentes - props / args](#passando-dados-para-dentro-do-componente)
- [trocando dados entre componentes (pai e filho)](#trocando-dados-entre-componente-pai-e-filho)
- [rotas e navegação entre paginas](#rotas---react-router-dom)
---
### criando componente
- crie um arquivo com extensão ".js" ou ".ts"
- estrutura padrão de um componente react
	```javascript
	import React from "react";

	export default function ComponentName(){
	  return (
	    <div>
	      <h1>Hello Component</h1>
	    </div>
 	  )
	}
	```

- importe o componente no arquivo principal (app.js)
	```javascript 
	import ComponentName from "./component/location/Component-file";
	```

- utilize o componente como se fosse uma tag HTML
	```javascript 
	<ComponentName></ComponentName>
	```
---
### useState (variaveis, arrays e objetos) - armazenamento de valores
- inicialização
	```javascript 
	const [text, setText] = useState("initial text")
	const [array, setArray] = useState(["element 1", "element 2"])
	const [thing, setThing] = useState({name: "person", age: 18})

	setText("new text")
	setArray(oldArray => [...oldArray, "new element"]) // adiciona elemento ao array
	setThing({...thing, name: "new name"})
	```
- remoção
	```javascript 
	const [array, setArray] = useState(["element 1", "element 2"])

	let deletingElement = "element 2"
	setArray (old => old.filter((element) => element != deletingElement))
	```
---
### useEffect - execução de comandos por evento ou tempo determinado
useEffect pode ser usado para executar um comando um determinado número de vezes
- one single time using an empty array
	```javascript
	useEffect(()=>{
		//code block
	}, [])
	```
---
### lendo texto de inputs
```javascript
const [text, setText] = useState("")
const changeText = element => setTask(element.target.value)
```
---
### local storage - banco de dados local
- salvar
	```javascript
	let obj = {name:"my name"}
	localStorage.setItem('key_name', JSON.stringify(obj))
	```
- consultar
	```javascript
	let obj = JSON.parse(localStorage.getItem('key_name'))
	```
---
### passando dados para dentro do componente
- nomeie os dados de entrada dentro dos parenteses do componente, o qual chamamos de "props"
	```javascript
	export default function ComponentName(props){
		//code block
	}
	```
- use as chaves para mostrar um prop em meio ao HTML
	```javascript
	  return (
	    <div>
	      <p> {props.propname} {props.id} </p>
		  <p> {propname} {id} </p> // atributos podem ser usados sem expecificar o objeto de uso
	    </div>
 	  );
	```

- adicione o componente como uma tag HTML:
	```javascript
	<ComponentName propname = "this is a prop" id = {1}></ComponentName>
	```
---
### trocando dados entre componente pai e filho
#### (componente pai)
- importe o useState do react
	```javascript 
	import {useState} from "react"
	```
- defina uma variavel para armazenar os dados do componente filho
	```javascript 
	const [childData, setChildData] = useState()
	```
- crie uma função e passe como uma prop para o componente filho
	```javascript 
	const func = (data) => { setData(data) }
	``` 
	```html
	<Component func = {func}></Component>
	```
- renderizando os dados do componente filho
	```html 
	<h1>{childData}</h1>
	``` 

#### (componente filho)
- use a função passada por prop para definir o valor que você precisa para fora do componente
	```javascript
	const [data,setData] = useState()
    setData("childData")
    props.func(data)
	``` 
---
# rotas - React Router Dom
- requisitos
    ```console
    npm install react-router-dom
    ```
- limitações e poderes
    - navegação só pode ser feita por meio desse componente. portanto para fazer a navegação em cabeçalho ou rodapé o componente precisa estar dentro de BrowserRouter
- utilização
    - componente de rotas (/router/Router.tsx)
        ```javascript
        import {Routes, Route, BrowserRouter} from "react-router-dom"
		import {Header} from "./components/Header"

        function Router(){
            return (
                <BrowserRouter>
                    <Header/> // componente de navegação
                    <Router>
                        <Route element = { <HomeComponent/> } index>
                        <Route element = { <PageTwo/> } path = "/page">
                    </Router>
                </BrowserRouter>
            )
        }

        export dafault Router
        ```
    - navegação (Header.tsx) 
        ```javascript
        import {Navigate} from "react-router-dom"

        function Header(){
            const navigate = Navigate() // só pode ser usado dentro de um componente de rotas
            return(
                <div>
                    <button onClick = { ()=>navigate("/") }>Home</button>
                    <button onClick = { ()=>navigate("/page") }>Page Two</button>
                </div>
            )
        }

        export dafault Header
        ```
---
### consumindo API
- fetch

