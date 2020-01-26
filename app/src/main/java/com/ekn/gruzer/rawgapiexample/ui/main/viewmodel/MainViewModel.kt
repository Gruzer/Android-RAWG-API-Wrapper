/*
 * Copyright 2019 Evstafiev Konstantin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ekn.gruzer.rawgapiexample.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekn.gruzer.rawg.entity.Game
import com.ekn.gruzer.rawg.network.RawgData
import com.ekn.gruzer.rawgapiexample.data.DataState
import com.ekn.gruzer.rawgapiexample.data.contracts.GameRepo
import com.ekn.gruzer.rawgapiexample.ui.main.MainViewState
import com.ekn.gruzer.rawgapiexample.utils.currentFormated
import com.ekn.gruzer.rawgapiexample.utils.formatedDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel(private val repository: GameRepo) : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState


    fun perform(intent: MainViewIntent) = when (intent) {
        is MainViewIntent.FetchFutureRelease -> getGames(getDates())
    }


    private fun getGames(dates: String) {
        if (_viewState.value == null)
            viewModelScope.launch(Dispatchers.Main) {
                _viewState.value = MainViewState.IsLoading
                performRequest(dates)
            }
    }

    private fun handleResult(result: DataState<RawgData<List<Game>>>) = when (result) {
        is DataState.NetworkError -> _viewState.postValue(MainViewState.Error(result.message))
        is DataState.Error -> _viewState.postValue(MainViewState.Error(result.error))
        is DataState.Success -> _viewState.postValue(MainViewState.ShowData(result.data?.result))
    }


    private suspend fun performRequest(dates: String) = withContext(Dispatchers.IO) {
        delay(5000)
        val result = repository.getGames(dates)
        withContext(Dispatchers.Main) { _viewState.value = MainViewState.IsDoneLoading }
        handleResult(result)
    }

    private fun getDates(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, 1)
        val currentDate = Date().currentFormated()
        val nextYearDate = Date().formatedDate(calendar.time)
        return "$currentDate,$nextYearDate"
    }
}