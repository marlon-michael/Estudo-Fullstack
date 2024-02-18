import Label from "./text/Label"



export default function Navigator(props) {
  return (
    props.tabs[props.activeTab] ?? <Label fontSize={20}>Não há nenhuma componente chamado: {props.activeTab}</Label>
  )
}
