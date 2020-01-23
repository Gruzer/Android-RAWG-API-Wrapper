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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ekn.gruzer.rawg.entity.Game
import com.ekn.gruzer.rawgapiexample.R
import kotlinx.android.synthetic.main.itemview_main_rv.view.*

class GamesAdapter(val listener: RecycleViewItemClickLister) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface RecycleViewItemClickLister {
        fun onRecycleViewItemSelected(item: Game)
    }

    private var games: List<Game> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GamesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.itemview_main_rv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GamesViewHolder -> {
                holder.bind(games[position])
                    .setOnClickListener { listener.onRecycleViewItemSelected(games[position]) }

            }
        }
    }

    fun updateList(list: List<Game>) {
        games = list
        notifyDataSetChanged()
    }


    class GamesViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.game_title_img
        val title = itemView.game_title_txt


        fun bind(game: Game): View {
            title.text = game.name


            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)


            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(game.background_image)
                .into(image)
            return itemView
        }
    }
}