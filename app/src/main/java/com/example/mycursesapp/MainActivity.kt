@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycursesapp.ui.theme.MyCursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCursesAppTheme {
                // Navigation System
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    // 1 - Nav Controller
                    val navController = rememberNavController()
                    // 2 - Nav Host
                    NavHost(navController = navController, startDestination = "home") {
                        // 3 - Nav Graph Builder
                        composable("home") {
                            HomeScreen(
                                onDetailsClick = { title ->
                                    navController.navigate("details/title=$title")
                                },
                                onAboutClick = {
                                    navController.navigate("about")
                                }
                            )
                        }
                        composable("about") {
                            AboutScreen(
                                onNavigateUp = { navController.popBackStack() }
                            )
                        }
                        composable(
                            "details/title={title}",
                            arguments = listOf(
                                navArgument("title") {
                                    type = NavType.StringType
                                    nullable = true
                                }
                            ),
                        ) { backStackEntry ->
                            val arguments = requireNotNull(backStackEntry.arguments)
                            val title = arguments.getString("title")
                            if (title != null) {
                                DetailsScreen(
                                    title = title,
                                    onNavigateUp = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Home Screen
@Composable
fun HomeScreen(
    onDetailsClick: (title: String) -> Unit,
    onAboutClick: () -> Unit,
) {
    Scaffold { padding ->
        LazyColumn(contentPadding = padding) {
            item {
                HomeAppBar(onAboutClick)
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            items(allCourses) { item ->
                CourseCard(
                    item, onClick = {
                        onDetailsClick(item.title)
                    }
                )
            }
        }
    }
}

@Composable
private fun HomeAppBar(onAboutClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(text = "My Udemy Courses", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onAboutClick) {
            Text(text = "About", fontSize = 24.sp)
        }
    }
}

@Composable
fun CourseCard(item: Course, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(), onClick = onClick
    ) {
        Column {
            Image(
                painterResource(item.thumbnail), contentDescription = null,
                Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = item.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.body, maxLines = 1,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun AboutScreen(onNavigateUp: () -> Unit) {
    Scaffold { padding ->
        Column(Modifier.padding(padding)) {
            AppBar(title = "About", onNavigateUp)
            Spacer(modifier = Modifier.height(20.dp))
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = "This app is a demonstration about the navigation" +
                            "in android jetpack compose"
                )
                Spacer(modifier = Modifier.height(20.dp))
                val udemyLink = LocalUriHandler.current
                Button(onClick = {
                    udemyLink.openUri("https://www.udemy.com/")

                }) {
                    Text(text = "View Our Udemy Courses")

                }
            }
        }
    }
}

@Composable
fun AppBar(title: String, onNavigateUp: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        IconButton(onClick = onNavigateUp) {
            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = "Go Back"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = title, fontSize = 24.sp)
        }
    }
}

@Composable
fun DetailsScreen(title: String, onNavigateUp: () -> Unit) {
    //Searching for the current course
    val course = allCourses.first { it.title == title }
    Scaffold { padding ->
        Column(Modifier.padding(padding)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            }
            Image(
                painterResource(id = course.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = course.title, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = course.body,
                    Modifier.fillMaxSize(),
                    fontSize = 16.sp
                )
            }
        }
    }
}
