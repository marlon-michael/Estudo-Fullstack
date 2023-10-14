import { StatusBar } from 'expo-status-bar'
import { useContext, useState } from 'react'
import { Dimensions, SafeAreaView, StyleSheet, View } from 'react-native'
import storage from '@react-native-async-storage/async-storage'
import Context from './hook/Context'
import Navigator from './component/Navigator'
import Settings from './tab/Settings'
import Home from './tab/Home'
import Button from './component/basic/Button'
import HelloInput from './tab/HelloInput'



export default function App() {
  const style = styles()
  const app = useContext(Context)
  const animation = { map: undefined }
  const [tab, setTab] = useState('home')
  const [, update] = useState(0)
  app.update = () => update(x => x + 1)
  app.setTab = (tab) => setTab(old => {
    app.tabHistory.push(old)
    return tab
  })

  return (
    <SafeAreaView style={[style.container, { marginTop: 40 }]}>
      <StatusBar translucent={true} backgroundColor={app.darkmode ? app.statusBarDarkColor : app.statusBarLightColor} />
      <View style={style.headerBar}>
        <Button
          onPress={() => {
            app.tabHistory.length && app.setTab(app.tabHistory.pop())
            app.tabHistory.pop()
          }}
        >👈</Button>
        <Button onPress={() => app.setTab('home')}>home</Button>
        <Button onPress={() => app.setTab('helloInput')}>hello input</Button>
        <Button onPress={() => app.setTab('settings')}>settings</Button>
      </View>

      <View style={style.sideBar}>
        <Button onPress={() => app.setTab('home')}>home</Button>
        <Button onPress={() => app.setTab('helloInput')}>hello input</Button>
        <Button onPress={() => app.setTab('settings')}>settings</Button>
      </View>
      <View style={style.container}>

        <Navigator
          activeTab={tab}
          tabs={{
            'home': <Home />,
            'settings': <Settings />,
            'helloInput': <HelloInput />
          }}
        />

      </View>
    </SafeAreaView>
  )
}


function styles() {
  const app = useContext(Context)
  return StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: app.darkmode ? app.backgroundDarkColor : app.backgroundLightColor,
      alignItems: 'center',
      justifyContent: 'center',
    },
    headerBar: {
      width: Dimensions.get('screen').width,
      justifyContent: 'center',
      flexDirection: 'row',
      backgroundColor: app.darkmode ? app.secondaryBackgroundDarkColor : app.secondaryBackgroundLightColor,
    },
    sideBar: {
      opacity: 0,
      display: 'none',
      position: 'absolute',
      top: 0,
      left: 0,
      width: Dimensions.get('screen').width,
      justifyContent: 'center',
      flexDirection: 'column',
      padding: 50,
      backgroundColor: app.darkmode ? app.secondaryBackgroundDarkColor : app.secondaryBackgroundLightColor,
    }
  })
}
