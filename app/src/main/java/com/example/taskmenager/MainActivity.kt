package com.example.taskmenager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var taskModel: TaskModel = TaskModel()
    private var dtbConnect: DTBConnect = DTBConnect(this)
    private lateinit var listView: ListView

   // private val fragmentManager = supportFragmentManager
    //private val editFragment = EditTaskFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

       // val fragment = EditTaskFragment.newInstance()
       // ShowFragment(fragment)

       /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Hello my friend", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        listView = findViewById(R.id.listView_tasks)

        val listofTasks = mutableListOf<Task>()

        val adapter = ArrayAdapter(this, android.R.layout.activity_list_item, listofTasks)
        listView.adapter = adapter

        var taskController = TaskController(this, taskModel, dtbConnect)

        listView = findViewById<ListView>(R.id.listView_tasks)


    }

    fun AddToListView(listView_tasks: ArrayList<Task>){
        val listOfTasks = listView_tasks
        val adapter = TaskAdapter(this, listOfTasks)
        listView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.menuaddtask -> changeActivity()
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }

        fun changeActivity() {

            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
    }

    fun ClearListView(listView_tasks: ArrayList<Task>){

        val listofTasks = listView_tasks
        val adapter = TaskAdapter(this, listofTasks)
        listView.adapter = adapter

        listofTasks.clear()
        adapter.notifyDataSetChanged();
    }

}
