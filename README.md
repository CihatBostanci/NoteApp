# Note App
* [General info](#general-info)
* [Technologies](#technologies)
* [Structure](#structure)
* [Features](#features)

## General info
This project is simple Android  Note App to learn recommended by Google [MVVM](https://developer.android.com/jetpack/guide)
and  Repository Pattern.

## Technology 
Try to use these technology [Coroutines](https://developer.android.com/kotlin/coroutines), [Room](https://developer.android.com/training/data-storage/room)
, [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started),
[unit test with io mockk](https://mockk.io/), [koin](https://insert-koin.io/) etc. without remote service.

## Structure

While doing this app, I want to explain my tech stack in android development process.
* Firstly, This app created with [MVVM (Model-View-ViewModel) ](https://developer.android.com/jetpack/guide) that is recommended by Google and written on Kotlin Language 100%.
* While using MVVM, I chose [Single Activity-Multiple Fragments](https://www.youtube.com/watch?v=2k8x8V77CrU) Structure with JetPack [Navigation Architecture Component](https://developer.android.com/guide/navigation/navigation-getting-started)
* On the other hand we know that if the app is not big enough as we can use Single Activity-Multiple Fragment structure otherwise it can not be good performance such as CPU, Memory Allocation and Memory Leaks etc.
* Besides that, I used Light weight  [koin](https://insert-koin.io/) Dependency Injection Library works as Service Locater that provides SOLID principles and doing maintainable test easily.
* While doing database operation, I used [Coroutines](https://developer.android.com/kotlin/coroutines) to handle. It can easily lifecycle aware and lightweight thread system to deal with Input Output operations with IO Thread. Thus Main thread may not overload.
* For the UI, the Material Library used in this project as possible and use some Animations and Transitions.
* For the local data provider, I used [Room](https://developer.android.com/training/data-storage/room) SQLite DataBase and SharedPreferences.
* Business Operation are in the following; Users can edit, delete and create their notes. There are two tables one - to - many relationships each other that are NoteModel(represents Many) and UserModel (represents One)
* For the Helper to User Operations, I Used Shared Preferences Manager while create, edit and delete operations and to handle Users.
* Unit tests were written for repository and view model
* Trying to use Kotlin Lint and Android name conventions.

## Feature
Project is included according to user create a notes, update their notes and delete their notes
if note updated note changed.
* Login Page

![Login Page](https://github.com/CihatBostanci/NoteApp/blob/main/app/src/main/res/drawable/loginpage.png?raw=true "Login Page")

* Create A Note

![Create A Note Page](https://github.com/CihatBostanci/NoteApp/blob/main/app/src/main/res/drawable/createanote.png?raw=true "Create A Note")

* Update A Note

![Update A Note Page](https://github.com/CihatBostanci/NoteApp/blob/main/app/src/main/res/drawable/updatenote.png?raw=true "Update A Note")


* Note List

![ Note List](https://github.com/CihatBostanci/NoteApp/blob/main/app/src/main/res/drawable/notelist.png?raw=true "Note List")


* Note Delete

![ Note Delete](https://github.com/CihatBostanci/NoteApp/blob/main/app/src/main/res/drawable/notedelete.png?raw=true "Note Deletet")
