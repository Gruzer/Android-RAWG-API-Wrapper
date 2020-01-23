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

package com.ekn.gruzer.rawgapiexample.data.repository

import com.ekn.gruzer.rawg.entity.GameSingle
import com.ekn.gruzer.rawgapiexample.data.DataState
import com.ekn.gruzer.rawgapiexample.data.Repository
import com.ekn.gruzer.rawgapiexample.data.contracts.GameSingleRepo
import com.ekn.gruzer.rawgapiexample.data.remote.contract.RemoteSource

class GameSingleRepository(private val remote: RemoteSource) : GameSingleRepo,
    Repository<GameSingle>() {


    override suspend fun fetchGameDetails(gameID: String): DataState<GameSingle> {
        return handleResult(remote.fetchGameDetails(gameID))
    }



}