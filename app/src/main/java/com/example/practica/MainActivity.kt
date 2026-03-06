package com.example.practica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practica.data.UserSession
import com.example.practica.ui.theme.practicaTheme
import com.example.practica.ui.theme.practicaTheme
import com.example.practica.ui.view.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            practicaTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "onboarding", // Изменено с "onboard1" на "onboarding"
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Единый экран онбординга вместо трех отдельных
                        composable("onboarding") { OnboardingScreen(navController) }

                        composable("login") { LoginScreen(navController = navController) }
                        composable("register") { RegisterScreen(navController = navController) }

                        composable("home") { HomeScreen(navController = navController) }
                        composable("profile") {
                            val userId = UserSession.userId
                            val accessToken = UserSession.accessToken

                            if (userId != null && accessToken != null) {
                                ProfileScreen(
                                    navController = navController,
                                    userId = userId,
                                    accessToken = accessToken
                                )
                            } else {
                                LoginScreen(navController = navController)
                            }
                        }

                        composable("forgot_password") {
                            ForgotPasswordScreen(navController)
                        }

                        composable(
                            route = "verifyOTP/{email}/{type}",
                            arguments = listOf(
                                navArgument("email") { type = NavType.StringType },
                                navArgument("type") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            val type = backStackEntry.arguments?.getString("type") ?: "signup"
                            VerifyOTPScreen(
                                navController = navController,
                                email = email,
                                otpType = type
                            )
                        }

                        composable(
                            route = "new_password/{email}",
                            arguments = listOf(
                                navArgument("email") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val email = backStackEntry.arguments?.getString("email") ?: ""
                            NewPasswordScreen(navController = navController, email = email)
                        }
                    }
                }
            }
        }
    }
}