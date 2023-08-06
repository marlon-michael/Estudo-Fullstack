

function Home({props}: any) {
	const session = props
	
	return (
		<div className="body">
			<h1>Hello {session.isLogged ? "Usuario" : "React"}</h1>
		</div>
	)
}

export default Home
