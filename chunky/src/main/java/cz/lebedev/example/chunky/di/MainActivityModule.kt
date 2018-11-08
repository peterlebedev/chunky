package cz.lebedev.example.chunky.di

import cz.lebedev.example.chunky.view.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentsModule::class))
    abstract fun contributeMainActivity() : MainActivity


}