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

import com.google.gson.annotations.SerializedName



data class RawgData<T>(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    var prev: String,
    @SerializedName("results")
    var result: T
)


/*

@JsonClass(generateAdapter = true)
data class ServiceResult(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    var prev: String?,
    @Json(name = "results")
    var result: List<Game>
)
*/
