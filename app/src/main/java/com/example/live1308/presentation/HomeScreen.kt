package com.example.live1308.presentation

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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.live1308.utils.OutlinedAgeTextField
import com.example.live1308.utils.RoundedTextField

@Composable
@Preview(showBackground = true)
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel()
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
            items(getUsers.value) { person ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
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
            }
        }
    }
}