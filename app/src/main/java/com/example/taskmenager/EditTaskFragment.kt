package com.example.taskmenager

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        edit_button.setOnClickListener{

            val edittask_name = singletask.taskName


            val mainActivity = activity as MainActivity
            mainActivity.ShowListViewFragment()

        }
    }

   /* override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditTaskViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //taskname_edit.text = "22"

    }*/

}
