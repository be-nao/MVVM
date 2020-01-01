package be_nao.mvvm_base.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import be_nao.mvvm_base.R
import be_nao.mvvm_base.databinding.FragmentHomeBinding
import be_nao.mvvm_base.di.ViewModelFactory
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        setBinding()
        observeViewModel()
        val text = "Hello toast!"
        val duration = Toast.LENGTH_SHORT

        Toast.makeText(context, text, duration).show()
    }

    private fun setBinding() {
    }

    private fun observeViewModel() {
        homeViewModel.text.observe(this, Observer {
            binding.textHome.text = it
        })
    }
}
