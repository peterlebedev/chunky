package cz.lebedev.example.chunky.service.repository

import cz.lebedev.example.chunky.service.model.Product
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService{
    @GET("products.php/page={page}")
    fun getProducts(@Query("page") page:Int) : Single<List<Product>>
    companion object {
        val API = "https://stark-atoll-33661.herokuapp.com/"
    }

}