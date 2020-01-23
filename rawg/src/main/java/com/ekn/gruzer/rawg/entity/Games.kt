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

package com.ekn.gruzer.rawg.entity

import java.util.*

data class Game(
    val id: Int,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    val background_image: String,
    val rating: Number,
    val ratingTop: Int,
    val ratings: List<Rating>,
    val ratingsCount: Int,
    val reviewsTextCount: String,
    val added: Int,
    val addedByStatus: AddedByStatus,
    val metacritic: Int,
    val playtime: Int,
    val suggestionsCount: Int,
    val short_screenshots: List<ShortScreenshot>
)

data class ShortScreenshot(
    val id: Int,
    val image: String
)

/**
 * @property id integer (ID)
 * @property slug string <slug> (Slug)^[-a-zA-Z0-9_]+$
 * @property name string (Name) non-empty
 * @property nameOriginal string (Name original) non-empty
 * @property description string (Description) non-empty
 * @property metacritic integer (Metacritic)
 * @property released string <date> (Released)
 * @property tba boolean (TBA)
 * @property updated string <date-time> (Updated)
 * @property backgroundImageURL string <uri> (Background image)
 * @property backgroundImageAdditionalURL string (Background image additional)
 * @property websiteURL string <uri> (Website) non-empty
 * @property ratingTop integer (Rating top)
 * @property ratingObj object (Ratings)
 * @property reactions object (Reactions)
 * @property added integer (Added)
 * @property addedByStatus object (Added by status)
 * @property playtime integer (Playtime) in hours
 * @property screenshotsCount integer (Screenshots count)
 * @property moviesCount integer (Movies count)
 * @property creatorsCount integer (Creators count)
 * @property achievementsCount integer (Achievements count)
 * @property parentAchievementsCount string (Parent achievements count)
 * @property redditURL string (Reddit url) non-empty For example "https://www.reddit.com/r/uncharted/" or "uncharted"
 * @property redditName string (Reddit name) non-empty
 * @property redditDescription string (Reddit description) non-empty
 * @property redditLogoURL string <uri> (Reddit logo) non-empty
 * @property redditCount integer (Reddit count)
 * @property twitchCount string (Twitch count)
 * @property youtubeCount string (Youtube count)
 * @property reviewsTextCount string (Reviews text count)
 * @property ratingsCount integer (Ratings count)
 * @property suggestionsCount integer (Suggestions count)
 * @property alternativeNames Array of strings (Alternative names)
 * @property metacriticURL string (Metacritic url) non-empty For example "http://www.metacritic.com/game/playstation-4/the-witcher-3-wild-hunt"
 * @property parentsCount integer (Parents count)
 * @property additionsCount integer (Additions count)
 * @property gameSeriesCount integer (Game series count)
 *
 */
data class GameSingle(
    val id: Int,
    val slug: String,
    val name: String,
    val nameOriginal: String,
    val description: String,
    val metacritic: Int,
    val released: String,
    val tba: Boolean,
    val updated: String,
    val backgroundImageURL: String,
    val backgroundImageAdditionalURL: String,
    val websiteURL: String,
    val rating: Number,
    val ratingTop: Int,
    val ratings: List<Rating>,
    val reactions: Objects,
    val added: Int,
    val addedByStatus: AddedByStatus,
    val playtime: Int,
    val screenshotsCount: Int,
    val moviesCount: Int,
    val creatorsCount: Int,
    val achievementsCount: Int,
    val parentAchievementsCount: Int,
    val redditURL: String,
    val redditName: String,
    val redditDescription: String,
    val redditLogoURL: String,
    val redditCount: Int,
    val twitchCount: String,
    val youtubeCount: String,
    val reviewsTextCount: String,
    val ratingsCount: Int,
    val suggestionsCount: Int,
    val alternativeNames: List<String>,
    val metacriticURL: String,
    val parentsCount: Int,
    val additionsCount: Int,
    val gameSeriesCount: Int
)

data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Float
)

data class AddedByStatus(
    val yet: Int,
    val owned: Int,
    val beaten: Int,
    val toplay: Int,
    val dropped: Int,
    val playing: Int
)

data class GamePersonList(
    val id: Int,
    val name: String,
    val slug: String,
    val imageURL: String,
    val imageBackground: String,
    val gameCount: Int
)

/**
 * @property boolean (Hidden) Default: false Set image as hidden or visible.
 */
data class ScreenShot(
    val id: Int,
    val imageURL: String,
    val hidden: Boolean,
    val width: Int,
    val height: Int
)

/**
 * Get links to the stores that sell the game.
 */
data class GameStoreFull(
    val id: Int,
    val gameID: Int,
    val storeID: Int,
    val url: String
)

/**
 * Get a list of game achievements.
 */
data class Achievement(
    val id: Int,
    val name: String,
    val description: String,
    val imageURL: String,
    val percent: String
)

/**
 * Get a list of most recent posts from the game's subreddit.
 */
data class RecentPosts(
    val id: Int,
    val name: String,
    val text: String,
    val imageURL: String,
    val URL: String,
    val username: String,
    val usernameURL: String,
    val created: String
)

/**
 * Get streams on Twitch associated with the game
 */
data class TwitchStreams(
    val id: Int,
    val externalID: Int,
    val name: String,
    val description: String,
    val created: String,
    val published: String,
    val thumbnailURL: String,
    val viewCount: Int,
    val language: String
)

/**
 * Get videos from YouTube associated with the game.
 *
 * channel_id
 */
data class YoutubeChannels(
    val id: Int,
    val externalID: String,
    val channelID: String,
    val channelTitle: String,
    val name: String,
    val description: String,
    val created: String,
    val viewCount: Int,
    val commentCount: Int,
    val likeCount: Int,
    val dislikeCount: Int,
    val favoriteCount: Int,
    val thumbnails: Objects
)
