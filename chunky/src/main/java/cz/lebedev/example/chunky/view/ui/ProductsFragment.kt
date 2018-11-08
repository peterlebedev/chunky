package cz.lebedev.example.chunky.view.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import cz.lebedev.example.chunky.R
import cz.lebedev.example.chunky.view.adapter.ProductsAdapter
import cz.lebedev.example.chunky.viewModel.ProductsViewModel
import dagger.android.support.AndroidSupportInjection
import cz.lebedev.example.chunky.databinding.ProductsFragmentBinding
import javax.inject.Inject

class ProductsFragment : Fragment() {

    private lateinit var binding: ProductsFragmentBinding
    private lateinit var productsAdapter : ProductsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<ProductsFragmentBinding
                >(LayoutInflater.from(container!!.context), R.layout.products_fragment, container, false)

        return binding.root
    }

    private lateinit var projectsViewModel: ProductsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
       projectsViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(ProductsViewModel::class.java!!)

        productsAdapter = ProductsAdapter{projectsViewModel.retry()}
        binding.myrecycler.adapter = productsAdapter
        binding.isLoading = true



        projectsViewModel.productsList.observe(this,Observer {
            productsAdapter.submitList(it)
                binding.isLoading = false
        })




//        projectsViewModel.getObservableErrors().observe(this,Observer {
//            binding.errorMessage = it
//        })


        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        val TAG : String = "ProjectsFragment"
    }

}