package cz.lebedev.example.chunky.service.repository

import androidx.paging.PageKeyedDataSource
import cz.lebedev.example.chunky.service.model.Product
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class ProductsDataSource (
        private val productService : ProductService,
        private val compositeDisposable: CompositeDisposable)
        : PageKeyedDataSource<Int, Product>()   {

       private var retryCompletable: Completable? = null

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
                compositeDisposable.add(
                        productService.getProducts(1)
                                .subscribe(
                                        {
                                               callback.onResult(it.distinct(),
                                                        null,
                                                        2
                                                )
                                        },
                                        {
                                                setRetry(Action { loadInitial(params, callback) })
                                        }
                                )
                )
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
                compositeDisposable.add(
                        productService.getProducts(params.key)
                                .subscribe(
                                        {
                                                callback.onResult(it.distinct(),
                                                        params.key + 1
                                                )
                                        },
                                        {
                                                setRetry(Action { loadAfter(params, callback) })
                                        }
                                )
                )
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        }


        fun retry() {
                if (retryCompletable != null) {
                        compositeDisposable.add(retryCompletable!!
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe())
                }
        }


        private fun setRetry(action: Action?) {
                retryCompletable = if (action == null) null else Completable.fromAction(action)
        }
}