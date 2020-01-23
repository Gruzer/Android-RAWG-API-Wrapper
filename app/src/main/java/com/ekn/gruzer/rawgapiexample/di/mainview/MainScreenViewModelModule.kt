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

package com.ekn.gruzer.rawgapiexample.di.mainview

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.ekn.gruzer.rawgapiexample.data.contracts.GameRepo
import com.ekn.gruzer.rawgapiexample.ui.main.viewmodel.MainViewModel
import com.ekn.gruzer.rawgapiexample.utils.MyViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainScreenViewModelModule(private val fragment: Fragment) {

    @Provides
    fun provideViewModel(fragment: Fragment, gameRepository: GameRepo): MainViewModel {
        return ViewModelProviders.of(fragment,
            MyViewModelFactory {
                MainViewModel(gameRepository)
            }).get(MainViewModel::class.java)
    }

    @Provides
    fun provideFragment() = fragment
}