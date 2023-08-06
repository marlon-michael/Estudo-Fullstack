import { useState } from "react"


function Counter({props}: any) {

	const [count, setCount] = useState(0)

	return (
		<div className="body">
			<h3>Contador de cliques: {count}</h3>
			<button onClick={() => setCount(count + 1)}> contador++ </button>
		</div>
	)
}

export default Counter