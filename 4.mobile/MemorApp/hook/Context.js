import { createContext } from "react"



const Context = createContext({
    darkmode: true,
    tabHistory: [],

    primaryDarkColor: '#334',
    primaryLightColor: '#46b',

    secondaryDarkColor: '#223',
    secondaryLightColor: '#35a',

    backgroundDarkColor: '#222229',
    backgroundLightColor: '#fff',

    secondaryBackgroundDarkColor: '#191920',
    secondaryBackgroundLightColor: '#ddd',

    statusBarDarkColor: '#16161b',
    statusBarLightColor: '#ccc',

    negativeDarkColor: '#eee',
    negativeLightColor: '#2c2c3a'
})


export default Context