This project outlines the assignment requirements by wareline 
"Develop an Android application to efficiently load and display images in a scrollable grid using the Pexels Curated Photos API. You are not allowed to use any third-party image loading library."

This task is accomplished via using Kotlin language and on top of it Jetpack compose UI toolkit for the modern UI. 

To run the project you can clone or fork the repo in your machine and to ensure it runs smoothly make sure to get and provide your API_KEY from the https://www.pexels.com/api/documentation/ whre you can 
provide it in local.properties file under the variable name API_KEY and run the app smoothly.

Android studio version used: Android Studio Koala | 2024.1.1 RC 1
Kotlin version : 1.9.0

**Task Accomplishments:**

**Requirement:**
1. Image Grid:
   - Display a 3-column square image grid.
   - Images should be center-cropped.

**Solution:** Used LazyVerticalGrid with specifying columns value-parameter and created a PhotoCard composable to center crop the image 

**Requirement:**

2. Image Loading:  
 - Implement asynchronous image loading using the Pexels Curated Photos API.

**Solution:** Implmented API call and handling via Retrofit following MVVM architecture to efficiently handle the incoming images,
 utilized kotlin coroutines to handle the network request to ensure the app does not lag.

**Requirement:**

3. Display:
   - Ensure the user can scroll through at least 100 images.

**Solution:** Lazy loading and scrollable modifier function esure smooth scrolling of images by the users.

**Requirement:**

4. Caching:
   - Develop a caching mechanism to store images retrieved from the API in both memory and disk cache for efficient retrieval.

**Solution:** Creating a caching mecahsim utilizing android util's LruCache class and later stored the cached images in the local database with room library.

**Requirement:**

5. Error Handling:
   - Handle network errors and image loading failures gracefully when fetching images from the API, providing informative error messages or placeholders for failed image loads.

**Solution:** Written the code keeping everything in mind considering every possible scenario, also placed logs for better debugginh in case of errors. 

**Requirement:**
6. Implementation Language:
   - Kotlin or Java using Native Android technologies.

**Solution:** The project is entirely implemented in Kotlin, Google's recommended language for Android application development. 
