package cz.lebedev.example.chunky.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cz.lebedev.example.chunky.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(),HasSupportFragmentInjector{

    @Inject
    lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            val fr = ProductsFragment()
            supportFragmentManager.beginTransaction().replace(R.id.content, fr,
                ProductsFragment.TAG
            ).commit()
        }

    }
}