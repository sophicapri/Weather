# Weather

Weather app.

<img src="https://github.com/sophicapri/Weather/blob/main/screenshots/weatherappvid.gif" align="right" width="40%">

Bonus
-----

Here are new features that I thought would add value to the app:
- Locate the user and give the weather according to their position.
- Possibility to choose the degree type between C and F.
- Save favorite locations so that the user doesn't have to type the location everytime.

Notes
-----

Here are some code improvements that could be made:
- Add placeholders for the images.
- Cover more tests.
- Handle network errors in a more specific way. (ex: log the network errors)  
- Extract common Dp values into a separate file for reusability

Architecture
--------------

I applied Clean Architecture (in the Uncle Bob sense) and chose to have a single 'app' module to keep things simple, but I've split the packages in a way I would if it was a multi-module project by feature.
I have these packages for this single feature app:
- data 
- domain
- presentation
- di 


Technical Stack
--------------
  * [Kotlin Coroutines][91] - For asynchronous operations.
  * [Kotlin Flow][13] - Works in hand with coroutines to handle asynchronous data.
  * [Compose][11] - Library to create the UI components.
  * [Retrofit][5] - For network calls.
  * [Hilt][92] - For dependency injection.
  * [Coil][32] - To load remote images.
  * [Moshi][9] - Json library.
  * [MockK][20] - Mocking library for tests.


[13]: https://developer.android.com/kotlin/flow
[11]: https://developer.android.com/jetpack/compose
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://developer.android.com/training/dependency-injection/hilt-android
[5]: https://github.com/square/retrofit
[9]: https://github.com/square/moshi
[20]: https://github.com/mockk/mockk
[32]: https://github.com/coil-kt/coil
