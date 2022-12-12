package com.example.recetasdeazahara

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recetasdeazahara.databinding.FragmentHomeBinding


class FragmentHome : Fragment() ,  ActionOnClickListener {

    lateinit var  binding : FragmentHomeBinding
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var mAdapter:AzaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //se indica a donde se va a navegar
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragmentAdd_to_fragmentHome)
        }




    }
}