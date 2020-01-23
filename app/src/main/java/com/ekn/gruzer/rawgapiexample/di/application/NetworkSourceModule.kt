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

package com.ekn.gruzer.rawgapiexample.di.application

import com.ekn.gruzer.rawg.network.RawgServiceApi
import com.ekn.gruzer.rawgapiexample.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class NetworkSourceModule() {

    @ApplicationScope
    @Provides
    fun provideNetworkApi(): RawgServiceApi {
        return RawgServiceApi.create()
    }
}