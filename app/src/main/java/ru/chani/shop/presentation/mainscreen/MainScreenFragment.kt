package ru.chani.shop.presentation.mainscreen

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import ru.chani.shop.databinding.FragmentMainScreenBinding
import ru.chani.shop.domain.models.HomeStoreModel
import ru.chani.shop.domain.models.LocationsModel
import ru.chani.shop.presentation.mainscreen.bestseller.BestSellerAdapter
import ru.chani.shop.presentation.mainscreen.category.CategoryAdapter
import ru.chani.shop.presentation.mainscreen.filters.Filters
import ru.chani.shop.presentation.mainscreen.util.ActionOnRightSwipe
import ru.chani.shop.presentation.mainscreen.util.OnSwipeTouchListener


class MainScreenFragment : Fragment(), ActionOnRightSwipe {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentMainScreenBinding == null")

    private lateinit var filters: Filters

    private lateinit var viewModel: MainViewModel

    private lateinit var categoryRvAdapter: CategoryAdapter
    private lateinit var bestSellerRvAdapter: BestSellerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        val viewModelFactory = MainViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater)
        filters = Filters(binding.root, layoutInflater, requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCategoryRecyclerView()
        setupBestSellerRecyclerView()
        setObservers()
        setOnSwipeListeners()
        setOnClickListeners()
    }

    private fun setObservers() {
        viewModel.currentHomeStore.observe(viewLifecycleOwner) {
            setHomeStore(it)
        }

        viewModel.bestSeller.observe(viewLifecycleOwner) {
            bestSellerRvAdapter.setListOfBestSellers(it)
        }


        viewModel.categories.observe(viewLifecycleOwner) {
            categoryRvAdapter.setListOfCategories(it)
        }


        viewModel.locations.observe(viewLifecycleOwner) {
            setUpLocations(it)
        }
    }

    private fun setHomeStore(homeStore: HomeStoreModel) {
        with(binding) {
            if (homeStore.is_new) {
                ivNew.visibility = View.VISIBLE
            } else {
                ivNew.visibility = View.INVISIBLE
            }

            tvHsTitle.text = homeStore.title
            tvShSubtitle.text = homeStore.subtitle

            Picasso.get().load(homeStore.picture).into(ivHsBg)
        }

    }

    private fun setUpLocations(locations: LocationsModel) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), R.layout.simple_spinner_item, locations.places)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        binding.spinner.adapter = adapter
    }

    private fun setupCategoryRecyclerView() {
        with(binding.rvCategory) {
            categoryRvAdapter = CategoryAdapter()
            adapter = categoryRvAdapter
            itemAnimator = null
        }
    }

    private fun setupBestSellerRecyclerView() {
        with(binding.rvBestSeller) {
            bestSellerRvAdapter = BestSellerAdapter()
            adapter = bestSellerRvAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
            itemAnimator = null
        }
    }

    private fun setOnClickListeners() {
        categoryRvAdapter.onCategoryItemClickListener = { category ->
            viewModel.changeActiveCategory(category = category)
        }


        bestSellerRvAdapter.onBestSellerItemFavoriteClickListener = { sellerModel ->
            viewModel.changeBestSellerItemFavoriteState(bestSellerModel = sellerModel)
        }

        binding.btFilter.setOnClickListener {
            filters.showPopupWindow()
        }
    }

    private fun setOnSwipeListeners() {
        binding.cvHotSales.setOnTouchListener(OnSwipeTouchListener(requireContext(),this ))
    }

    override fun rightSwipe() {
        viewModel.nextHomeStore()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainScreenFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

