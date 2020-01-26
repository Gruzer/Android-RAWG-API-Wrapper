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

package com.ekn.gruzer.rawgapiexample.ui.details


import android.content.Context
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ekn.gruzer.rawg.entity.GameSingle

import com.ekn.gruzer.rawgapiexample.R
import com.ekn.gruzer.rawgapiexample.RawgApplication
import com.ekn.gruzer.rawgapiexample.di.detailview.DetailedScreenViewModelModule
import com.ekn.gruzer.rawgapiexample.ui.details.viewmodel.DetailViewIntent
import com.ekn.gruzer.rawgapiexample.ui.details.viewmodel.DetailsViewModel
import com.ekn.gruzer.rawgapiexample.utils.loadImage
import kotlinx.android.synthetic.main.fragment_game_detail.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class GameDetailFragment : Fragment() {

    @Inject
    lateinit var viewModel: DetailsViewModel

    val args by navArgs<GameDetailFragmentArgs>()
    private lateinit var gameID: String
    private lateinit var gameTitle: String
    private lateinit var gameImage: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameID = args.gameID
        gameTitle = args.gameTitle
        gameImage = args.gameImage
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context?.let {
            (it.applicationContext as RawgApplication).appComponent.provideDetailScreenComponent(
                DetailedScreenViewModelModule(this)
            ).inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        details_game_title_txt.text = gameTitle
        loadImage(details_image, gameImage)

        viewModel.viewState.observe(viewLifecycleOwner, Observer { render(it) })
        viewModel.handleIntent(DetailViewIntent.FetchData(gameID))

    }

    private fun render(viewState: DetailsViewState) = when (viewState) {
        is DetailsViewState.isLOading -> isLoading()
        is DetailsViewState.isDoneLoading -> loadingIsDone()
        is DetailsViewState.ShowData -> show(viewState.game)
        is DetailsViewState.Eroor -> showError(viewState.error)
    }

    private fun isLoading() {
        //TODO: show loading progress
    }

    private fun loadingIsDone() {
        //TODO: hide loading progress
    }

    private fun showError(error: String) {
        //TODO: show error message
    }

    private fun show(gameSingle: GameSingle?) {
        gameSingle?.let {
            details_description_txt.text = Html.fromHtml(it.description)
        }

    }


}
