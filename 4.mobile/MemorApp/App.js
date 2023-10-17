import { StatusBar } from 'expo-status-bar'
import { useContext, useState } from 'react'
import { Animated, Dimensions, SafeAreaView, StyleSheet, View } from 'react-native'
import storage from '@react-native-async-storage/async-storage'
import Context from './hook/Context'
import Navigator from './component/Navigator'
import Settings from './tab/Settings'
import Home from './tab/Home'
import HelloInput from './tab/HelloInput'
import AnimatedComponent from './component/animation/AnimatedComponent'
import Button from './component/basic/Button'



export default function App() {
  const style = styles()
  const app = useContext(Context)
  const sidaBarAnimation = { map: undefined }
  const [tab, setTab] = useState('home')
  const [, update] = useState(0)
  app.update = () => update(x => !x)
  app.setTab = (tab) => setTab(old => {
    old !== tab && app.tabHistory.push(old)
    return tab
  })

  return (
    <SafeAreaView style={[style.container, { marginTop: 40 }]}>
      <StatusBar translucent={true} backgroundColor={app.darkmode ? app.statusBarDarkColor : app.statusBarLightColor} />
      <View style={style.headerBar}>
        <Button
          onPress={() => {
            // back to last tab
            app.tabHistory.length && app.setTab(app.tabHistory.pop())
            app.tabHistory.pop()
          }}
        >👈</Button>
        <Button onPress={() => app.setTab('home')}>home</Button>
        <Button onPress={() => app.setTab('helloInput')}>hello input</Button>
        <Button onPress={() => app.setTab('settings')}>settings</Button>
        <Button onPress={() => {
          sidaBarAnimation.map.get('zIndex').getAnimation(1, 0).start()
          sidaBarAnimation.map.get('top').getAnimation(1, 500).start()
          sidaBarAnimation.map.get('opacity').getAnimation(1, 500).start()
        }}>animation</Button>
      </View>

      <AnimatedComponent
        component={Animated.View}
        animation={sidaBarAnimation}
        style={[style.sideBar, {}]}
        animations={[{
          atribute: 'top',
          inputRange: [0, 1],
          range: [-Dimensions.get('screen').height, 0]
        }, {
          atribute: 'opacity',
          inputRange: [0, 1],
          range: [0, 1]
        }, {
          atribute: 'zIndex',
          inputRange: [0, 1],
          range: [-1, 1]
        }]}
      >
        <Button onPress={() => app.setTab('home')}>home</Button>
        <Button onPress={() => app.setTab('helloInput')}>hello input</Button>
        <Button onPress={() => app.setTab('settings')}>settings</Button>
        <Button onPress={() => {
          sidaBarAnimation.map.get('top').getAnimation(0, 500).start()
          sidaBarAnimation.map.get('opacity').getAnimation(0, 500).start(() => {
            sidaBarAnimation.map.get('zIndex').getAnimation(0, 0).start()
          })
        }}>animation</Button>

      </AnimatedComponent>

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
      position: 'absolute',
      top: -Dimensions.get('screen').height,
      left: 0,
      width: Dimensions.get('screen').width,
      height: Dimensions.get('screen').height,
      justifyContent: 'center',
      flexDirection: 'column',
      padding: 50,
      backgroundColor: app.darkmode ? app.secondaryBackgroundDarkColor : app.secondaryBackgroundLightColor,
    }
  })
}
