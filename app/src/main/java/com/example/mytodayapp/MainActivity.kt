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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodayapp.model.Tarefa.Tarefa
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
    var scope = rememberCoroutineScope()
    //scaffold.- e uma tag onde vc vai rechear as partes visuais
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                TopAppBar(
                    title = { Text(text = "TaskTodayApp")},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch{
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

                val tProvaDeCalculo = Tarefa(
                    "Estudar prova calclo",
                    "do Livro tal",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tProvaDeKotlin = Tarefa(
                    "Estudar Proa de kotlin",
                    "teste Livro",
                    Date(),
                    Date(),
                    status= 0.0 ///CONSIDERANDO 0 por cento de 100 realizado
                )

                var MinhaListaDeTarefas= listOf<Tarefa>(tProvaDeCalculo,tProvaDeKotlin)

                MyTasksWidgetList(MinhaListaDeTarefas);


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
fun MyTasksWidgetList(ListaDeTarefas: List<Tarefa>){
    //algo para o usuario roolar na tela
    ListaDeTarefas.forEach(action = {Log.i("###33333333333333######","${it.nome}")})

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
        tarefaASerMostrada: Tarefa

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