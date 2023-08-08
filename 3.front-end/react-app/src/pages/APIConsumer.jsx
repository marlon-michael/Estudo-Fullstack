import { useState } from "react"


function APIConsumer () {

    const [userInput, setUserInput] = useState('')
    const [inputStyles, setInputStyles] = useState('')
    const [message, setMessage] = useState('')
    const [users, setUsers] = useState([{name:'clique em GET para listar os usuários'}])

    const getAll = async () => {
        await fetch('http://127.0.0.1:3000/get')
        .then(response => response.json())
        .then(data => {
            setUsers([])
            data.map((user)=>setUsers(old=> [...old, user]))
        })
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

        await fetch('http://127.0.0.1:3000/post',{
            mode: 'cors',
            method: 'POST',
            body: JSON.stringify( {name:userInput} ),
            headers: new Headers({'accept': 'application/json','content-type':'text/plain'})
        })
        .then(res=> res.status != 200 ? setInputStyles('error') : setInputStyles(''))
        .catch(e=>console.log(e))
    }

    return(
        <div className="body">
            <div className="row-div">
                <div className="left-div">
                    <div className="usernames-div">
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
