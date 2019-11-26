package com.example.taskmenager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.content_main.*

class TaskAdapter(private val context: Context, private var dataSource: List<Task>): BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var mainActivity: MainActivity = context as MainActivity
    private var dtbConnect: DTBConnect = DTBConnect(context)
    //private var taskModel: TaskModel = TaskModel()
    //private lateinit var tasklistfragment: TaskListFragment

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView = inflater.inflate(R.layout.layout_task, parent, false)

        //val idView =

        val nameTextView = rowView.findViewById(R.id.taskname_layout) as TextView

        val proriotyTextView = rowView.findViewById(R.id.priority_layout) as TextView

        val statusTextView = rowView.findViewById(R.id.status_layout) as TextView

        val dateTextView = rowView.findViewById(R.id.date_layout) as TextView

        val task = getItem(position) as Task

        // co ma sie wywolac po wcisnieciu pojedynczego wiersza - tasku
        rowView.setOnClickListener{

            Toast.makeText(context, "Edit Task", Toast.LENGTH_LONG).show()
            val fragment = EditTaskFragment.newInstance()

            var idtasku =  dtbConnect.getIdFromDTB(task)
            val bundle = Bundle()
            bundle.putInt("idtasku",idtasku)
            fragment.arguments = bundle
            ShowFragment(fragment)



        }

        //Button del on listView
        val delButton = rowView.findViewById<Button>(R.id.butondel_layout)
        delButton.setOnClickListener {
            var idTSK = dtbConnect.getIdFromDTB(task)
            //Toast.makeText(context," ${idTSK}", Toast.LENGTH_SHORT).show()
            dtbConnect.delTaskFromDTB(idTSK)
            dataSource = dtbConnect.getTaskFromDTB().toList()
            notifyDataSetChanged()


        }

        nameTextView.text = task.taskName.toString()
        proriotyTextView.text = task.taskPriority.toString()
        statusTextView.text = task.taskStatus.toString()
        dateTextView.text = task.taskDate.toString()

        return rowView
    }

    fun ShowFragment(fragment: Fragment){
        val manager = mainActivity.supportFragmentManager
        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, fragment)
        fragmentTransaction.commit()

    }
}