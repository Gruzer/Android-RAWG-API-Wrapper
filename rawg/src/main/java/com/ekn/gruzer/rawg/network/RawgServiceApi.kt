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

package com.ekn.gruzer.rawg.network

import com.ekn.gruzer.rawg.entity.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RawgServiceApi {

    /**
     * Get a list of creator positions (jobs).
     */
    @GET("/creator-roles")
    suspend fun getListOfCreatorPosition(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Position>>>

    /**
     * Get a list of game creators.
     */
    @GET("/creators")
    suspend fun getListOfGameCreators(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Person>>>

    /**
     * Get details of the creator.
     */
    @GET("/creators/{id}")
    suspend fun fetchCreatorDetails(
        @Path("id") id: String
    ): RawgApiResult<Person>

    /**
     * Get a list of game developers.
     */
    @GET("/developers")
    suspend fun getDevelopersList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): RawgApiResult<RawgData<List<Developer>>>

    /**
     * Get details of the developer.
     */
    @GET("/developers/{id}")
    suspend fun fetchDeveloperDetails(
        @Path("id") id: String
    ): RawgApiResult<Developer>


    /**
     * Get a list of games.
     * @property page integer A page number within the paginated result set.
     * @property pageSize integer Number of results to return per page.
     * @property search string Search query.
     * @property parentPlatform string Filter by parent platforms, for example: 1,2,3.
     * @property platforms string Filter by platforms, for example: 4,5.
     * @property stores string Filter by stores, for example: 5,6.
     * @property developers string Filter by developers, for example: 1612,18893 or valve-software,feral-interactive.
     * @property publishers string Filter by publishers, for example: 354,20987 or electronic-arts,microsoft-studios.
     * @property genres string Filter by genres, for example: 4,51 or action,indie.
     * @property tags string Filter by tags, for example: 31,7 or singleplayer,multiplayer.
     * @property creators string Filter by creators, for example: 78,28 or cris-velasco,mike-morasky.
     * @property dates string Filter by a release date, for example: 2010-01-01,2018-12-31.1960-01-01,1969-12-31.
     * @property platformsCount integer Filter by platforms count, for example: 1.
     * @property excludeCollection integer Exclude games from a particular collection, for example: 123.
     * @property excludeAdditions boolean Exclude additions.
     * @property excludeParents boolean Exclude games which have additions.
     * @property excludeGameSeries boolean Exclude games which included in a game series.
     * @property ordering string Available fields: name, released, added, created, rating.
     *                    You can reverse the sort order adding a hyphen, for example: -released.
     *
     */
    @GET("/api/games")
    suspend fun getListOfGames(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null,
        @Query("search") search: String? = null,
        @Query("parent_platforms") parentPlatform: String? = null,
        @Query("platforms") platforms: String? = null,
        @Query("stores") stores: String? = null,
        @Query("developers") developers: String? = null,
        @Query("publishers") publishers: String? = null,
        @Query("genres") genres: String? = null,
        @Query("tags") tags: String? = null,
        @Query("creators") creators: String? = null,
        @Query("dates") dates: String? = null,
        @Query("platforms_count") platformsCount: Int? = null,
        @Query("exclude_collection") excludeCollection: Int? = null,
        @Query("exclude_additions") excludeAdditions: Boolean? = null,
        @Query("exclude_parents") excludeParents: Boolean? = null,
        @Query("exclude_game_series") excludeGameSeries: Boolean? = null,
        @Query("ordering") ordering: String? = null
    ): RawgApiResult<RawgData<List<Game>>>


    /**
     * Get The Sitemap Games list.
     */
    @GET("/games/sitemap")
    suspend fun getSitemapGameList(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): RawgApiResult<RawgData<List<GameSingle>>>

    /**
     * Get a list of DLC's for the game, GOTY and other editions, companion apps, etc.
     */
    @GET("/games/{game_pk}/additions")
    suspend fun getDLC(
        @Path("game_pk") gamePK: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): RawgApiResult<RawgData<List<Game>>>

    /**
     * Get a list of individual creators that were part of the development team.
     *
     * @property ordering string Which field to use when ordering the results.
     */
    @GET("/games/{game_pk}/development-team")
    suspend fun getListOfIndividualCreators(
        @Path("game_pk") gamePK: String,
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<GamePersonList>>>


    /**
     * Get a list of games that are part of the same series.
     */
    @GET("/games/{game_pk}/game-series")
    suspend fun getListOfGamesIsPartOfSameSeries(
        @Path("game_pk") gamePK: String,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Game>>>


    /**
     * Get a list of parent games for DLC's and editions.
     */
    @GET("/games/{game_pk}/parent-games")
    suspend fun getListOfParentGamesDLC(
        @Path("game_pk") gamePK: String,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Game>>>


    /**
     * Get screenshots for the game.
     */
    @GET("/games/{game_pk}/screenshots")
    suspend fun getScreenshotsOfTheGame(
        @Path("game_pk") gamePK: String,
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<ScreenShot>>>

    /**
     * Get links to the stores that sell the game.
     */
    @GET("/games/{game_pk}/stores")
    suspend fun getLinksToStoresThatSellTheGame(
        @Path("game_pk") gamePK: String,
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<GameStoreFull>>>

    /**
     * Get details of the game.
     * @return @[GameSingle]
     */
    @GET("/api/games/{id}")
    suspend fun getDetailsOfGame(
        @Path("id") id: String
    ): RawgApiResult<GameSingle>

    /**
     * Get a list of game achievements.
     */
    @GET("/games/{id}/achievements")
    suspend fun getListOfGameAchievements(
        @Path("id") id: String
    ): RawgApiResult<List<Achievement>>

    /**
     * Get a list of game trailers.
     * @property id string An ID or a slug identifying this Game.
     */
    @GET("/games/{id}/movies")
    suspend fun getGameTrailers(
        @Path("id") id: String
    ): RawgApiResult<RawgData<Any>>

    /**
     * Get a list of most recent posts from the game's subreddit.
     */
    @GET("/games/{id}/reddit")
    suspend fun getListOfMostRecentPostFromGamesSubreddit(
        @Path("id") id: String
    ): RawgApiResult<RawgData<List<RecentPosts>>>

    /**
     * Get a list of visually similar games.
     */
    @GET("/games/{id}/suggested")
    suspend fun getListOfVisualSimilarGames(
        @Path("id") id: String
    ): RawgApiResult<RawgData<List<GameSingle>>>

    /**
     * Get streams on Twitch associated with the game .
     */
    @GET("/games/{id}/twitch")
    suspend fun getTwitchStreams(
        @Path("id") id: String
    ): RawgApiResult<RawgData<List<TwitchStreams>>>

    /**
     * Get videos from YouTube associated with the game.
     */
    @GET("/games/{id}/youtube")
    suspend fun getYoutubeChannel(
        @Path("id") id: String
    ): RawgApiResult<RawgData<List<YoutubeChannels>>>


    /**
     * Get a list of video game genres.
     */
    @GET("/genres")
    suspend fun getListOfGamesGenres(
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Genre>>>

    /**
     * Get details of the genre.
     */
    @GET("/genres/{id}")
    suspend fun fetchGenreDetails(
        @Path("id") id: Int
    ): RawgApiResult<Genre>

    /**
     * Get a list of video game platforms.
     */
    @GET("/platforms")
    suspend fun getListOfGamePlatforms(
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Platform>>>

    /**
     * Get a list of parent platforms.
     * For instance, for PS2 and PS4 the “parent platform” is PlayStation.
     */
    @GET("/platforms/lists/parents")
    suspend fun getListOfParentPlatforms(
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<PlatformParentSingle>>>

    /**
     * Get details of the platform.
     */
    @GET("/platforms/{id}")
    suspend fun fetchPlatformDetails(
        @Path("id") id: Int
    ): RawgApiResult<Platform>

    /**
     * Get a list of video game publishers.
     */
    @GET("/publishers")
    suspend fun getListOfVideoGamesPublishers(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Publisher>>>

    /**
     *Get details of the publisher.
     */
    @GET("/publishers/{id}")
    suspend fun fetchPublisherDetails(
        @Path("id") id: Int
    ): RawgApiResult<Publisher>

    /**
     * Get a list of video game storefronts.
     */
    @GET("/stores")
    suspend fun getListOfGameStoreFronts(
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Store>>>

    /**
     * Get details of the store.
     */
    @GET("/stores/{id}")
    suspend fun fetchStoreDetails(
        @Path("id") id: Int
    ): RawgApiResult<Store>

    /**
     * Get a list of tags.
     */
    @GET("/tags")
    suspend fun getListOfTags(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): RawgApiResult<RawgData<List<Tag>>>

    /**
     * Get details of the tag.
     */
    @GET("/tags/{id}")
    suspend fun fetchTagsDetails(
        @Path("id") id: Int
    ): RawgApiResult<Tag>


    companion object {
        fun create(): RawgServiceApi {
            val gson: Gson = GsonBuilder()
                .setLenient()
                .create()


            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.rawg.io")
                .addCallAdapterFactory(RawgCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(RawgServiceApi::class.java)
        }
    }
}