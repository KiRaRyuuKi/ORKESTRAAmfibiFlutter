package com.orkestra.paycars.controllers.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.orkestra.paycars.controllers.view.ViewDataHome
import com.orkestra.paycars.services.ApiDataService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    private val apiDataService: ApiDataService
//) : ViewModel() {
//
//    private val homeListData = MutableLiveData<Resource<List<ViewDataHome>>>()
//    val homeListItems: LiveData<Resource<List<ViewDataHome>>>
//        get() = homeListData
//
//    init {
//        getHomeListItems()
//    }
//
//    private fun getHomeListItems() = viewModelScope.launch {
//        homeListData.postValue(Resource.Loading)
//        val moviesDeferred = async { apiDataService.getMovies() }
//        val directorsDeferred = async { apiDataService.getDirectors() }
//
//        val movies = moviesDeferred.await()
//        val directors = directorsDeferred.await()
//
//        val homeListData = mutableListOf<ViewDataHome>()
//        if(movies is Resource.Success && directors is Resource.Success){
//            homeListData.add(ViewDataHome.Title(1, "Recommended Movies"))
//            homeListData.addAll(movies.value)
//            homeListData.add(ViewDataHome.Title(2, "Top Directors"))
//            homeListData.addAll(directors.value)
//            homeListData.postValue(Resource.Success(homeItemsList))
//        }else{
//            Resource.Failure(false, null, null)
//        }
//    }
//}