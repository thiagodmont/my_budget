package com.tmd.mybudget.helpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import assertk.Assert
import assertk.assertThat
import assertk.assertions.isEqualTo

fun <T : Any?> LiveData<T>.test(
    comparator: ((value: T) -> Any?)? = null
): Assert<Any?> {
    val observer = Observer<T> { /* unused */ }

    if (!hasActiveObservers()) observeForever(observer)

    return assertThat(
        comparator?.invoke(this.value as T) ?: this.value
    ).apply {
        removeObserver(observer)
    }
}

fun <T : Any?> LiveData<T>.testHistory(): Assert<List<T?>> {
    val history = mutableListOf<T?>()

    if (!hasActiveObservers()) observeForever {
        it?.let { history.add(it) }
    }

    return assertThat(history)
}

fun <T : Any?> LiveData<T>.testList(): List<T?> {
    val history = mutableListOf<T?>()

    if (!hasActiveObservers()) observeForever { history.add(it) }

    return history
}

fun <T> Assert<T>.isTrue() = this.isEqualTo(true)

fun <T> Assert<T>.isFalse() = this.isEqualTo(false)

fun <T> Assert<T>.isFireSingleEvent() = this.isEqualTo(listOf(Unit))