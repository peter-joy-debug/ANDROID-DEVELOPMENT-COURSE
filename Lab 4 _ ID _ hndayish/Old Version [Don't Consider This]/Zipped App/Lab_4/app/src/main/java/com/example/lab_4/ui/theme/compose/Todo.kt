@file:OptIn(ExperimentalFoundationApi::class)
package com.example.lab_4.ui.theme.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lab_4.R
import com.example.lab_4.model.Task
import com.example.lab_4.model.TaskViewModel
import com.example.lab_4.ui.theme.ReplyVariantSec500
import com.example.lab_4.ui.theme.TaskTheme
import com.example.lab_4.ui.theme.WhiteFort
import java.util.*

@Composable
fun TodoScreen(navController: NavHostController) {
    val viewModel: TaskViewModel = viewModel()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var elevation = FloatingActionButtonDefaults.elevation(8.dp)
    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        floatingActionButtonPosition = FabPosition.End,
        drawerElevation = 200.dp,

        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(horizontal = 0.dp, vertical = 45.dp),
                onClick = {showDialog = true}

            )
            {
                Icon(Icons.Filled.Add, contentDescription = "")

            }
        },
        content = {
            if (showDialog){
                addTaskDialog(context, dismissDialog = {showDialog = false}, {viewModel.addNewTask(it)})
            }
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                )
            )
            {

                items(viewModel.taskList, key={ tasks -> tasks.taskId}) { teams ->
                    TaskItem(item = teams, context, {viewModel.deleteTask(it)},{viewModel.updateTask(it)},{viewModel.updateTaskFalse(it)})
                }
            }
        }
    )
}

@Composable
fun addTaskDialog(context: Context, dismissDialog:() -> Unit, addTeam: (Task) -> Unit){
    var taskTitleTextField by remember {
        mutableStateOf("")
    }
    var taskDescTextField by remember {
        mutableStateOf("")
    }


    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={ Text(text = stringResource(id = R.string.addTask), style = MaterialTheme.typography.h6) },
        text = {
            Column(modifier = Modifier.padding(top=20.dp)) {
                TextField(label = { Text(text= stringResource(id = R.string.teamName)) }, value = taskTitleTextField, onValueChange = {taskTitleTextField=it})
                Spacer(modifier = Modifier.height(10.dp))
                TextField(label = { Text(text= stringResource(id = R.string.taskDescription)) },value = taskDescTextField, onValueChange = {taskDescTextField=it})
            }
        },
        confirmButton = {
            Button(onClick = {
                if(taskTitleTextField.isNotEmpty()) {
                    val newID = UUID.randomUUID().toString();
                    val taskStatus: Boolean = false
                    addTeam(Task(newID, taskTitleTextField, taskDescTextField,taskStatus))
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.taskAdded),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.add))
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}
@Composable
fun updateData(context: Context, item: Task, updateTask: (Task) -> Unit) {
    updateTask(item)
}

fun updateDataFalse(context: Context, item: Task, updateFalseTask: (Task) -> Unit) {
    updateFalseTask(item)
}

@Composable
fun deleteTaskDialog(context: Context, dismissDialog:() -> Unit, item: Task, deleteTask: (Task) -> Unit){
    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={ Text(text = stringResource(id = R.string.delete), style = MaterialTheme.typography.h6) },
        confirmButton = {
            Button(onClick = {
                deleteTask(item)
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.deleteTask),
                    Toast.LENGTH_SHORT
                ).show()
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.yes))
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.no))
            }
        }
    )
}
@Composable
fun CheckBoxDemo(): Boolean {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    Checkbox(
        checked = checkedState,
        onCheckedChange = { checkedState = it }
    )
    return checkedState
}
@Composable
fun TaskItem(item: Task, context: Context, deleteTeam: (Task) -> Unit, updateStatus: (Task) -> Unit, updateStatusFalse: (Task) -> Unit) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var dataHandler by remember { mutableStateOf(false) }
    var result by rememberSaveable { mutableStateOf(false) }
    var msg:String = ""
    var colorChanger = ReplyVariantSec500;
    var backChanger = MaterialTheme.colors.primary;

    dataHandler = item.isCompleted
    if(dataHandler==true)
    {
        msg = "Completed"
        colorChanger = WhiteFort
        backChanger = ReplyVariantSec500
    }
    else
    {
        msg = "Pending..."
    }
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = backChanger,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(2.dp, color = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    Toast
                        .makeText(
                            context,
                            context.resources.getString(R.string.readmsg) + " " + item.taskTitle,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                },
                onLongClick = { showDeleteDialog = true }
            )
    ) {
        Row() {


            Column(

                modifier = Modifier
                    .padding(16.dp)
                    .background(colorChanger)
                    .clip(
                        CircleShape
                    )
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            ) {
                var id:Int = 0
                result = CheckBoxDemo()
                if(result==true)
                {
                    updateData(context, item, updateStatus)
                    Toast
                        .makeText(
                            context,
                            context.resources.getString(R.string.clicked) + " " + item.taskTitle +" is completed" ,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                else
                {
                    updateDataFalse(context, item, updateStatusFalse)
                    Toast
                        .makeText(
                            context,
                            context.resources.getString(R.string.clicked) + " " + item.taskTitle +" is pending",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = item.taskTitle, style = MaterialTheme.typography.body1)
                Text(text = item.taskDescription, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(15.dp))

                Text(text = msg, style = MaterialTheme.typography.h6, modifier = Modifier
                    .border(BorderStroke(2.dp, colorChanger))
                    .padding(6.dp)
                    .clip(
                        shape = RoundedCornerShape(50.dp)
                    ))

            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {


            }


        }


    }
    if (showDeleteDialog){
        deleteTaskDialog(context, dismissDialog = {showDeleteDialog = false}, item, deleteTeam)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskTheme {
//        TodoScreen()
    }
}