package com.example.taskmenager


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * A simple [Fragment] subclass.
 */
class TaskListFragment : Fragment() {

    private var taskModel: TaskModel = TaskModel()
    private lateinit var dtbConnect: DTBConnect
    private lateinit var listView: ListView
    val listofTasks = mutableListOf<Task>()
    private lateinit var taskController:TaskController
    private lateinit var adapter:TaskAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment





       // val adapter = ArrayAdapter(this, android.R.layout.activity_list_item, listofTasks)
       // listView.adapter = adapter



        //listView = findViewById<ListView>(R.id.listView_tasks)
        return inflater.inflate(R.layout.fragment_task_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = activity?.findViewById(R.id.listView_tasks)!!
        //!! - wskazuje na to ze zmienna, ktora przekazujemy nie jest nullem
        dtbConnect = DTBConnect(context!!)
        listofTasks.addAll(dtbConnect.getTaskFromDTB())

        AddToListView(listofTasks.toList())

    }

    fun AddToListView(listView_tasks: List<Task>){
        adapter = TaskAdapter(context as MainActivity, listView_tasks)
        listView.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        ClearListView()
    }

    override fun onStart() {
        super.onStart()
        ClearListView()
    }

    fun ClearListView(){

        listofTasks.clear()
        listofTasks.addAll(dtbConnect.getTaskFromDTB())


//        val adapter = TaskAdapter(context!!, listofTasks)
//        listView.adapter = adapter

        adapter.notifyDataSetChanged();
    }

}
