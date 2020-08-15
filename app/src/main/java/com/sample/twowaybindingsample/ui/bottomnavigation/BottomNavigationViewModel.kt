package com.sample.twowaybindingsample.ui.bottomnavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.twowaybindingsample.Event
import com.sample.twowaybindingsample.R

class BottomNavigationViewModel : ViewModel() {

    val selectedItemId = MutableLiveData<Int>()

    private val _moveHomeEvent = MutableLiveData<Event<Unit>>()
    val moveHomeEvent: LiveData<Event<Unit>> = _moveHomeEvent

    private val _moveDashboardEvent = MutableLiveData<Event<Unit>>()
    val moveDashboardEvent: LiveData<Event<Unit>> = _moveDashboardEvent

    private val _moveNotificationEvent = MutableLiveData<Event<Unit>>()
    val moveNotificationEvent: LiveData<Event<Unit>> = _moveNotificationEvent

    fun changeNavigationItem(itemId: Int) {
        when (itemId) {
            R.id.navigation_home -> _moveHomeEvent.value = Event(Unit)
            R.id.navigation_dashboard -> _moveDashboardEvent.value = Event(Unit)
            R.id.navigation_notifications -> _moveNotificationEvent.value = Event(Unit)
        }
    }

    // 홈으로 이동
    fun moveHome() {
        selectedItemId.value = R.id.navigation_home
    }
}