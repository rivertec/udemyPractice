package com.liam.udemypractice.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liam.udemypractice.model.Banner
import com.liam.udemypractice.model.HomeData
import com.liam.udemypractice.model.Promotion
import com.liam.udemypractice.repository.home.HomeRepository
import com.liam.udemypractice.ui.common.Event
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

//    private val _title = MutableLiveData<Title>()
//    val title: LiveData<Title> = _title

    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    private val _topBanners = MutableLiveData<List<Banner>>()
    val topBanners: LiveData<List<Banner>> = _topBanners

    private val _promotion = MutableLiveData<Promotion>()
    val promotion: LiveData<Promotion> = _promotion

    private val _openDetailEvent = MutableLiveData<Event<String>>()
    val openDetailEvent: LiveData<Event<String>> = _openDetailEvent

    init {
        loadHomeData()
    }

    fun openProductDetail(productId: String) {
        _openDetailEvent.value = Event(productId)
        Log.d("test", productId)
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            val homeData = homeRepository.getHomeData()
            homeData?.let { homeData ->
                _homeData.value = homeData
                _topBanners.value = homeData.topBanners
                _promotion.value = homeData.promotions
            }
        }
    }
}