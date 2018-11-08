package cz.lebedev.example.chunky.di

import cz.lebedev.example.chunky.viewModel.ProductsViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun projectsViewModel(): ProductsViewModel
}
