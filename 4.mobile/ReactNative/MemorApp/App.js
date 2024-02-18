import { Animated, Dimensions, Image, SafeAreaView, StyleSheet, View } from 'react-native'
import { useContext, useEffect, useState } from 'react'
import { StatusBar } from 'expo-status-bar'
import themeContext from './hook/context/themeContext'
import applicationContext from './hook/context/applicationContext'
import Navigator from './component/root/Navigator'
import AnimatedComponent from './component/root/AnimatedComponent'
import Button from './component/root/Button'
import Settings from './tab/Settings'
import Home from './tab/Home'
import Editor from './tab/FileEditor'
import Label from './component/root/text/Label'
import * as fs from 'expo-file-system'
import { sidebarAnimationList, closeMenu, pending, sidabarAnimation } from './animations/animations'
import NavBar from './component/NavBar'



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
      <AnimatedComponent
        pending={pending}
        component={Animated.View}
        animation={sidabarAnimation}
        style={[style.sideBar, {}]}
        animations={sidebarAnimationList}
      >
        <Button onPress={() => {
          closeMenu(sidabarAnimation)
          if (application.currentTab != 'home') {
            pending.animation.push(closeMenu)
            application.setTab('home')
          }
        }}>
          <Image style={style.icon} source={require('./assets/icons/home.png')} />
          <Label contrast>home</Label>
        </Button>
        <Button onPress={() => {
          closeMenu(sidabarAnimation)
          if (application.currentTab != 'helloInput') {
            pending.animation.push(closeMenu)
            application.setTab('helloInput')
          }
        }}>
          <Image style={style.icon} source={require('./assets/icons/document.png')} />
          <Label contrast>text editor</Label></Button>
        <Button onPress={() => {
          closeMenu(sidabarAnimation)
          if (application.currentTab != 'settings') {
            pending.animation.push(closeMenu)
            application.setTab('settings')
          }
        }}>
          <Image style={style.icon} source={require('./assets/icons/settings.png')} />
          <Label contrast>settings</Label></Button>
      </AnimatedComponent>

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
