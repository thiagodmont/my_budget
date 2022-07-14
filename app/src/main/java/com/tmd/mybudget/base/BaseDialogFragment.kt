package com.tmd.mybudget.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import timber.log.Timber

abstract class BaseDialogFragment(
    @LayoutRes private val layoutId: Int
) : DialogFragment(layoutId) {

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.tag(BaseDialogFragment.LOG_DIALOG_FRAGMENT_NAME_INFO).d(this::class.java.simpleName)

        setupView(savedInstanceState)
        setupObservers()
    }

    protected open fun setupView(savedInstanceState: Bundle?) {}

    protected open fun setupObservers() {}

    companion object {
        private const val LOG_DIALOG_FRAGMENT_NAME_INFO = "DIALOG_FRAGMENT_NAME"
    }
}