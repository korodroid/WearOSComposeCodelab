/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.wearoscomposecodelab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
/*
// for supporting Non-Wear Navigation --from--
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
// for supporting Non-Wear Navigation --to--
 */
import androidx.wear.compose.material.*
// for supporting Wear Navigation --from--
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
// for supporting Wear Navigation --to--
import com.example.hellowearoscompose.model.ContactData
import com.example.hellowearoscompose.model.ContactDataList
import com.example.wearoscomposecodelab.presentation.theme.WearOSComposeCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

/**
 * Sample Contact Data
 */
val model = ContactDataList(
    data = listOf(
        ContactData(name = "Your Name", phone = "+81-3-0000-XXXX", nation = "Your Nation"),
        ContactData(name = "Kenichi", phone = "+81-3-1111-XXXX", nation = "US"),
        ContactData(name = "Josh", phone = "+1-3-2222-XXXX", nation = "Kenya"),
        ContactData(name = "Mary", phone = "+44-3-3333-XXXX", nation = "UK"),
        ContactData(name = "Susan", phone = "+43-3-4444-XXXX", nation = "Australia"),
        ContactData(name = "Han", phone = "+65-3-5555-XXXX", nation = "Singapore"),
        ContactData(name = "Chan", phone = "+94-3-6666-XXXX", nation = "Sri Lanka"),
        ContactData(name = "Lee", phone = "+66-3-7777-XXXX", nation = "Thai"),
        ContactData(name = "Woods", phone = "+670-3-8888-XXXX", nation = "East Timor"),
        ContactData(name = "Hoo", phone = "+33-3-9999-XXXX", nation = "France"),
    )
)

@Composable
fun WearApp() {
    WearOSComposeCodelabTheme {
//        ContactListScreenV0()
        ContactListScreenV1()
//        ContactListScreenV2()
//        ContactDetailScreen(0)
//        ScreenNavigationV4()
//        ScreenNavigationV5()
    }
}

/**
 * Show all contacts. (First ver.)
 */
@Composable
fun ContactListScreenV0() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

/**
 * Show all contacts. (Step.1) (Using ScalingLazyColumn instead of LazyColumn)
 */
@Composable
fun ContactListScreenV1() {
    // Add your STEP1 code here
    ScalingLazyColumn {
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

/**
 * Show all contacts. (Step.2) (Using Scaffold)
 */
@Composable
fun ContactListScreenV2() {
    // Add your STEP2 code here
}

/**
 * Show one of Contact Detail.
 */
@Composable
fun ContactDetailScreen(id: Int) {
    // Add your STEP3 code here
}

/**
 * Show all contacts. (Step.3) (Using Scaffold & Added NavController)
 */
@Composable
fun ContactListScreenV4(navController: NavHostController) {
    // Add your STEP4 code here
}

/*
 * Navigating Screen (can move forward, but cannot back to previous screen)
 */
@Composable
fun ScreenNavigationV4(
//    navController: NavHostController = rememberNavController()
) {
    // Add your STEP4 code here
}

/*
 * Navigating Screen (can move forward and back)
 */
@Composable
fun ScreenNavigationV5(
    navController: NavHostController = rememberSwipeDismissableNavController()
) {
    // Add your STEP5 code here
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview_Small_Round() {
    WearApp()
}

//@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
//@Composable
//fun DefaultPreview_Large_Round() {
//    WearApp()
//}
//
//@Preview(device = Devices.WEAR_OS_RECT, showSystemUi = true)
//@Composable
//fun DefaultPreview_Rect() {
//    WearApp()
//}
//
//@Preview(device = Devices.WEAR_OS_SQUARE, showSystemUi = true)
//@Composable
//fun DefaultPreview_Square() {
//    WearApp()
//}