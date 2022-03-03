package com.example.jetchat0

import ConversationContent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetchat0.components.JetchatScaffold
import com.example.jetchat0.data.exampleUiState
import com.example.jetchat0.profile.ProfileScreen
import com.example.jetchat0.profile.ProfileViewModel
import com.example.jetchat0.ui.theme.Jetchat0Theme
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ProvideWindowInsets(consumeWindowInsets = true) {
                Jetchat0App()
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Jetchat0App() {
        Jetchat0Theme {

            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = Closed)
            val drawerOpen by mainViewModel.drawerShouldBeOpened.collectAsState()
            val userData by profileViewModel.userData.observeAsState()

            if (drawerOpen) {
                // Open drawer and reset state in VM.
                LaunchedEffect(Unit)  {
                    drawerState.open()
                    mainViewModel.resetOpenDrawerAction()
                }
            }

            // Intercepts back navigation when the drawer is open
            val scope = rememberCoroutineScope()
            if (drawerState.isOpen) {
                BackHandler {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }

            JetchatScaffold(
                drawerState = drawerState,
                onChatClicked = {
                    navController.navigate(Screen.Conversation.route)
                       scope.launch {
                           drawerState.close()
                       }
                },
                onProfileClicked =  {
                    navController.navigate(Screen.Profile.passUserId(it))
                    scope.launch {
                        drawerState.close()
                    }
                }

            ) {



                NavHost(navController = navController, startDestination = Screen.Conversation.route) {

                    composable(Screen.Conversation.route) {
                        ConversationContent(
                            uiState = exampleUiState,
                            navigateToProfile = {
                                navController.navigate(Screen.Profile.passUserId(it))
                            },
                            onNavIconPressed = {
                                mainViewModel.openDrawer()
                            }
                        )
                    }

                    composable(
                       route = Screen.Profile.route,
                       arguments = listOf(navArgument(USER_ID) {
                           type = NavType.StringType
                       }
                       )
                    ) {
                        val userId = it.arguments?.getString(USER_ID)
                        profileViewModel.setUserId(userId)
                        ProfileScreen(
                            userData = userData!!,
                            onNavIconPressed = {
                                mainViewModel.openDrawer()
                            }
                        )
                    }

                }


            }
        }
    }


}







