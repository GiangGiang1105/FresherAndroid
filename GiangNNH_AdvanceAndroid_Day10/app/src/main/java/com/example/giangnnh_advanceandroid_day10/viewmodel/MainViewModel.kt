package com.example.giangnnh_advanceandroid_day10.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giangnnh_advanceandroid_day10.base.Event
import com.example.giangnnh_advanceandroid_day10.data.MainRepository
import com.example.giangnnh_advanceandroid_day10.data.model.city.ListCity
import com.example.giangnnh_advanceandroid_day10.data.model.weather.WeatherData
import com.example.giangnnh_advanceandroid_day10.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    var weatherDataLiveData: MutableLiveData<Resource<WeatherData>> = MutableLiveData()
        private set

    var listCitiesLiveData: MutableLiveData<Resource<ListCity>> = MutableLiveData()
        private set

    fun getWeatherOfCity(name: String = "Đà Nẵng") {
        mainRepository.getWeatherOfCity(name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
                weatherDataLiveData.value = Resource.Loading(Event(true))
            }.doOnComplete {
                weatherDataLiveData.value = Resource.Loading(Event(false))
            }.subscribe(
                { weather ->
                    weatherDataLiveData.value = Resource.Success(weather)
                },
                { throwable ->
                    weatherDataLiveData.value = Resource.Failure(Event(throwable))
                }
            )
    }

    fun getCitiesByNamePrefix(namePrefix: String) {
        mainRepository.getCitiesByNamePrefix(namePrefix).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
                listCitiesLiveData.value = Resource.Loading(Event(true))
            }.doOnComplete {
                listCitiesLiveData.value = Resource.Loading(Event(false))
            }.subscribe(
                { list ->
                    listCitiesLiveData.value = Resource.Success(list)
                },
                { throwable ->
                    listCitiesLiveData.value = Resource.Failure(Event(throwable))
                }
            )
    }
}