package com.example.live1308.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.live1308.R
import com.example.live1308.data.User
import com.example.live1308.utils.OutlinedAgeTextField
import com.example.live1308.utils.RoundedTextField

@Composable
//@Preview(showBackground = true)
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    user: User
) {
    val getUsers = vm.users.collectAsStateWithLifecycle()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(75.dp))
        RoundedTextField(
            value = name,
            onValueChange = { name = it },
            labelText = "Name"
        )
        Spacer(modifier = Modifier.height(15.dp))
        RoundedTextField(
            value = email,
            onValueChange = { email = it },
            labelText = "Email"
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedAgeTextField(
                value = age,
                onValueChange = { age = it },
                labelText = "Age"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    vm.addUser(
                        name = name,
                        email = email,
                        age = age.toInt()
                    )
                    name = ""
                    email = ""
                    age = ""
                }
            ) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            items(
                getUsers.value,
                key = { it.id }
            ) { person ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { value ->

                        if (value == SwipeToDismissBoxValue.EndToStart) {
                            vm.deleteUser(
                               person
                            )
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    modifier = Modifier.fillMaxWidth(),

                    backgroundContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Red)
                                .padding(12.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {

                            Icon(
                                painter = painterResource(R.drawable.ic_delete),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                            )
                        }
                    },
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                        ) {
                            Text("Name: ${person.name}")
                            Text("Email: ${person.email}")
                            Text("Age: ${person.age}")
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.Gray)
                            )
                        }
                    },
                    enableDismissFromStartToEnd = false
                )
            }
        }
    }
}
