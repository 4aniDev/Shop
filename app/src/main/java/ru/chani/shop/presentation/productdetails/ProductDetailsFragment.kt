package ru.chani.shop.presentation.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.chani.shop.databinding.FragmentProductDetailsBinding


class ProductDetailsFragment : Fragment() {

    private var productId: Int = DEFAULT_ID

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding: FragmentProductDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentProductDetailsBinding == null")

    private lateinit var viewModel: ProductDetailsViewModel



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

    }

    private fun setObservers() {
        viewModel.product.observe(viewLifecycleOwner) {
            binding.tv.text = it.toString()
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