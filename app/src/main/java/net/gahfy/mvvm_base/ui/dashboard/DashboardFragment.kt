package net.gahfy.mvvm_base.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import net.gahfy.mvvm_base.CustomToast
import net.gahfy.mvvm_base.R
import net.gahfy.mvvm_base.databinding.FragmentDashboardBinding
import net.gahfy.mvvm_base.di.ViewModelFactory
import net.gahfy.mvvm_base.observe
import javax.inject.Inject

class DashboardFragment : DaggerFragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
        setBinding()
        observeViewModel()
    }

    private fun setBinding() {
        binding.apply {
            viewModel = this@DashboardFragment.viewModel
            lifecycleOwner = this@DashboardFragment.viewLifecycleOwner
        }
    }

    private fun observeViewModel() {
        viewModel.randomUser.observe(viewLifecycleOwner) {
            it?.let {
                binding.textDashboard.text = it.results[0].gender
            }
        }

        viewModel.errorObserver.observe(viewLifecycleOwner) {
            CustomToast(context).showError(it)
        }
    }
}
