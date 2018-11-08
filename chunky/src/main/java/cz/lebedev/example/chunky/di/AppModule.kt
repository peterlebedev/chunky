package cz.lebedev.example.chunky.di

import androidx.lifecycle.ViewModelProvider
import cz.lebedev.example.chunky.service.repository.ProductService

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
class AppModule{

    @Singleton
    @Provides
    fun provideProductService() : ProductService {
        return Retrofit.Builder().baseUrl(ProductService.API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ProductService::class.java)
    }


    @Singleton
    @Provides
    fun provideViewModelFactory(
            builder: ViewModelSubComponent.Builder
    ): ViewModelProvider.Factory {
        return ViewModelFactory(builder.build())
    }
}