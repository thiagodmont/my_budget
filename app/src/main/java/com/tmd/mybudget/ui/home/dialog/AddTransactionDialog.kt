package com.tmd.mybudget.ui.home.dialog

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import com.tmd.mybudget.R
import com.tmd.mybudget.base.BaseDialogFragment
import com.tmd.mybudget.base.viewBinding
import com.tmd.mybudget.databinding.DialogFragmentAddTransactionBinding
import com.tmd.mybudget.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTransactionDialog(private val requestKey: String? = null) :
    BaseDialogFragment(R.layout.dialog_fragment_add_transaction) {

    private val viewBind by viewBinding(DialogFragmentAddTransactionBinding::bind)
    private val viewModel: AddTransactionDialogViewModel by viewModel()

    override fun setupView(savedInstanceState: Bundle?) {
        super.setupView(savedInstanceState)

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_transaction_type,
            resources.getStringArray(R.array.listTransactionType)
        )

        viewBind.actvTransactionType.setAdapter(arrayAdapter)

        viewBind.tilTransactionType.editText?.setText(viewModel.transactionType.value)
        viewBind.tilTransactionType.editText?.addTextChangedListener {
            val transactionType = it.toString()
            viewModel.setTransactionType(transactionType)

            if (transactionType.isNotEmpty() && viewBind.tilTransactionType.isErrorEnabled) {
                viewBind.tilTransactionType.isErrorEnabled = false
            }
        }

        viewBind.tilTransactionDescription.editText?.setText(viewModel.transactionDescription.value)
        viewBind.tilTransactionDescription.editText?.addTextChangedListener {
            val description = it.toString()
            viewModel.setTransactionDescription(description)

            if (description.isNotEmpty() && viewBind.tilTransactionDescription.isErrorEnabled) {
                viewBind.tilTransactionDescription.isErrorEnabled = false
            }
        }

        viewBind.tilTransactionAmount.editText?.setText(viewModel.transactionAmount.value)
        viewBind.tilTransactionAmount.editText?.addTextChangedListener {
            val amount = it.toString()
            viewModel.setTransactionAmount(amount)

            if (amount.isNotEmpty() && viewBind.tilTransactionAmount.isErrorEnabled) {
                viewBind.tilTransactionAmount.isErrorEnabled = false
            }
        }

        viewBind.mbAddTransaction.setOnClickListener {
            viewModel.save()
        }
    }

    override fun setupObservers() {
        observe(viewModel.validation) {
            when(it) {
                is AddTransactionValidation.TransactionType -> {
                    viewBind.tilTransactionType.error = requireContext().getString(R.string.transactionTypeError)
                }
                is AddTransactionValidation.Description -> {
                    viewBind.tilTransactionDescription.error = requireContext().getString(R.string.descriptionError)
                }
                is AddTransactionValidation.Amount -> {
                    viewBind.tilTransactionAmount.error = requireContext().getString(R.string.amountError)
                }
            }
        }

        observe(viewModel.submit) {
            requestKey?.let {
                setFragmentResult(it, bundleOf())
            }

            dismiss()
        }
    }
}