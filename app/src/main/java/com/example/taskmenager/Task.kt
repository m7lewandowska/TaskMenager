package com.example.taskmenager

class Task(name: String, priority: String, status: String, date: String, description: String){

    var taskName = name
    var taskPriority = priority
    var taskStatus = status
    var taskDate = date
    var taskDescription = description

    companion object {

        var ID = 1
    }

    var id: Int = 1

    init {

        id = ID
        ID++

    }

}
