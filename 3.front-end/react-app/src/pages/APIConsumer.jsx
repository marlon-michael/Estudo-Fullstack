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
            res.status != 200 ? setUsernameDivStyles('error') : setUsernameDivStyles('')
            res.json()
        })
        .then(data => {
            setUsers([])
            data.map((user) => setUsers(old=> [...old, user]))
        })
        .catch(error => console.log(error))
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
        .then(res => res.status != 200 ? setInputStyles('error') : setInputStyles(''))
        .catch(error => console.log(error))
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
        </div>
    )
}

export default APIConsumer
