import { useState } from "react"
import { User } from "../types"


function APIConsumer ({props}: any) {

    const [users, setUsers] = useState<User[]>([])

    const getAll = async () => {
        await fetch('http://127.0.0.1:3000/get')
        .then(response => response.json())
        .then(data => {
            setUsers([])
            data.map((user: User)=>setUsers(old=> [...old, user]))
        })
    }

    const post = async () => {
        await fetch('http://127.0.0.1:3000/post',{
            method: 'POST',
            mode: 'cors',
            body: JSON.stringify( {name:"juca"} ),
            headers: new Headers({
                'accept': 'application/json',
                'content-type':'text/plain',
                // 'Access-Control-Allow-Origin': '*',
            })
        })
        .then(res=>res.json())
        .then(res=>console.log(res))
        .catch(e=>console.log(e))
    }

    return(
        <div className="body">
            {users.map((user: User) => <p>{user.name}</p>)}
            <button onClick={getAll}>GET USERS</button>
            <button onClick={post}>POST USERS</button>
        </div>
    )
}

export default APIConsumer
