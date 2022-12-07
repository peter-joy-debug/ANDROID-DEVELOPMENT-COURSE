package com.example.lab_4.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {
    var taskList = mutableStateListOf<Task>()

    fun addNewTask(newTask: Task)
    {
        taskList.add(newTask)
    }

    fun deleteTask(task: Task)
    {
        taskList.remove(task)
    }
    fun updateTask(task:Task)
    {
        var index = -1
        var i = 0
        for(item in taskList)
        {
            if (item.taskId == task.taskId) {
                break
            }
            i = i + 1
        }
        index = i
        taskList.set(index, Task(task.taskId,task.taskTitle,task.taskDescription,true))
    }

    fun updateTaskFalse(task:Task)
    {
        var index = -1
        var i = 0
        for(item in taskList)
        {
            if (item.taskId == task.taskId) {
                break
            }
            i = i + 1
        }
        index = i
        taskList.set(index, Task(task.taskId,task.taskTitle,task.taskDescription,false))
    }

}