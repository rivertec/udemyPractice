package com.liam.udemypractice.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liam.udemypractice.model.Banner
import com.liam.udemypractice.model.Title
import com.liam.udemypractice.repository.home.HomeRepository
import com.liam.udemypractice.ui.common.Event

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _title = MutableLiveData<Title>()
    val title: LiveData<Title> = _title

    private val _topBanners = MutableLiveData<List<Banner>>()
    val topBanners: LiveData<List<Banner>> = _topBanners

    private val _openDetailEvent = MutableLiveData<Event<String>>()
    val openDetailEvent: LiveData<Event<String>> = _openDetailEvent

    init {
        loadHomeData()
    }

    fun openProductDetail(productId: String) {
        _openDetailEvent.value = Event(productId)
    }

    private fun loadHomeData() {
        val homeData = homeRepository.getHomeData()
        homeData?.let { homeData ->
            _title.value = homeData.title
            _topBanners.value = homeData.topBanners
        }
    }
}