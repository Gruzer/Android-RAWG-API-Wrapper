/*
 * Copyright 2020 Evstafiev Konstantin
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

package com.ekn.gruzer.rawgapiexample.data
import com.ekn.gruzer.rawg.network.*

abstract class Repository<R> {

     fun handleResult(response: RawgApiResult<R>) = when (response) {
        is RawgApiResult.Success ->handleSuccess(response.data)
        is RawgApiResult.Failure -> handleErrorState(response.toString())
        is RawgApiResult.NetworkError -> handleNetworkError(response.toString())

    }

    fun handleNetworkError(message: String): DataState<R>{
         return DataState.NetworkError(message)
     }
     fun handleErrorState(error: String) : DataState<R>{
        return DataState.Error(error)
    }
     fun handleSuccess(data: R?) :DataState<R>{
         return DataState.Success(data)
     }

}