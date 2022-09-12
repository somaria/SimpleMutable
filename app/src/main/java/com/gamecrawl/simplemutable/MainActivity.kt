package com.gamecrawl.simplemutable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.gamecrawl.simplemutable.ui.theme.SimpleMutableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: HomeViewModel by viewModels()

        setContent {
            SimpleMutableTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(viewModel)
                }
            }
        }
    }
}

data class Post(
    val title: String,
    val body: String,
    )

//define a list of 3 posts
val theposts = listOf(
    Post("Post 1", "This is the body of post 1"),
    Post("Post 2", "This is the body of post 2"),
    Post("Post 3", "This is the body of post 3"),
)

class HomeViewModel: ViewModel() {
    //create a mutable list of posts
    private val _posts = mutableStateListOf<Post>()
    val posts: List<Post> = _posts
    //add the posts to the mutable list
    init {
        _posts.addAll(theposts)
    }

    fun addPost(post: Post) {
        _posts.add(post)
    }

}

@Composable
fun Greeting(viewModel: HomeViewModel) {
    Column {
        Button(onClick = {
            viewModel.addPost(Post("Post 4", "This is the body of post 4"))
        }) {
            Text(text = "Add Post")
        }
        LazyColumn {
            items(viewModel.posts.size) { index ->
                Text(text = viewModel.posts[index].title)
            }
        }
    }

}

