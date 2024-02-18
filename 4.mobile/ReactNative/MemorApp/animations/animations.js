import { Dimensions } from 'react-native'


export const pending = { animation: [] }
export const sidabarAnimation = { map: undefined }
export const modalAnimation = { map: undefined }

export const sidebarAnimationList = [{
  atribute: 'top',
  inputRange: [0, 1],
  range: [-Dimensions.get('screen').height, 0]
}, {
  atribute: 'opacity',
  inputRange: [0, 1],
  range: [-1, 1]
}, {
  atribute: 'zIndex',
  inputRange: [0, 1],
  range: [-1, 1]
}]

export const modalAnimationList = [{
  atribute: 'top',
  inputRange: [0, 1],
  range: [-Dimensions.get('screen').height, Dimensions.get('screen').height/2 - 200]
}, {
  atribute: 'opacity',
  inputRange: [0, 1],
  range: [-1, 1]
}, {
  atribute: 'zIndex',
  inputRange: [0, 1],
  range: [-1, 1]
}]

export const openMenu = (animation) => {
  animation.map.get('zIndex').getAnimation(1, 0).start()
  animation.map.get('top').getAnimation(1, 500).start()
  animation.map.get('opacity').getAnimation(1, 500).start()
}

export const closeMenu = (animation) => {
  animation.map.get('zIndex').getAnimation(1, 0).start()
  animation.map.get('top').getAnimation(1, 0).start()
  animation.map.get('opacity').getAnimation(1, 0).start()
  animation.map.get('top').getAnimation(0, 500).start()
  animation.map.get('opacity').getAnimation(0, 500).start(() => {
    // animation.map.get('zIndex').getAnimation(0, 0).start()
  })
}

export const openModal = (animation) => {
  animation.map.get('zIndex').getAnimation(1, 0).start()
  animation.map.get('top').getAnimation(1, 500).start()
  animation.map.get('opacity').getAnimation(1, 500).start()
}

export const closeModal = (animation) => {
  animation.map.get('zIndex').getAnimation(1, 0).start()
  animation.map.get('top').getAnimation(1, 0).start()
  animation.map.get('opacity').getAnimation(1, 0).start()
  animation.map.get('top').getAnimation(0, 500).start()
  animation.map.get('opacity').getAnimation(0, 500).start(() => {
    // animation.map.get('zIndex').getAnimation(0, 0).start()
  })
}