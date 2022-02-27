package com.example.roomdatabase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.database.StoreNameDataBase
import com.example.roomdatabase.databinding.FragmentDisplayBinding

class DisplayFragment : Fragment() {

    private lateinit var binding : FragmentDisplayBinding
    private lateinit var viewModel: DisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_display, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        val dataSource = StoreNameDataBase.getInstance(application).dataDao

        val viewModelFactory = DisplayViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this,viewModelFactory).get(DisplayViewModel::class.java)

        viewModel.allname?.observe(this, Observer {
            binding.displayTextView.text = viewModel.allname?.value.toString()
        })
        binding.ClearDataButton.setOnClickListener {
            viewModel.onClear()
        }
//        binding.ShowDataButton.setOnClickListener {
//            viewModel.getData()
//        }
        return binding.root
    }

}