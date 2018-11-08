package cz.lebedev.example.chunky.di

import cz.lebedev.example.chunky.view.ui.ProductsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeProjectsFragment() : ProductsFragment


}