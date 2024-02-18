import { Animated, Dimensions, StyleSheet } from 'react-native'
import themeContext from "../hook/context/themeContext"
import { useContext } from 'react'
import { modalAnimation, pending, modalAnimationList } from '../animations/animations'
import AnimatedComponent from './root/AnimatedComponent'


export default function Modal(props) {
  const style = getStyle()


  return (
    <AnimatedComponent
      pending={pending}
      component={Animated.View}
      animation={modalAnimation}
      style={[style.modal, {}]}
      animations={modalAnimationList}
    >
      {props.children}
    </AnimatedComponent >
  )
}

function getStyle() {
  const theme = useContext(themeContext)
  return StyleSheet.create({
    modal: {
      position: 'absolute',
      top: -Dimensions.get('screen').height,
      width: Dimensions.get('screen').width * 0.8,
      justifyContent: 'center',
      alignItems: 'center',
      flexDirection: 'column',
      padding: 10,
      borderRadius: 20,
      backgroundColor: theme.darkmode ? theme.lessBackgroundDarkColor : theme.lessBackgroundColor,
    },
    icon: {
      width: 40,
      height: 40
    }
  })
}
