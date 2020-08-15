package com.sample.twowaybindingsample.ui.bottomnavigation

import android.view.MenuItem
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.bottomnavigation.BottomNavigationView

object BottomNavigationViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:selectedItem")
    fun BottomNavigationView.changeSelectedItem(itemId: Int?) {
        if (itemId != null && itemId != selectedItemId) {
            selectedItemId = itemId
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:selectedItem", event = "android:selectedAttrChanged")
    fun BottomNavigationView.getSelectedItem(): Int {
        return selectedItemId
    }

    @JvmStatic
    @BindingAdapter(
        value = ["app:afterItemChanged", "android:selectedAttrChanged"],
        requireAll = false
    )
    fun BottomNavigationView.addOnItemSelectedListener(
        afterItemChanged: OnAfterNavigationItemChangeListener?,
        attrChanged: InverseBindingListener?
    ) {
        val newValue = object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId != selectedItemId) {
                    item.isChecked = true
                }
                afterItemChanged?.afterItemChanged(item.itemId)
                attrChanged?.onChange()
                return false
            }
        }
        setOnNavigationItemSelectedListener(newValue)
    }
}