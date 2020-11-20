package ru.ashilkin.picturecoloring.componentstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentLifecycleHelper(private val componentStore: ComponentStore) :
    FragmentManager.FragmentLifecycleCallbacks() {

    private var isInSaveState = false

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        super.onFragmentStarted(fm, f)
        isInSaveState = false
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        isInSaveState = false
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        super.onFragmentSaveInstanceState(fm, f, outState)
        isInSaveState = true
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        removeComponentIfNeeded(f)
    }

    private fun removeComponentIfNeeded(fragment: Fragment) {
        if (fragment !is ComponentOwner) return

        if (fragment.requireActivity().isFinishing) {
            componentStore.remove(fragment.componentKey())
            return
        }

        if (isInSaveState) {
            isInSaveState = false
            return
        }

        var anyParentIsRemoving = false
        var parent = fragment.parentFragment
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving
            parent = parent.parentFragment
        }
        if (fragment.isRemoving || anyParentIsRemoving) {
            componentStore.remove(fragment.componentKey())
        }
    }
}