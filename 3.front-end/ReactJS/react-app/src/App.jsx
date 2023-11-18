import { useState } from 'react'
import RouterComponent from './routes/RouterComponent'
import './App.css'

function App() {

	const [session] = useState({
		name: String
	})

	return (
		<>
			<RouterComponent props = {session} />
		</>
	)
}

export default App
