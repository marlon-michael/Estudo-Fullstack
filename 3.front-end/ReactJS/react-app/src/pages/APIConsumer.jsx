import { useState } from "react"


function APIConsumer () {

    const [userInput, setUserInput] = useState('')
    const [inputStyles, setInputStyles] = useState('')
    const [usernameDivStyles, setUsernameDivStyles] = useState('')
    const [message, setMessage] = useState('')
    const [users, setUsers] = useState([{name:'clique em GET para listar os usuários'}])
    const API_URL = 'http://localhost:3000'

    const getAll = async () => {
        await fetch(API_URL + '/user/get')
        .then(res => {
            if (res.status != 200){
                setInputStyles('error')
                res.json().then(error => console.log(error))
            }else{
                setInputStyles('')
            }
            return res.json()
        })
        .then(data => {
            if (data == undefined || data.lenth == 0) return
            console.log(data)
            setUsers([])
            data.map((user) => setUsers(old=> [...old, user]))
        })
        .catch(error => console.log(error.message))
    }

    const post = async () => {
        if (userInput.length == 0) {
            setInputStyles('error')
            setMessage('Nome de usúario deve ter ao menos uma letra')
            return
        }
        else {
            setInputStyles('')
            setMessage('')
        }

        await fetch(API_URL + '/user/post',{
            mode: 'cors',
            method: 'POST',
            body: JSON.stringify( {name:userInput} ),
            headers: {
                'Accept': 'application/json',
                'Content-Type':'application/json'
            }
        })
        .then(res => {
            if (res.status != 200){
                setInputStyles('error')
                res.json().then(error => console.log(error))
            }else{
                setInputStyles('')
            }
        })
        .catch(error => console.log(error.message))
    }

    const [url, setUrl] = useState('http://localhost:3000/user/post')
    const [method, setMethod] = useState('post')
    const [headers, setHeaders] = useState('{"Content-Type":"application/json"}')
    const [body, setBody] = useState('{\n\n"name": "username"\n\n}')
    const request = async () => {
        if (method == 'GET'){
            await fetch(url)
            .then(res => res.json())
            .then(res => console.log(res))
            .catch(error => console.log(error))
        }
        else{
            await fetch(url, {mode:'cors', method, body, headers: JSON.parse(headers)})
            .then(res => res.json())
            .then(res => console.log(res))
            .catch(error => console.log(error))
        }
    }

    return(
        <div className="body">
            <div className="row-div">
                <div className="left-div">
                    <div className={"usernames-div " + usernameDivStyles}>
                        {users.map((user, key) => <p key={key}> {user.name}</p>)}
                    </div>
                    <button onClick={getAll}>GET USERS</button>
                </div>
                <div className="right-div">
                    <div>
                        <input className={inputStyles} value={userInput} onChange={(e)=>setUserInput(e.target.value)} placeholder="username"></input>
                        <p>{message}</p>
                    </div>
                    <button onClick={post}>POST USER</button>
                </div>
            </div>
            <div className='body' style={{gap: '20px', height: '500px', minHeight: '10px'}}>
                <input style={{width: "50vw"}} value={url} onChange={(e)=>setUrl(e.target.value)} placeholder='http://website.com/users/get'></input>
                <input style={{width: "50vw"}} value={method} onChange={(e)=>setMethod(e.target.value)} placeholder='GET/POST'></input>
                <input style={{width: "50vw"}} value={headers} onChange={(e)=>setHeaders(e.target.value)} placeholder='{"Content-Type":"application/json"}'></input>
                <textarea style={{width: "50vw", height: '100px'}} rows={"5"} value={body} onChange={(e)=>setBody(e.target.value)} placeholder='{"name": "username"}'></textarea>
                <button onClick={request}>request</button>
                <p>as repostas das requisições são logadas pelo console ( Shift + I )</p>
            </div>
        </div>
    )
}

export default APIConsumer
