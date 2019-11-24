package com.example.taskmenager


class TaskController(activity: MainActivity, taskModel: TaskModel, dtbConnect: DTBConnect) {

    //private var taskModel = TaskModel
    private var activity = activity
    private var dtbConnect = dtbConnect

    init {

        var tasks = dtbConnect.getTaskFromDTB()

        tasks.sortByDescending { it.taskPriority }

        //Dodanie do listView_users wszystkich userów
        activity.AddToListView(tasks as ArrayList<Task>)
    }

}