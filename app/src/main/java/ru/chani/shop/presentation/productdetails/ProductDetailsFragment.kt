package ru.chani.shop.presentation.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bekawestberg.loopinglayout.library.LoopingLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import ru.chani.shop.databinding.FragmentProductDetailsBinding
import ru.chani.shop.domain.models.ProductModel
import ru.chani.shop.presentation.navigator
import ru.chani.shop.presentation.productdetails.images.CarouselLayoutManager
import ru.chani.shop.presentation.productdetails.images.ImagesAdapter
import ru.chani.shop.presentation.productdetails.info.ProductInfoAdapter
import ru.chani.shop.presentation.productdetails.util.StarManager
import ru.chani.shop.presentation.productdetails.util.ViewHasStar


class ProductDetailsFragment : Fragment(), ViewHasStar {

    private lateinit var demoCollectionAdapter: ProductInfoAdapter

    private var productId: Int = DEFAULT_ID

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding: FragmentProductDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentProductDetailsBinding == null")

    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var imagesRvAdapter: ImagesAdapter
    private lateinit var starManager: StarManager

    override val ivStar1: ImageView
        get() = binding.ivStar1
    override val ivStar2: ImageView
        get() = binding.ivStar2
    override val ivStar3: ImageView
        get() = binding.ivStar3
    override val ivStar4: ImageView
        get() = binding.ivStar4
    override val ivStar5: ImageView
        get() = binding.ivStar5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getInt(KEY_PRODUCT_ID)
        }

        val viewModelFactory = ProductDetailsViewModelFactory(requireContext(), productId)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(layoutInflater)

        setObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setListeners()
        starManager = StarManager(this)


    }

    private fun setObservers() {
        viewModel.product.observe(viewLifecycleOwner) {
            setupImageRv(it.images)
            binding.tvTitle.text = it.title
            starManager.setStarsByRating(it.rating)

            setTabLayout(product = it)

        }
    }

    private fun setListeners() {
        binding.btBack.setOnClickListener { navigator().goBack() }
    }

    private fun setupImageRv(productPicture: List<String>) {
        val carouselLayoutManager = CarouselLayoutManager(
            requireContext(),
            LoopingLayoutManager.HORIZONTAL,
            false
        )
        with(binding.rvImages) {
            imagesRvAdapter = ImagesAdapter()
            adapter = imagesRvAdapter
            imagesRvAdapter.setListOfImages(productPicture)
            layoutManager = carouselLayoutManager
            carouselLayoutManager.scrollToPosition(ImagesAdapter.MIDDLE_OF_LIST)

        }
    }

    private fun setTabLayout(product: ProductModel) {
        viewModel.categoryTitles.observe(viewLifecycleOwner) { listOfTitles ->
            demoCollectionAdapter = ProductInfoAdapter(
                product = product, productDetailsViewModel = viewModel, fragment = this
            )
            binding.viewPager.adapter = demoCollectionAdapter
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = listOfTitles[position]
            }.attach()
        }
    }


    companion object {
        const val FRAGMENT_NAME = "PRODUCT DETAILS"

        private const val KEY_PRODUCT_ID = "KEY_PRODUCT_ID"
        private const val DEFAULT_ID = 0

        @JvmStatic
        fun newInstance(productId: Int) =
            ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PRODUCT_ID, productId)
                }
            }
    }


}