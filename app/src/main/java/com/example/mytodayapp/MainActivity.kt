package com.example.mytodayapp

import android.os.Bundle
import android.telecom.Call.Details
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodayapp.ui.theme.MyTodayAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MainScreenContet(drawerState = DrawerState(initialValue = DrawerValue.Closed))
            }
        }
    }

@Composable
fun MainScreenContet(drawerState: DrawerState) {
    val scaffoldState = rememberScaffoldState( drawerState = drawerState)
    //scaffold.- e uma tag onde vc vai rechear as partes visuais
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                TopAppBar(
                    title = { Text(text = "TaskTodayApp")},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                CoroutineScope(Dispatchers.Default).launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null)
                        }
                    }
                )
        },
        drawerBackgroundColor = Color.Red,
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
               Box(
                   modifier = Modifier
                       .background(Color.Magenta)
                       .height(16.dp)
               ){
                   Text(text = "Opções!!")
               }
            Column() {
                Text(text = "Opçoes de menu 1")
                Text(text = "Opçoes de menu 1")
                Text(text = "Opçoes de menu 1")
            }
        },
        content = {
            paddingValues -> Log.i("paddinValues","$paddingValues")
            Column(
                //modifier =Modifier e tipo apontar o lugar varias vezes para nao errar, na assinatura do codigo tem uma ordem que deve ser seguida caso nao declarada.
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier.fillMaxWidth())
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Preparar aula LazyList/LazyColum",
                    taskDetails = "É bem melhor usar lazlist ao inves de colum",
                    deadEndDate = Date()
                )
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Prova Matematica",
                    taskDetails = "Estudar Calculo caplo 1 e 2",
                    deadEndDate = Date())
            }
        },
        bottomBar = {
            BottomAppBar(
                content = { Text("asdf")}
            )
        }
    )
}

@Composable
fun MySearchField(modificador: Modifier){
    TextField(
        value = "",
        onValueChange ={},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisa tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        }
    )
}

@Composable
fun MyTaskWidget(
        modificador: Modifier,
        taskName: String,
        taskDetails: String,
        deadEndDate: Date
){
    val dateFormatter = SimpleDateFormat("EEE,MMM DD, yyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Icons of a pendent task"
            )
            Text(
                text = dateFormatter.format(deadEndDate),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp
            )
        }
        Column(
            modifier = modificador
                .border(width = 1.dp, color = Color.Black)
                .padding(3.dp)
        ){
            Text(
                text = taskName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = taskName,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }//collum
    }//Row
    Spacer(modifier = Modifier.height(16.dp))
}//fimmmmm

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContet(drawerState = DrawerState(initialValue = DrawerValue.Closed))
}