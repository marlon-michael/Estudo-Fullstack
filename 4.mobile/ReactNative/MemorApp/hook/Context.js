import { createContext } from "react"



const Context = createContext({
    darkmode: true,
    tabHistory: [],

    primaryAccentDarkColor: '#334',
    primaryAccentLightColor: '#46b',

    secondaryAccentDarkColor: '#223',
    secondaryAccentLightColor: '#35a',

    primaryBackgroundDarkColor: '#222229',
    primaryBackgroundLightColor: '#fff',

    secondaryBackgroundDarkColor: '#191920',
    secondaryBackgroundLightColor: '#ddd',

    contrastDarkColor: '#eee',
    contrastLightColor: '#2c2c3a',

    statusBarDarkColor: '#16161b',
    statusBarLightColor: '#ccc',
})


export default Context