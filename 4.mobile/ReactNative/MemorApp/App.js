import * as fs from 'expo-file-system'
import { Dimensions, Image, SafeAreaView, StyleSheet, View } from 'react-native'
import { useContext, useEffect, useState } from 'react'
import { StatusBar } from 'expo-status-bar'
import themeContext from './hook/context/themeContext'
import applicationContext from './hook/context/applicationContext'
import Navigator from './component/root/Navigator'
import Settings from './tab/Settings'
import Home from './tab/Home'
import Editor from './tab/FileEditor'
import NavBar from './component/NavBar'
import Menu from './component/Menu'



export default function App() {
  const style = getStyle()
  const application = useContext(applicationContext)
  const theme = useContext(themeContext)
  // const sidaBarAnimation = { map: undefined }
  const [changes, setChanges] = useState(0)

  useEffect(() => {
    fs.readAsStringAsync(fs.documentDirectory + 'settings')
      .then(document => {
        const settings = JSON.parse(document)
        theme.darkmode = settings.darkmode
        application.update()
      }
      )
  }, [])

  if (changes !== 0) setChanges(0)
  application.update = () => setChanges(changes => changes + 1)
  application.setTab = (tab) => {
    application.currentTab !== tab && application.tabHistory.push(application.currentTab)
    application.currentTab = tab
    application.update()
    return tab
  }

  return (
    <SafeAreaView style={[style.container, { marginTop: 40 }]}>
      <StatusBar translucent={true} backgroundColor={theme.darkmode ? theme.statusBarDarkColor : theme.statusBarColor} />
      <Menu />
      <View style={style.container}>
        <Navigator
          activeTab={application.currentTab}
          tabs={{
            'home': <Home />,
            'settings': <Settings />,
            'helloInput': <Editor />
          }}
        />
      </View>
      <NavBar />
    </SafeAreaView>
  )
}


function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: theme.darkmode ? theme.backgroundDarkColor : theme.backgroundColor,
      alignItems: 'center',
      justifyContent: 'center',
    },
    sideBar: {
      position: 'absolute',
      top: -Dimensions.get('screen').height,
      left: 0,
      width: Dimensions.get('screen').width,
      height: Dimensions.get('screen').height,
      justifyContent: 'center',
      flexDirection: 'column',
      padding: 50,
      backgroundColor: theme.darkmode ? theme.lessBackgroundDarkColor : theme.lessBackgroundColor,
    },
    icon: {
      width: 40,
      height: 40
    }
  })
}
