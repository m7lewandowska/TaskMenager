package com.example.taskmenager

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_task_add.*
import kotlinx.android.synthetic.main.edit_task_fragment.*


class EditTaskFragment : Fragment() {

    private lateinit var dtbConnect: DTBConnect

    companion object {
        fun newInstance() = EditTaskFragment()
    }

    private lateinit var viewModel: EditTaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Toast.makeText(context, "All Tasks", Toast.LENGTH_LONG).show()

        return inflater.inflate(R.layout.edit_task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dtbConnect = DTBConnect(context!!)

        val idTask = arguments!!.getInt("idtasku")
       // Toast.makeText(context," ${idTask}", Toast.LENGTH_SHORT).show()

        val singletask = dtbConnect.getTaskById(idTask)

        val edittask_name = singletask.taskName
        val edittask_priority = singletask.taskPriority
        val edittask_status = singletask.taskStatus
        val edittask_date = singletask.taskDate
        val edittaks_desc = singletask.taskDescription

        dateoftask_edit.text = edittask_date
        taskname_edit.setText(edittask_name)
        taskdecsription_edit.setText(edittaks_desc)

        if(edittask_priority == "Important")
        {
            radiogroup3.check(R.id.important_edit)
        }
        else if(edittask_priority == "More important")
        {
            radiogroup3.check(R.id.moreimportant_edit)
        }

        else if(edittask_priority == "The most important")
        {
            radiogroup3.check(R.id.themostimportant_edit)
        }

        if(edittask_status == "TO DO")
        {
            radioGroup2.check(R.id.todo_edit)
        }
        else
        {
            radioGroup2.check(R.id.done_edit)
        }

        edit_button.setOnClickListener{

            val newname: String = taskname_edit.text.toString()

            val radio3: RadioButton = activity!!.findViewById(radiogroup3.checkedRadioButtonId)
            var newpriority: String = radio3.text.toString()

            val radio2: RadioButton = activity!!.findViewById(radioGroup2.checkedRadioButtonId)
            var newstatus: String = radio2.text.toString()

            val newdesc: String = taskdecsription_edit.text.toString()

            val newupdatedTask = Task(newname, newpriority,newstatus,edittask_date,newdesc)

            dtbConnect.updateTask(idTask,newupdatedTask)

            val mainActivity = activity as MainActivity
            mainActivity.ShowListViewFragment()
        }
    }
}
