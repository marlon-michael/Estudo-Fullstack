import { useState } from 'react'
import './App.css'
import RouterComponent from './routes/RouterComponent'

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
