package com.tmd.mybudget.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DefaultItemAnimator
import com.tmd.mybudget.R
import com.tmd.mybudget.base.BaseFragment
import com.tmd.mybudget.base.ViewState
import com.tmd.mybudget.base.viewBinding
import com.tmd.mybudget.databinding.FragmentHomeBinding
import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.extensions.observe
import com.tmd.mybudget.ui.home.dialog.AddTransactionDialog
import com.tmd.mybudget.utils.MoneyFormat
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewBind by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModel()
    private val adapter: TransactionHistoryAdapter by inject()

    override fun setupView(savedInstanceState: Bundle?) {
        super.setupView(savedInstanceState)
        configureRecyclerView()

        adapter.setOnItemClickListener(object : TransactionHistoryAdapter.OnItemClickListener {
            override fun onItemClick(model: TransactionModel) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage(requireContext().getString(R.string.question_delete_transaction))
                    .setCancelable(false)
                    .setPositiveButton(requireContext().getString(R.string.yes)) { _, _ ->
                        viewModel.delete(id = model._id)
                    }
                    .setNegativeButton(requireContext().getString(R.string.no)) { dialog, _ ->
                        dialog.dismiss()
                    }

                val alert = builder.create()
                alert.show()
            }
        })

        viewBind.fabAddFinancialReport.setOnClickListener {
            AddTransactionDialog(REQUEST_KEY_ADD_TRANSACTION).show(
                parentFragmentManager,
                TAG_FRAGMENT_DIALOG
            )
        }

        viewModel.fetch()

        viewBind.srRefresher.setOnRefreshListener {
            viewModel.fetch()
        }

        setFragmentResultListener(REQUEST_KEY_ADD_TRANSACTION) { _, _ ->
            viewModel.fetch()
        }
    }

    override fun setupObservers() {
        observe(viewModel.transactions) {
            if (it.isNotEmpty()) {
                viewBind.tvEmptyMessage.visibility = View.GONE
                adapter.populate(it)
            } else {
                adapter.populate(listOf())
                viewBind.tvEmptyMessage.visibility = View.VISIBLE
            }
        }

        observe(viewModel.viewState) {
            when(it) {
                ViewState.LOADING -> viewBind.srRefresher.isRefreshing = true
                ViewState.ERROR -> viewBind.srRefresher.isRefreshing = false
                ViewState.LOADED, ViewState.INITIAL -> {
                    viewBind.srRefresher.isRefreshing = false
                }
            }
        }

        observe(viewModel.balance) { balance ->
            viewBind.tvIncomeAmount.text = MoneyFormat.format(balance.income)
            viewBind.tvExpensesAmount.text = MoneyFormat.format(balance.expenses)
            viewBind.tvBalanceAmount.text = MoneyFormat.format(balance.balance)
            viewBind.pbRatioExpensesIncome.progress = balance.ratio
        }
    }

    private fun configureRecyclerView() {
        viewBind.rvTransactions.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = this@HomeFragment.adapter
        }
    }

    companion object {
        private const val REQUEST_KEY_ADD_TRANSACTION = "requestKeyAddTransaction"
        private const val TAG_FRAGMENT_DIALOG = "AddTransactionDialog"
    }
}