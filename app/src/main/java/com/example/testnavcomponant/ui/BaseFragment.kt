package com.example.testnavcomponant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testnavcomponant.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class BaseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navHostFragment.navController)
        return view
    }

}