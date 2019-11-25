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

    companion object {
        fun newInstance() = EditTaskFragment()
    }

    private lateinit var viewModel: EditTaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_task_fragment, container, false)
        Toast.makeText(context, "test1 - edycja", Toast.LENGTH_LONG).show()

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
