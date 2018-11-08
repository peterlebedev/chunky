package cz.lebedev.example.chunky.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import cz.lebedev.example.chunky.service.model.Product
import cz.lebedev.example.chunky.service.repository.ProductService
import cz.lebedev.example.chunky.service.repository.ProductsDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProductsViewModel @Inject
constructor(productService: ProductService, application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    var productsList: LiveData<PagedList<Product>>


    private var productsDataSourceFactory: ProductsDataSourceFactory

    init {
        productsDataSourceFactory = ProductsDataSourceFactory(compositeDisposable, productService)
        val config = PagedList.Config.Builder()
            .setPageSize(2)
            .setInitialLoadSizeHint(4)
            .setEnablePlaceholders(false)
            .build()

        productsList = LivePagedListBuilder<Int, Product>(productsDataSourceFactory, config).build()
    }


    fun retry() {
        productsDataSourceFactory.productsDataSourceLiveData.value?.retry()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
