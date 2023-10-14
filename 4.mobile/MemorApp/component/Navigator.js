import TextTag from "./basic/text/TextTag"



export default function Navigator(props) {
  return (
    props.tabs[props.activeTab] ?? <TextTag fontSize={20}>Não há nenhuma componente chamado: {props.activeTab}</TextTag>
  )
}
