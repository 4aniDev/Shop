package ru.chani.shop.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.chani.shop.R
import ru.chani.shop.databinding.ActivityMainBinding
import ru.chani.shop.presentation.mainscreen.MainScreenFragment
import ru.chani.shop.presentation.productdetails.ProductDetailsFragment

class MainActivity : AppCompatActivity(), Navigator {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")


    private var selectedTab = TAB_1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                MainScreenFragment.newInstance(),
                MainScreenFragment.FRAGMENT_NAME
            )
            .commit()
        supportFragmentManager.addOnBackStackChangedListener {
            setRightVisibleModeForNavBar()
        }



        binding.llExplorer.setOnClickListener {
            if (selectedTab != TAB_1) {

                binding.ivExplorer.setImageResource(R.drawable.rounded)
                binding.ivCart.setImageResource(R.drawable.cart)
                binding.ivFavorite.setImageResource(R.drawable.favorite)
                binding.ivProfile.setImageResource(R.drawable.profile)

                binding.tvExplorer.visibility = View.VISIBLE
                binding.tvCart.visibility = View.GONE
                binding.tvFavorite.visibility = View.GONE
                binding.tvProfile.visibility = View.GONE

                selectedTab = TAB_1
            }
        }

        binding.llCart.setOnClickListener {
            if (selectedTab != TAB_2) {


                selectedTab = TAB_2
            }
        }

    }


    //  if on Display MainScreenFragment - Navigation Bar == VISIBLE
    private fun setRightVisibleModeForNavBar() {
        val mainScreenFragment: MainScreenFragment? =
            supportFragmentManager.findFragmentByTag(MainScreenFragment.FRAGMENT_NAME) as MainScreenFragment?
        if (mainScreenFragment != null && mainScreenFragment.isVisible) {
            binding.navBar.visibility = View.VISIBLE
        }
    }


    override fun goToProductDetails(id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ProductDetailsFragment.newInstance(productId = id))
            .addToBackStack(ProductDetailsFragment.FRAGMENT_NAME)
            .commit()

        binding.navBar.visibility = View.GONE
    }

    override fun goBack() {
        onBackPressed()
    }


    companion object {
        private const val TAB_1 = 1
        private const val TAB_2 = 2
        private const val TAB_3 = 3
        private const val TAB_4 = 4
    }


}