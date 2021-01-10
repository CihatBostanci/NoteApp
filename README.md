# NoteApp

### Deadline

We'll be waiting for your solution within 4 days.

### Goal ###

Develop a simple note app that allows the user to save/edit/delete any kind of note and display them in a list.

### Functional Requirements ###

* Kotlin is preferred but not a must.
* Users must be able to create notes with input fields such as title, description, image url (input can be optional) and store it locally on their phones.
* Created note must contain a created date.
* There must be a way to display all saved notes in the list. An item on the list must contain the created date (dd/mm/yyyy), the image if url is available, title and max. 2 lines of description.
* There must be a way to edit/delete previously created notes. But edited notes must contain an (edited) tag somewhere while being displayed on the list.
* All data should be persisted locally.

### UI Suggestions ###

It doesn't need to be super pretty, but it shouldn't be broken as well. The design is mostly up to you as long as creating, listing and editing/deleting features are available to use.

Nice to have:
* Animations/Transitions
* At least one custom view

### Expectations ###

Consider this as a showcase of your skills.
Approach it as if you are going to make a pull request on our main/master branch.

We are expecting at least:
* Clear, defined architecture.
* Apply the Material Design Guidelines as much as possible.
* Meaningful tests (You do not need to have 100% coverage, but we will be looking for tests).
* Good and lint verified syntax.
* The repo should not contain any references to Getir in it.
* The code must compile.
* The code must be production ready. Unit tests are expected.

### Realized While Writing This App  ###

While doing this app, I want to explain my tech stack in android development process.
* Firstly, This app created with MVVM(Model-View-ViewModel) that is recommended by Google and written on Kotlin Language 100%.
* While using MVVM, I chose Single Activity - Multiple Fragment Structure with JetPack Navigation Architecture Components
* On the other hand we know that if the app is not big enough as we can use Single Activity-Multiple Fragments structure otherwise it can not be good performance such as CPU, Memory Allocation and Memory Leaks etc.
* Besides that, I used Light weight Koin Dependency Injection Library works as Service Locater that provides SOLID principles and doing maintainable test easily.
* While doing database operation, I used Coroutines to handle. It can easily lifecycle aware and lightweight thread system to deal with Input Output operations with IO Thread. Thus Main thread may not overload.
* For the UI, the Material Library used in this project as possible and use some Animations and Transitions.
* For the local data provider, I used Room SQLite DataBase and SharedPreferences.
* Business Operation are in the following; Users can edit, delete and create their notes. There are two tables one - to - many relationships each other that are NoteModel(represents Many) and UserModel (represents One)
* For the Helper to User Operations, I Used Shared Preferences Manager while create, edit and delete operations and to handle Users.
* Unit tests were written for repository and view model
* Trying to use Kotlin Lint and Android name conventions.

