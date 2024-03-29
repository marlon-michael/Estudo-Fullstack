# Como contruir um web app com React

### indice
- [criando componente](#criando-componente)
- [armazenando valores - useState](#usestate-variaveis-arrays-e-objetos---armazenamento-de-valores)
- [eventos e temporizadores - useEffect](#useeffect---execução-de-comandos-por-evento-ou-tempo-determinado)
- [informações globais - useContext](#informações-globais---usecontext)
- [executar alterações de estado por comandos predefinidos](#executar-alterações-de-estado-com-uma-comandos-predefinidos---usereducer)
- [input](#lendo-texto-de-inputs)
- [armazenamento de dados local - localStorage / sessionStorage](#local-storage---banco-de-dados-local)
- [passando dados para componentes - props / args](#passando-dados-para-dentro-do-componente)
- [trocando dados entre componentes (pai e filho)](#trocando-dados-entre-componente-pai-e-filho)
- [rotas e navegação entre paginas](#rotas---react-router-dom)
- [consumindo API](#consumindo-api)
---
### criando componente
- crie um arquivo com extensão ".js" ou ".ts" de acordo com a linguagem selecionada para o projeto
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
- uma unica vez
	```javascript
	useEffect(()=>{
		//code block
	}, [])
	```
- quando uma variavel for alterada
	```javascript
	useEffect(()=>{
		//code block
	}, [variavel])
	```
---
### informações globais - useContext
useContext serve para disponibilizar variaveis de forma global na aplicação para casos em que o valor precisa ser utiliado por toda aplicação. Exemplo: tema escuro ou claro
- instanciar componente provedor de contexto, bem como o contexto a ser disponibilizado
```javascript
const myContext = createContext(null)
const theme = useContext('dark')
```
- retornar os componentes que utilizarão o contexto dentro do componente provedor, junto com o contexto passado para a propriedade "value"
```javascript
return(
	<myContext.Provider value={theme}>
		<h1>{theme}</h1> // mostrara o valor "dark"
	</myContext.Provider>
)
```
---
### executar alterações de estado com uma comandos predefinidos - useReducer
- definir a função de redução, suas entradas e saídas
	- state: estado atual, definido pelo retorno da função de redução (objeto)
	- action: ação a ser executada (objeto)
	```javascript
	const reducer = (state, action) => {
	switch (action.type) {
		case "INCREMENT": // define count + 1, caso action.type == "INCREMENT"
			return { count: state.count + 1 };
		case "DECREMENT": // define count - 1, caso action.type == "DECREMENT"
			return { count: state.count - 1 };
		default: // define mesmo estado caso action.type não satisfaça nenhuma opção
			return state;
	}
	}
	```
- 
	```javascript
	// definir a váriavel/objeto de estado, a função o que gerencia, passando a função redutora e o estado inicial
	const [state, dispatch] = useReducer(reducer, { count: 0 })

	return (
		<div>
		<h1>Contador: {state.count}</h1>
		<button onClick={() => dispatch({ type: "INCREMENT" })}>Incrementar</button>
		<button onClick={() => dispatch({ type: "DECREMENT" })}>Decrementar</button>
		</div>
	)
	```
---
### lendo texto de inputs
```javascript
const [text, setText] = useState("")
const changeText = element => setTask(element.target.value)
```
```html
<input onChange={(event)=>setText(event.target.value)} value={text}></input>
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
    - componente de rotas (/router/Router.jsx)
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
    - navegação (Header.jsx) 
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
	- GET
	```javascript
	// requisição GET
	const [data, setData] = useState([{}])

	async function getRequest(){
		await fetch('http://127.0.0.1:3000') // requisição GET
		.then(response => response.json()) // fazendo o parse da requisição para JSON
		.then(response => setData(response)) // guardando response
		.catch(error => console.log(error)) // mostrando erros
	}
	```
	- POST, UPDATE
	```javascript
	const [data, setData] = useState({information: true})

	async function postRequest(){
		await fetch('http://127.0.0.1:3000', {
			method: 'post', // method: GET, POST, UPDATE, DELETE, ...
			mode: 'cors', // mode: cors, no-cors, ...
			body: JSON.stringfy(data), // conversão de JSON para texto 
			headers: {
				'Content-Type':'text/plain' // formato de dados em body: Texto
				// 'Content-Type':'application/json', // formato de dados em body: JSON
			}
		})
		.then(response => response.status != 200 ? console.log(response.json())) // mostra requisição de não retornar OK
		.catch(error => console.log(error)) // mostra erro na requisição
	}
	```

