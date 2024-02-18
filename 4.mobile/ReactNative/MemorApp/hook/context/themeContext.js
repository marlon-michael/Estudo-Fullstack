import { createContext } from "react"


const themeContext = createContext({
    darkmode: true,

    highlightColor: '#556',
    highlightDarkColor: '#334',

    lessHighlightColor: '#34a',
    lessHighlightDarkColor: '#220',

    contrastHighlightColor: '#fff',

    backgroundColor: '#eee',
    backgroundDarkColor: '#222229',

    lessBackgroundColor: '#ddd',
    lessBackgroundDarkColor: '#191920',

    fontColor: '#777',
    fontDarkColor: '#eee',

    statusBarColor: '#ddd',
    statusBarDarkColor: '#16161b',
})

export default themeContext