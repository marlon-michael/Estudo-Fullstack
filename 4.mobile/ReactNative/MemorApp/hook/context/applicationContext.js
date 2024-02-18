import { createContext } from "react"


const applicationContext = createContext({
    currentTab: 'home',
    tabHistory: ['home']
})

export default applicationContext