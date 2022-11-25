package ru.chani.shop.presentation.productdetails.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.chani.shop.databinding.FragmentInfoShopBinding
import ru.chani.shop.domain.models.ProductModel
import ru.chani.shop.presentation.productdetails.ProductDetailsViewModel
import ru.chani.shop.presentation.productdetails.info.capacity.CapacityAdapter
import ru.chani.shop.presentation.productdetails.info.color.ColorAdapter
import java.text.NumberFormat
import java.util.*


class InfoShopFragment : Fragment() {
    private var product: ProductModel? = null
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var colorsRvAdapter: ColorAdapter
    private lateinit var capacityRvAdapter: CapacityAdapter

    private var _binding: FragmentInfoShopBinding? = null
    private val binding: FragmentInfoShopBinding
        get() = _binding ?: throw RuntimeException("FragmentInfoShopBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getParcelable(ARG_PRODUCT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoShopBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCpu.text = product?.CPU
        binding.tvCamera.text = product?.camera
        binding.tvRam.text = product?.ssd
        binding.tvSd.text = product?.sd

        binding.tvPrice.text = product?.let { getPrice(it.price) }

        setColorsRecyclerView()
        setCapacityRecyclerView()


        viewModel.colorList.observe(viewLifecycleOwner) {lisOfColorsModel ->
            colorsRvAdapter.setListOfColors(lisOfColorsModel)
        }

        viewModel.capacityList.observe(viewLifecycleOwner) {listOfCapacities ->
            capacityRvAdapter.setListOfCapacities(listOfCapacities)
        }
    }

    private fun setColorsRecyclerView() {
        with(binding.rvColors) {
            colorsRvAdapter = ColorAdapter()
            adapter = colorsRvAdapter
            itemAnimator = null
        }

        colorsRvAdapter.onColorItemClickListener = {
            viewModel.changeCheckedColor(it)
        }
    }

    private fun setCapacityRecyclerView() {
        with(binding.rvCapacity) {
            capacityRvAdapter = CapacityAdapter()
            adapter = capacityRvAdapter
            itemAnimator = null
        }

        capacityRvAdapter.onCapacityItemClickListener = {
            viewModel.changeCheckedCapacity(it)
        }
    }

    private fun getPrice(int: Int): String {
        val long = int.toLong()
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("USD")

        return format.format(long)
    }




    companion object {
        private const val ARG_PRODUCT = "ARG_PRODUCT"

        @JvmStatic
        fun newInstance(product: ProductModel, productDetailsViewModel: ProductDetailsViewModel) =
            InfoShopFragment().apply {
                viewModel = productDetailsViewModel
                arguments = Bundle().apply {
                    putParcelable(ARG_PRODUCT, product)
                }
            }

    }
}