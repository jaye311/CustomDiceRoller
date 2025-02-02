package com.example.customdice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customdice.ui.theme.CustomDiceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomDiceTheme  {
                DiceRollerApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
        )

}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier){
    var result by remember{mutableIntStateOf(1)}
    var max by remember{mutableStateOf("6")}
    TextField(
        modifier = Modifier.height(150.dp).fillMaxWidth(),
        value = max,
        onValueChange = {max = it},
        label = {Text("Max",
            fontSize = 50.sp)},
        textStyle = TextStyle.Default.copy(fontSize = 50.sp)
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            "$result",
            fontSize = 100.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {result = (1..noTrouble(max)).random()}){
            Text("Roll")
        }
    }
}

private fun noTrouble(m: String): Int {
    try{
        val g= Integer.parseInt(m)
        if(g>=1)
            return g
    }
    catch(e: Exception){
        println("Invalid Number")
    }
    return 6
}

