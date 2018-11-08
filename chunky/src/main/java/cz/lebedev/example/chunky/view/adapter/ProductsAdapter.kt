package cz.lebedev.example.chunky.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cz.lebedev.example.chunky.R
import cz.lebedev.example.chunky.service.model.Product
import cz.lebedev.example.chunky.databinding.ProductItemBinding


class ProductsAdapter(private val retry: () -> Unit) : PagedListAdapter<Product, RecyclerView.ViewHolder>(ProductsDiffCallback) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        (holder as ProjectViewHolder).binding.product = product
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil.inflate<ProductItemBinding>(LayoutInflater.from(parent.context), R.layout.product_item, parent, false)
        return ProjectViewHolder(binding)
    }


    class ProjectViewHolder(var binding: ProductItemBinding ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val ProductsDiffCallback = object : DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.title.equals(newItem.title)
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return (oldItem.title.equals(newItem.title ) && oldItem.image.equals(newItem.image) && oldItem.price.equals(newItem.price))
            }
        }
    }
}