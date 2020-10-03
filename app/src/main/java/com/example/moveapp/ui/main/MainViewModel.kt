package com.example.moveapp.ui.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moveapp.networking.data.Movie
import com.example.moveapp.networking.data.MovieRepository
import com.example.moveapp.uti.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor( private val repository: MovieRepository) : ViewModel()  {
      fun getPopular()=liveData(Dispatchers.IO){
          emit(Resource.loading(data = null ))
          try {
              emit(Resource.success(data = repository.getPopularRepo()))
          }catch (e:Exception ){
              emit(Resource.error(data = null,message = e.message?:"Error Occurred!"))
          }
      }

}