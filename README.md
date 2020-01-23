# Android Simple RAWG API Wrapper
Android RAWG API Wrapper simple RAWG api wrapper (component in use Retrofit, <b>Coroutines</b>)
for now it support coroutines only in feature will be option for rx and live data


# RAWG API Documentation

RAGW API Docs Avalible <a href ="https://api.rawg.io/docs/">here.</a>

```HTML
https://api.rawg.io/docs/
`````

# Download
add this line to build.gradle
```gradle
dependencies {
	implementation 'com.ekn.gruzer.rawg:rawgapiwrapper:0.1.0'
}


```

# Simple Usage example

wrapper based on retrofit 2 and coroutines 
so who is familiar with retrofit usage is standard
call for service and use it to access api
for example:

```kotlin

//Create reference to api service
val service = RawgServiceApi.create()

```
next
# Example usecases

for example
<b>What are the most anticipated upcoming games?</b>
```kotlin

//dates=2019-09-01,2019-09-30 date format is yyyy-MM-dd
//need provide range start and end date to gate list of games between this dates
//For More info check officale documentation
//https://api.rawg.io/docs/#operation/games_list

val result = service.getListOfGames(dates = dates)
```

<b>Handle Result</b>
result is sealed class object(kotlin sealed class)
that looks like this

```kotlin
sealed class RawgApiResult<out T> {
    data class Success<T>(val data: T?) : RawgApiResult<T>()
    data class Failure(val statusCode: Int?) : RawgApiResult<Nothing>()
    object NetworkError : RawgApiResult<Nothing>()
}
```

example of handle a result

```kotlin

fun handleResult(response: RawgApiResult<R>) = when (response) {
        is RawgApiResult.Success ->handleSuccess(response.data)
        is RawgApiResult.Failure -> handleErrorState(response.statusCode)
        is RawgApiResult.NetworkError -> handleNetworkError()
}
```

in case <b>getListOfGames</b> resopnase data will containe `RawgApiResult<RawgData<List<Game>>>`
```kotlin
  val result = service.getListOfGames(dates = dates) //RawgApiResult<RawgData<List<Game>>>
  val data = result.data //RawgData<List<Games>> class  
  val list = data.result //  List<Game> list of games Number of results to return per page.(default 20)
                         // Page size can be specified more about all option please read official documentation
  val next = data.next // contaned next data url page number "x"
  
```
In case of <b>getDetailsOfGame(id = id)</b> resopnase data will containe `RawgApiResult<GameSingle>`

```kotlin

val result = service.getDetailsOfGame(id = id)  //RawgApiResult<GameSingle>
val data = result.data  //GameSingle

```
<h3> For more details about resturn object please visit officale documentation page</h3>

```HTML
https://rawg.io/apidocs
https://api.rawg.io/docs/
````


<b>In General for now </b>
inside resonse.data object can bee two option 
or this rawg data object 
```kotlin
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
```
or other objects as shown in documentation
for more details please check documentation <a href="https://api.rawg.io/docs/">here</a> 
```HTML
https://api.rawg.io/docs/
````



## TODO
* Test and check all endpoints for reachability
* Improve and update all return data (in docs they not fully updated)
* <b>Add standart return object for all request</b>
* Add support to use with Rx And LiveData as return result
* More QA

###PS
if you have any idea how i can improve wrapper please **open new issue** and describe , and i will try to add it to the Library.

## License

Copyright 2020 Evstafiev Konstantin 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)


