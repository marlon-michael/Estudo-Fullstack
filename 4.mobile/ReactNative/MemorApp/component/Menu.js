import { useContext } from 'react'
import { Animated, Dimensions, Image, StyleSheet } from 'react-native'
import { pending, menuAnimation, menuAnimationList, closeMenu } from '../animations/animations'
import applicationContext from '../hook/context/applicationContext'
import themeContext from '../hook/context/themeContext'
import AnimatedComponent from './root/AnimatedComponent'
import Button from './root/Button'
import Label from './root/text/Label'



export default function Menu(props) {
  const style = getStyle()
  const application = useContext(applicationContext)

  return (
    <AnimatedComponent
      pending={pending}
      component={Animated.View}
      animation={menuAnimation}
      style={[style.menu, {}]}
      animations={menuAnimationList}
    >
      <Button onPress={() => {
        closeMenu(menuAnimation)
        if (application.currentTab != 'home') {
          pending.animation.push(closeMenu)
          application.setTab('home')
        }
      }}>
        <Image style={style.icon} source={require('../assets/icons/home.png')} />
        <Label contrast>home</Label>
      </Button>
      <Button onPress={() => {
        closeMenu(menuAnimation)
        if (application.currentTab != 'helloInput') {
          pending.animation.push(closeMenu)
          application.setTab('helloInput')
        }
      }}>
        <Image style={style.icon} source={require('../assets/icons/document.png')} />
        <Label contrast>text editor</Label></Button>
      <Button onPress={() => {
        closeMenu(menuAnimation)
        if (application.currentTab != 'settings') {
          pending.animation.push(closeMenu)
          application.setTab('settings')
        }
      }}>
        <Image style={style.icon} source={require('../assets/icons/settings.png')} />
        <Label contrast>settings</Label></Button>
    </AnimatedComponent>
  )
}



function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    menu: {
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