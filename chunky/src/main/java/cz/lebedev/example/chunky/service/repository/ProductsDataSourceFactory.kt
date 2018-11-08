package cz.lebedev.example.chunky.service.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import cz.lebedev.example.chunky.service.model.Product
import io.reactivex.disposables.CompositeDisposable

class ProductsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val productService: ProductService)
: DataSource.Factory<Int, Product>() {

    val productsDataSourceLiveData = MutableLiveData<ProductsDataSource>()

    override fun create(): DataSource<Int, Product> {
        val productsDataSource = ProductsDataSource(productService, compositeDisposable)
        productsDataSourceLiveData.postValue(productsDataSource)
        return productsDataSource
    }
}