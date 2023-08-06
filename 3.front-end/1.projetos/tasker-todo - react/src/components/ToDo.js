import React, { useEffect, useState } from "react";
import Task from "./Task"


export default function ToDo(){
    const [taskId, setTaskId] = useState(0)
    const [tasks,setTasks] = useState([])
    const [searchBarText, setSearchBarText] = useState("")
    const [storageNeedsUpdate, setStorageUpdate] = useState(false)

    const nextTaskId = () => {
        setTaskId(id => id+1)
        return taskId
    }
    
    const addTask = (storageTasks) => {
        if (!storageTasks[0]?.title.length > 0) storageTasks = [{title: "", state: "UNDONE"}]
        let id = nextTaskId()
        let newTaskList = [...tasks]
        storageTasks.forEach(task => {
            newTaskList.push(
                <Task key={id} task={{id, title:task.title, state:task.state}}
                deleteTask={deleteTask} updateStorage={setStorageUpdate}></Task>
            )
            id++
        })
        setTaskId(id--)
        setTasks(newTaskList)
    }

    const deleteTask = (id) => setTasks (old => old.filter((task) => task?.props.task.id !== id))
    const taskRenderer = () => {return [...tasks].filter((task) => task?.props.task.title.indexOf(searchBarText) !== -1)}
    const updateSearchBar = (e) => setSearchBarText(e.target.value)

    useEffect(() => {
        let tasksObj = JSON.parse(localStorage.getItem('Tasker.tasks'))
        if (tasksObj === undefined || tasksObj === null || tasksObj === [] || tasks.length > 0) return
        addTask(tasksObj)
    })

    useEffect(()=> {
        setStorageUpdate(false)
        let tasksObj = []
        tasks.forEach(task => tasksObj.push({title: task.props.task.title,state: task.props.task.state}))
        localStorage.setItem('Tasker.tasks', JSON.stringify(tasksObj))
    } ,[storageNeedsUpdate, tasks])
    
    return (
        <div className='todo-section'>
            <input className='todo-search' onChange={updateSearchBar} value={searchBarText} placeholder='search your task here'></input>
            <div className="todo">
                {taskRenderer()}
            </div>
            <button className="bottom" onClick={addTask}>add task</button>
        </div>
    );
}