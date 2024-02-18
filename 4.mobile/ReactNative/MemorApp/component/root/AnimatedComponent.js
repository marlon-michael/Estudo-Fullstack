import { Animated, Easing } from "react-native"

// ANIMATED COMPONENT USE SAMPLE
/*
<AnimatedComponent
  component={Animated.Text}
  animation={animation}
  style={{ width: 400, padding: 20, color: 'grey', alignItems: 'center', justifyContent: 'center' }}
  animations={
    [{
      atribute: 'backgroundColor',
      inputRange: [0, 1, 2],
      range: ['#ccc', '#000', '#222']
    }, {
      atribute: 'color',
      inputRange: [0, 1],
      range: ['#222', '#ccc']
    }]
  }
  onPress={() => {
    // start
    animation.map.get('backgroundColor').getAnimation(2, 2000).start()
    animation.map.get('color').getAnimation(1, 2000).start()
  }}
>
  start animation
</AnimatedComponent>
*/


export default function AnimatedComponent(props) {
  const animationMap = new Map()
  props.animations.map((a) => {
    animationMap.set(a.atribute, {
      animatedValue: new Animated.Value(0),
      getAnimation: (stage, duration) => getAnimation(a.atribute, stage, duration),
      value: () => setStage(a.atribute, a.inputRange, a.range),
    })
  })

  animationMap.forEach(value => value.value())
  props.animation.map = animationMap
  mapStyle()

  function getAnimation(atribute, stage, duration) {
    let animation = animationMap.get(atribute)
    return Animated.timing(animation.animatedValue, {
      toValue: stage,
      duration: duration,
      easing: Easing.bezier(0.42, 0, 0.58, 1),
      useNativeDriver: false
    })
  }

  function setStage(atribute, inputRange, outputRange) {
    let animation = animationMap.get(atribute)
    return animation.value = animation.animatedValue.interpolate({
      inputRange: inputRange,
      outputRange: outputRange,
    })
  }

  function mapStyle() {
    animationMap.forEach((value, key) => {
      if (typeof (props.style.map) !== 'function') props.style[key] = value.value
      else props.style.map(style => {
        style[key] = value.value
      })
    })
  }

  while (props.pending?.animation.length > 0) {
    props.pending.animation.pop()(props.animation)
  }
  props.pending.animation = []

  return (
    <props.component
      {...props}
      opacity={animationMap.has('opacity') ? animationMap.get('opacity').value : 1}
    >{props.children}</props.component>
  )
}
