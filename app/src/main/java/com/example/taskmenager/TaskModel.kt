package com.example.taskmenager

class TaskModel() {
    private var tasksList = mutableListOf<Task>()

    fun addTask(name: String, priority: String, status: String, date: String, description: String) {
        var newTask = Task(name, priority, status, date, description )
        tasksList.add(newTask)
    }

    fun getTask(): MutableList<Task> {
        return tasksList
    }

}