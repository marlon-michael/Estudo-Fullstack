import { useNavigate } from "react-router-dom"


function Header (){
    const navigate = useNavigate()

    return(
        <div className="header">
            <button onClick={() => navigate('/')}>home</button>
            <button onClick={() => navigate('/counter')}>counter</button>
            <button onClick={() => navigate('/consumer')}>API Consumer</button>
        </div>
    )
}

export default Header