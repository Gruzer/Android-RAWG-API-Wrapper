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

package com.ekn.gruzer.rawgapiexample.ui.main


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.ekn.gruzer.rawg.entity.Game
import com.ekn.gruzer.rawgapiexample.R
import com.ekn.gruzer.rawgapiexample.RawgApplication
import com.ekn.gruzer.rawgapiexample.di.mainview.GameRepositoryModule
import com.ekn.gruzer.rawgapiexample.di.mainview.MainScreenViewModelModule
import com.ekn.gruzer.rawgapiexample.ui.main.viewmodel.MainViewIntent
import com.ekn.gruzer.rawgapiexample.ui.main.viewmodel.MainViewModel

import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), GamesAdapter.RecycleViewItemClickLister {

    @Inject
    lateinit var viewModel: MainViewModel

    private val gamesAdapter: GamesAdapter by lazy {
        GamesAdapter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.let {
            (it.applicationContext as RawgApplication).appComponent.provideMainScreenComponent(
                GameRepositoryModule(), MainScreenViewModelModule(this)
            ).inject(this)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        main_games_rv.apply {
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            adapter = gamesAdapter
        }
        viewModel.viewState.observe(viewLifecycleOwner, Observer { render(it) })
        viewModel.perform(MainViewIntent.FetchFutureRelease)


    }


    private fun render(viewState: MainViewState) = when (viewState) {
        is MainViewState.IsLoading -> loadingInProgress()
        is MainViewState.IsDoneLoading -> loadingIsDone()
        is MainViewState.ShowData -> displayGames(viewState.games)
        is MainViewState.Error -> showError(viewState.error)
    }

    private fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun loadingInProgress() {
        main_games_pb.visibility = View.VISIBLE
        //TODO : visual effect of loading make it visible
    }

    private fun loadingIsDone() {
        main_games_pb.visibility = View.GONE
        //TODO : visual effect of loading make it invisible
    }


    private fun displayGames(games: List<Game>?) {
        games?.let {
            gamesAdapter.updateList(it)
        }
    }

    override fun onRecycleViewItemSelected(item: Game) {
        val direction: NavDirections =
            MainFragmentDirections.actionMainFragmentToGameDetailFragment(
                item.id.toString(),
                item.name,
                item.background_image
            )
        findNavController().navigate(direction)
    }


}
