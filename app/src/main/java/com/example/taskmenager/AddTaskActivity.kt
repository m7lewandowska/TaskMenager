package com.example.taskmenager

import android.os.Build
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_task_add.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddTaskActivity : AppCompatActivity() {

    private var dtbConnect: DTBConnect = DTBConnect(this)
    private var mainActivity: MainActivity= MainActivity()
    private var taskModel: TaskModel = TaskModel()
    private var tasklistfragment: TaskListFragment = TaskListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_add)

        button_add.setOnClickListener {
            btnAddTask()
        }

    }

    fun btnAddTask()
    {
        var taskname: String = taskname_add.text.toString()
        var taskdate = ""
        val taskstatus: String = "TO DO"
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radiogroup.checkedRadioButtonId)
        var taskpriority: String = radio.text.toString()
        //Toast.makeText(applicationContext,"On click : ${taskpriority}", Toast.LENGTH_SHORT).show()
        var taskdescription: String = description_add.text.toString()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            taskdate = current.format(formatter).toString()
        }

        Toast.makeText(applicationContext,"Task was added to your list", Toast.LENGTH_SHORT).show()

        taskModel.addTask(taskname, taskpriority, taskstatus, taskdate, taskdescription)
        var newtask =Task(taskname, taskpriority, taskstatus, taskdate, taskdescription)
        dtbConnect.addTaskToDTB(newtask)


        tasklistfragment.ClearListView()
       // tasklistfragment.ClearListView(taskModel.getTask() as ArrayList<Task>)

        var tasks = dtbConnect.getTaskFromDTB()

        //Dodanie do listView_taskss wszystkich taskow
      //  tasklistfragment.AddToListView(tasks as ArrayList<Task>)
        finish()

    }


}