package com.example.clase7

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(navController: NavController) {
    val context = LocalContext.current

    var curso by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var semestre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }
    var archivosAdjuntos by remember { mutableStateOf("N/A") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.report_screen_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.content_description_icon_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.report_screen_header),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = curso,
                onValueChange = { curso = it },
                label = { Text(stringResource(R.string.report_field_course)) },
                leadingIcon = { Icon(Icons.Default.School, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it },
                label = { Text(stringResource(R.string.report_field_year)) },
                leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = semestre,
                onValueChange = { semestre = it },
                label = { Text(stringResource(R.string.report_field_semester)) },
                leadingIcon = { Icon(Icons.Default.Star, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text(stringResource(R.string.report_field_date)) },
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = comentarios,
                onValueChange = { comentarios = it },
                label = { Text(stringResource(R.string.report_field_comments)) },
                leadingIcon = { Icon(Icons.Default.Comment, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )
            
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {
                    Toast.makeText(context, context.getString(R.string.report_toast_attaching), Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Default.AttachFile, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(stringResource(R.string.report_button_attach))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.report_label_attached, archivosAdjuntos),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    Toast.makeText(context, context.getString(R.string.report_toast_saved), Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.report_button_save))
            }
        }
    }
}