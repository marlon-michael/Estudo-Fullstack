// import React from "react"
import { Routes, Route, BrowserRouter } from "react-router-dom"
import Footer from "../components/Footer"
import Header from "../components/Header"
import APIConsumer from "../pages/APIConsumer"

import Counter from "../pages/Counter"
import Home from "../pages/Home"


function RouterComponent ({props}: any) {
	const session = props
	
	return (
		<BrowserRouter>
			<Header props={session} />
			<Routes>
				<Route element = { <Home props = {session} /> } index />
				<Route element = { <Counter props = {session} /> } path = "/counter" />
				<Route element = { <APIConsumer props = {session} />} path = "/consumer" />
			</Routes>
			<Footer/>
		</BrowserRouter>
	)
}

export default RouterComponent
