![](https://jitpack.io/v/JoeyOggie/utilities.svg)

https://jitpack.io/#JoeyOggie/utilities


A collection of utilities and sample methods I re-use in different projects. Some of them are not by me.

Instructions:

Step 1. Add the JitPack repository to your project's build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency to the app module

	dependencies {
	        implementation 'com.github.joeyoggie:utilities:v2.2'
		
		//add the below annotation processors since they aren't transitive and have to be included in each module
		annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
		annotationProcessor "androidx.lifecycle:lifecycle-compiler:2.1.0"
    		//room database
		def room_version = "2.2.2" // 2.2.2 for latest stable version
    		annotationProcessor "androidx.room:room-compiler:$room_version"
		
	}
