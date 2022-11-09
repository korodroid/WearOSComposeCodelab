author: Kenichi Kambara
summary: Wear OS Codelab
id: codelab-wearos-markdown
categories: codelab,markdown
environments: Wearable
status: Published
feedback link: https://github.com/korodroid/hoge
analytics account:

# Building for WearOS Apps Codelab

## Introduction
Duration: 0:05:00

This Codelab explains how to develop WearOS Apps with Jetpack Compose.

"Compose for Wear OS" simplifies and accelerates UI development and helps you create beautiful apps with less code. Currently, it's the most recommended approach for Wear OS Apps Development.

For this codelab, we expect that you have some knowledge of Compose and Kotlin, but you certainly don't need to be an expert.

At this codelab, it starts from the basic sample. And you will learn how to improve Wear OS apps with Jetpack Compose little by little. Finally, you can start writing your own apps for Wear OS. Let's get started!

### Wear OS Apps Examples

![Sample1](./images/introduction/Sample1.png)
![Sample2](./images/introduction/Sample2.png)


### What you will learn

- How to get started Wear OS Apps Development with Jetpack Compose
- Similarities/differences between Mobile and WearOS experience with Jetpack Compose
- Basic Wear OS composables
- How to optimize screens for Wear OS
- How to get improve Wear OS UI & UX with useful composables


### Prerequisites

- Basic understanding of [Android development](https://developer.android.com/)
- Basic understanding of [Kotlin](https://developer.android.com/kotlin)
- Basic knowledge of [Compose](https://developer.android.com/jetpack/compose)

### What you will build

At first, you'll build a simple app that displays just a scrollable list of composables. It's just like a simple Contacts App. But there are some problems. Screens aren't opzimized for Wear OS. It includes only one screen. Also, it doesn't include any navigations.

Here are some steps for improving UI & UX.

By modifing the code little by little, finally you'll find how we develop the Wear OS screens.

Because you will be using Scaffold, you'll also get a curved text time at the top, a vignette, and finally a scrolling indicator tied to the side of the device.

Here's what it will look like when you are finished with the code lab:

![Preparation](./images/workshop/Step5.gif)


## Getting Set Up (in advance)

### What you will need

- Android Studio
	- Mandatory: Dolphin, Electric Eel or Flamingo
	- Recommendation for this workshop: Electric Eel
- Wear OS AVD images
	- Recommendation for this workshop: API Level 30

### How to create Wear OS AVD

![Preparation](./images/preparation/SetupAVD.gif)

### Download code

If you have git installed, you can simply run the command below to clone the code from [this repo](https://github.com/korodroid/WearOSComposeCodelab). To check whether git is installed, type ``git --version`` in the terminal or command line and verify that it executes correctly.

```
git clone https://github.com/korodroid/WearOSComposeCodelab.git
cd WearOSComposeCodelab
```

If you do not have git, you can click the following button to download all the code for this codelab:

<button>
[Download ZIP](https://github.com/korodroid/WearOSComposeCodelab/archive/refs/heads/main.zip)
</button>


## Run the Base App
Duration: 0:10:00

### Open project in Android Studio

1. On the Welcome to Android Studio window select ![folder](./images/icons/folder.png) Open an Existing Project
2. Select the folder ``[Download Location]``
3. When Android Studio has imported the project, test that you can run the ``start`` and ``finished`` modules on a Wear OS emulator or physical device. Also, there are some modules ``step1``, ``step2``, ``step3`` and ``step4`` for each step.
4. The modules should look like the screenshot below.
![1stStep](./images/workshop/Env1.png)

5. Choose ``start`` module. It's where you will be doing all your work.
![1stStep](./images/workshop/Env2.png)

### Explore the ``start`` code

- **build.gradle** contains a basic app configuration. It includes the dependencies necessary to create a Composable Wear OS App. We'll discuss what's similar and different between Jetpack Compose and the Wear OS version.
- **main > AndroidManifest.xml** includes the elements necessary to create a Wear OS application. This is the same as a non-Compose app and similar to a mobile app, so we won't review this.
- **main > theme/** folder contains the Color, Type, and Theme files used by Compose for the theme.
- **main > MainActivity.kt** contains boilerplate for creating an app with Compose. It also contains the top-level composables (like the Scaffold and ScalingLazyList) for our app.

### Result

Positive
: You can also refer an answer (`start` module)

![Step0](./images/workshop/Step0.gif)



## Step1
Duration: 0:10:00

Positive
: In a short word, ``Setting your name on the list`` and ``Replacing LazyColumn with ScalingLazyColumn``.

We will do all our work in the

``start``

module, so make sure every file you open is in there.

Let's start by opening ``MainActivity`` in this module.

This is a class that extends ``ComponentActivity`` and uses ``setContent { WearApp() }`` to create the UI.

Scroll down to the this part like this.

```kotlin
val model = ContactDataList(
    data = listOf(
        ContactData(name = "Your Name", phone = "+81-3-0000-XXXX", nation = "Your Nation"),
        ContactData(name = "Kenichi", phone = "+81-3-1111-XXXX", nation = "US"),
        ContactData(name = "Josh", phone = "+1-3-2222-XXXX", nation = "Kenya"),
        // ...
)
```

If you don't enable your preview screen, just tap the **Preview** button like this. You can check your screen without compiling.

![Step1-1](./images/workshop/Step1-1.png)

Let's set your name, phone number (should be dummy) and nation on **the first element** like this.

```kotlin
val model = ContactDataList(
    data = listOf(
        ContactData(name = "Frank", phone = "+1-3-8888-XXXX", nation = "Kenya"),
        ContactData(name = "Kenichi", phone = "+81-3-1111-XXXX", nation = "US"),
        ContactData(name = "Josh", phone = "+1-3-2222-XXXX", nation = "Kenya"),
        // ...
)
```

You'll be able to see the change in real time like this.

![Step1-2](./images/workshop/Step1-2.png)

Search for **ContactListScreenV0** and replace ``LazyColumn`` with ``ScalingLazyColumn`` like this.

```kotlin
ScalingLazyColumn {
    item {
        ListHeader {
            Text(text = "Contacts")
        }
    }
// ...
```

### Result

Your screen should be improved like this.

![Step1](./images/workshop/Step1.gif)

But there are still some problems.

Positive
: You can also refer an answer (On `step1` module, `ContactListScreenV1()` function)



## Step2
Duration: 0:10:00

Positive
: In a short word, ``Using a Scaffold``.

Here are three essential points to develop a better UI for Wear OS.

- **TimeText**
- **Vignette**
- **PositionIndicator**

Using a ``Scaffold`` , you can easily implement them.

Search for **ContactListScreenV0** and implement like this.

```kotlin
val listState = rememberScalingLazyListState()
    Scaffold(
        timeText = {
            if (!listState.isScrollInProgress) {
                TimeText()
            }
        },
        vignette = {
            Vignette(
                vignettePosition = VignettePosition.TopAndBottom
            )
        },
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState,
            )
        }
    ) {
        ScalingLazyColumn(state = listState) {
            item {
                ListHeader {
                    Text(text = "Contacts")
                }
            }
            items(model.data.size) {
                Chip(
                    modifier = Modifier
                        .fillMaxSize(),
                    icon = {
                        Icon(
                            Icons.Rounded.Face,
                            contentDescription = "faceIcon",
                        )
                    },
                    label = { Text(model.data[it].name) },
                    colors = ChipDefaults.primaryChipColors(),
                    onClick = { },
                )
            }
        }
    }
```


### Result

Your screen should be improved like this. This new screen includes TimeText, Vignette and PositionIndicator. 

![Step2](./images/workshop/Step2.gif)

But this app includes only one screen. So we cannot check each contact detail.

Positive
: You can also refer an answer (On `step2` module, `ContactListScreenV2()` function)



## Step3
Duration: 0:10:00

Positive
: In a short word, ``Adding a new Screen``.

### What we'd like to develop

![Step3](./images/workshop/Step3.png)

Let's add a new screen, the Contact Detail screen.
Search for **ContactDetailScreen** and implement like this.

```kotlin
@Composable
fun ContactDetailScreen(id: Int) {
    // Add your STEP3 code here
    Chip(
        modifier = Modifier
            .fillMaxSize(),
        icon = {
            Icon(
                Icons.Rounded.Face,
                contentDescription = "faceIcon",
            )
        },
        label = {
            Column(
            ) {
                Text(text = model.data[id].name, fontWeight = FontWeight.Bold)
                Text(text = model.data[id].phone)
                Text(text = model.data[id].nation)
            }
        },
        colors = ChipDefaults.primaryChipColors(),
        onClick = { /* do anything */ },
    )
}
```

To confirm the new screen, change the content of ``WearApp()`` like this.

```kotlin
@Composable
fun WearApp() {
    WearOSComposeCodelabTheme {
        ContactDetailScreen(0)
    }
}
```


### Result

You'll be able to see the change like this.

![Step3-1](./images/workshop/Step3-1.png)

But this app doesn't include any Navigation features. So we cannot move to another screen.

Positive
: You can also refer an answer (On `step3` module, `ContactDetailScreen()` function)



## Step4
Duration: 0:10:00

Positive
: In a short word, ``Implementing Navigation``.

Let's add a new Navigation feature to move to another screen.
First, search for **ContactListScreenV0** and add an argument like this.

```kotlin
@Composable
fun ContactListScreenV0(navController: NavHostController) {
    // ...
}
```

Next, on ``ScalingLazyColumn`` part, add implementation like this. 

```kotlin
Chip(
    // ...

    onClick = { navController.navigate("contact_detail/$it") },
)
```

Finally, let's add the Navigation feature. Search for **ScreenNavigationV4** and implement like this.

```kotlin
@Composable
fun ScreenNavigationV4(
    navController: NavHostController = rememberNavController()
) {
    // Add your STEP4 code here
    NavHost(
        navController = navController,
        startDestination = "contact_list"
    ) {
        composable("contact_list") {
            ContactListScreenV0(navController = navController)
        }
        composable("contact_detail/{id}") {
            val id = it.arguments?.getString("id")!!
            ContactDetailScreen(id = id.toInt())
        }
    }
}
```

To change the launch screen, change the content of ``WearApp()`` like this.

```kotlin
@Composable
fun WearApp() {
    WearOSComposeCodelabTheme {
        ScreenNavigationV4()
    }
}
```


### Result

Let's launch the app on the Emulator or the real device. You'll be able to move to another screen like this.

![Step4](./images/workshop/Step4.gif)

But if you try to move back to the previous screen, you won't able to do that. It's because there is still a problem.

Positive
: You can also refer an answer (On `step4` module, `ContactListScreenV4()` and `ScreenNavigationV4()` function)



## Step5
Duration: 0:10:00

Positive
: In a short word, ``Replacing Regular Navigation with Wear Navigation``.

Here is an important point to develop a Navigation feature for Wear OS. Using the Wear Navigation instead of the Regular Navigation is required.

Let's import the Wear Navigation library by opening ``build.gradle`` under  this module. Modify like this. It means that disabling **Non Wear Navigation Part** and enabling **Wear Navigation Part**. Afther that, sync the project to import the new library.

```gradle
// Non Wear Navigation
//    def nav_version = "2.5.2"
//    implementation "androidx.navigation:navigation-compose:$nav_version"
// Wear Navigation
implementation "androidx.wear.compose:compose-navigation:$wear_compose_version"
```

You'll see some errors on ``MainActivity``. It means we have to change **some import definitions** like this.

```gradle
// for supporting Non-Wear Navigation --from--
//import androidx.navigation.NavHost
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.compose.composable
// for supporting Non-Wear Navigation --to--
// for supporting Wear Navigation --from-
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
// for supporting Wear Navigation --to--
```

Maybe you'll still see some errors. Don't take care. On ``ScreenNavigationV4()`` function, change your code like this. Instead of using ``rememberNavController()``, use ``rememberSwipeDismissableNavController()``.

```kotlin
fun ScreenNavigationV4(
    navController: NavHostController = rememberSwipeDismissableNavController()
) {
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "contact_list"
    ) {
        composable("contact_list") {
            ContactListScreenV4(navController = navController)
        }
        composable("contact_detail/{id}") {
            val id = it.arguments?.getString("id")!!
            ContactDetailScreen(id = id.toInt())
        }
    }
}
)
```


### Result

Let's launch the app on the Emulator or the real device again. You'll be able to move to another screen and back to the previous screen like this.

![Finished](./images/workshop/Step5.gif)

Congratulations! That's it.

Positive
: You can also refer an answer (On `finished` module, `ContactListScreenV5()` function)


## See also

You learned how to build and improve your app for Wear OS! Here are some more ufeful materials by Google.

- [Codelabs for Wear OS by Google](https://codelabs.developers.google.com/?cat=flutter&product=wearos)

We made this tutorial with reference to them. 

Happy Wear OS Apps coding!