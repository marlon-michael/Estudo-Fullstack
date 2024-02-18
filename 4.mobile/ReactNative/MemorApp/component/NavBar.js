import { Dimensions, Image, StyleSheet, View } from 'react-native'
import Button from "./root/Button"
import applicationContext from "../hook/context/applicationContext"
import themeContext from "../hook/context/themeContext"
import { useContext } from 'react'
import { openMenu, sidabarAnimation } from '../animations/animations'


export default function NavBar(props) {
  const style = getStyle()
  const application = useContext(applicationContext)

  return (
    <View style={style.navBar}>
      <Button
        onPress={() => {
          application.tabHistory.length && application.setTab(application.tabHistory.pop())
          application.tabHistory.pop()
        }}
      >
        <Image style={style.icon} source={require('../assets/icons/back.png')} />
      </Button>
      <Button onPress={() => {
        application.setTab('home')
      }}>
        <Image style={style.icon} source={require('../assets/icons/home.png')} />
      </Button>
      <Button onPress={() => {
        application.setTab('helloInput')
      }}>
        <Image style={style.icon} source={require('../assets/icons/document.png')} />
      </Button>
      <Button onPress={() => {
        application.setTab('settings')
      }}>
        <Image style={style.icon} source={require('../assets/icons/settings.png')} />
      </Button>
      <Button style={{backgroundColor: ''}} onPress={() => {
        openMenu(sidabarAnimation)
      }}>
        <Image style={style.icon} source={require('../assets/icons/menu.png')} />
      </Button>
    </View>
  )
}

function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    navBar: {
      width: Dimensions.get('screen').width,
      justifyContent: 'center',
      flexDirection: 'row',
      backgroundColor: theme.darkmode ? theme.lessBackgroundDarkColor : theme.lessBackgroundColor,
    },
    icon: {
      width: 40,
      height: 40
    }
  })
}
