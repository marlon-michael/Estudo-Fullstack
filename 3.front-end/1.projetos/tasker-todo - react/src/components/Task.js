import React, { useEffect, useRef, useState } from "react";


export default function Task(props){
    const [id] = useState(props.task.id)
    const [editmode, setEditmode] = useState(false)
    const [state, setState] = useState(props.task.state ? props.task.state : "UNDONE")
    const [title, setTitle] = useState(props.task.title)
    const editInput = useRef(null)

    const toggleEditmode = () => editmode ? setEditmode(false): setEditmode(true); props.task.state = state
    const changeState = () => state === "DONE"? setState("UNDONE") : setState("DONE");
    const deleteTask = () => props.deleteTask(id)
    const updateText = (e) => setTitle(e.target.value); props.task.title = title
    const updateStorage = () => props.updateStorage(true)

    useEffect(()=> editmode ? editInput.current.focus() : undefined, [editmode])
    useEffect(()=> updateStorage()) //[title, state]
 
    return (
        <div className="task">
            <div className="state-field"><button onClick={changeState} className={"state-button " + state}></button></div>
            {editmode? 
                <input ref={editInput} value={title} onChange={updateText} className="task-input"></input>:
                <p className="task-title">{title.length > 0 ? title : "do something"}</p>
            }
            <div className="button-field">
                <button className="task-button" onClick={toggleEditmode}>{editmode? <div>save</div> : <div>edit</div>}</button>
                <button className="task-button" onClick={deleteTask}>delete</button>
            </div>
        </div>
    );
}