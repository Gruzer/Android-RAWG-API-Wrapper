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

package com.ekn.gruzer.rawgapiexample.ui.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekn.gruzer.rawg.entity.GameSingle
import com.ekn.gruzer.rawgapiexample.data.DataState
import com.ekn.gruzer.rawgapiexample.data.contracts.GameSingleRepo
import com.ekn.gruzer.rawgapiexample.ui.details.DetailsViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(private val repository: GameSingleRepo) : ViewModel() {


    private val _viewState: MutableLiveData<DetailsViewState> = MutableLiveData()

    val viewState: LiveData<DetailsViewState>
        get() = _viewState


    fun handleIntent(intent: DetailViewIntent) = when (intent) {
        is DetailViewIntent.FetchData -> getDetails(intent.gameID)
    }

    private fun getDetails(gameID: String) {
        if (_viewState.value !is DetailsViewState.ShowData) {
            viewModelScope.launch {
                performRequest(gameID)
            }
        }
    }

    private suspend fun performRequest(gameID: String) = withContext(Dispatchers.IO)
    {
        val result = repository.fetchGameDetails(gameID)
        withContext(Dispatchers.Main){_viewState.value = DetailsViewState.isDoneLoading}
        handleResult(result)

    }

    private fun handleResult(result: DataState<GameSingle>) = when (result) {
        is DataState.Success -> _viewState.postValue(DetailsViewState.ShowData(result.data))
        is DataState.NetworkError -> _viewState.postValue(DetailsViewState.Eroor(result.message))
        is DataState.Error -> _viewState.postValue(DetailsViewState.Eroor(result.error))
    }
}