import { useNavigate } from "react-router-dom"


function Header ({props}: any){
    const session = props
    const navigate = useNavigate()

    return(
        <div className="header">
            {session.name}
            <button onClick={() => navigate('/')}>home</button>
            <button onClick={() => navigate('/counter')}>counter</button>
            <button onClick={() => navigate('/consumer')}>API Consumer</button>
        </div>
    )
}

export default Header