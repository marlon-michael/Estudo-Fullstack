import { useNavigate } from "react-router-dom"


function Header (props: any){
    const session = props.props
    const navigate = useNavigate()

    return(
        <div className="header">
            {session.name}
            <button onClick={() => navigate('/')}>home</button>
            <button onClick={() => navigate('/counter')}>counter</button>
        </div>
    )
}

export default Header