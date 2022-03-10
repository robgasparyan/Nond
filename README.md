# Nond 🖼 🎨
 #### Nond is a classic image loader and cacher based on the LruCache.If you want to use it, you can simply clone it and import Nond module in your project.Or you can wait till we will publish it in the mavenCentral() and u can use it as an external dependency. 

# Project Structure
- language - **kotlin**, **java**
- architecture - **designed by MVVM**
- dependency injection - **Hilt**
- networking - **Retrofit**, **okHttp** 
- test - **JUnit, Mockk, Coroutines Test, MockWebServer, UI Test(Partially)**
- UI - **androidX**
# Screenshots

 ⠀             |  ⠀       | ⠀
:-------------------------:|:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/20711438/157626434-593c9844-051c-44f4-b740-abee6ca15b24.png)  |  ![](https://user-images.githubusercontent.com/20711438/157626461-2daba2b1-efa5-42a0-9805-a69c8541b183.png)  |  ![](https://user-images.githubusercontent.com/20711438/157626476-dc72010c-4e2c-40ac-bd04-2d7f438c9366.png)

# Logic behind the Nond 

#### Nond it is a light-weight library. You should call loadImage extension for ImageView's that's all. The logic behind the extension is following Nond will create for u his own ImageReuest builder using your url and target view after that it will be passed to the next stage and next stage is ImageLoading for that we have an image Laoder based on okhttp so once resonse come and it succeed otherwise the downloader coroutine will throw non-fatal HttpException so when it succeed your picture will be stored to the cache automatically, as a key of cache we are using the hash of your url. Next time when you will fetch the same url result will we given from the cache.

# Enjoy  😉
