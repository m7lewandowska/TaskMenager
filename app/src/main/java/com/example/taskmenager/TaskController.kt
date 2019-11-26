package com.example.taskmenager


class TaskController(activity: MainActivity, taskModel: TaskModel, dtbConnect: DTBConnect, taskListFragment: TaskListFragment) {

    //private var taskModel = TaskModel
    private var activity = activity
    private var tasklistfragment = taskListFragment
    private var dtbConnect = dtbConnect

    init {

        var tasks = dtbConnect.getTaskFromDTB()

        tasks.sortByDescending { it.taskPriority }

        //Dodanie do listView_users wszystkich userów
        taskListFragment.AddToListView(tasks as ArrayList<Task>)
    }

}