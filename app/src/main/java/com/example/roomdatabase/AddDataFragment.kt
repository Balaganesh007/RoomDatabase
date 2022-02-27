package com.example.roomdatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.database.StoreNameDataBase
import com.example.roomdatabase.databinding.FragmentAddDataBinding

class AddDataFragment : Fragment() {

    private  lateinit var viewModel: AddViewModel
    private lateinit var binding: FragmentAddDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_data, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = StoreNameDataBase.getInstance(application).dataDao

        val viewModelFactory = AddViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this,viewModelFactory).get(AddViewModel::class.java)
        binding.addViewModel = viewModel
        binding.lifecycleOwner = this

        binding.addButton.setOnClickListener {
            moveData()
        }

        viewModel.name.observe(this, Observer {
            newName -> viewModel.name.value.toString()
        })
        viewModel.navigateToDisplayFragment.observe(viewLifecycleOwner,Observer{
            if(it == true) {
                this.findNavController().navigate(R.id.action_addDataFragment_to_displayFragment)
                viewModel.doneNavigation()
            }
        })
        binding.NextButton.setOnClickListener {
            val value = true
            viewModel.setNavigation(value)
        }


        return binding.root
    }
    private fun moveData(){
        val name = binding.editTextTextPersonName.text.toString()
        viewModel.setName(name)
    }

}