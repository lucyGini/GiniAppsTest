package com.example.giniappstest

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.*
import com.example.giniappstest.data.Repository
import com.example.giniappstest.data.database.ImagesEntity
import com.example.giniappstest.data.network.PixalImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    //database

    var readImages: LiveData<List<ImagesEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertImages(imagesEntity: ImagesEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.local.insertImages(imagesEntity)
    }

    //retrofit
    var imagesResponse: MutableLiveData<PixalImages> = MutableLiveData()

    fun getImages() = viewModelScope.launch {
        getImagesSafeCall()
    }

    private suspend fun getImagesSafeCall() {

        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getImages()
                imagesResponse.value = response

                val images = imagesResponse.value

                if (images != null) {
                    offlineCashImages(images)
                }
            } catch (e: Exception) {
                Log.i("INTERNET CONNECTION", "Images are not found")
            }
        } else {
            Log.i("INTERNET CONNECTION", "No internet connection")
        }
    }

    private fun offlineCashImages(pixalImages: PixalImages) {
        val imagesEntity = ImagesEntity(pixalImages)
        insertImages(imagesEntity)
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>()
            .getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}