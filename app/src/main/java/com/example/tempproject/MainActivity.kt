package com.example.tempproject

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.tempproject.bininfo.BinInfo
import com.example.tempproject.ui.theme.TempProjectTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TempProjectTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val searchedBIN by viewModel.lastBINInfo.collectAsState()
    val errorString by viewModel.isFailure.collectAsState()
    var enteredBIN by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataStore(context)
    val binHistory by dataStore.getBinHistory.collectAsState("")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorString)
        PrintBinInfo(searchedBIN, context)
        Spacer(modifier = Modifier.size(8.dp))
        TextField(value = enteredBIN.toString(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            onValueChange = { enteredBIN = it.toInt() })
        Spacer(modifier = Modifier.size(8.dp))
        Row {
            Button(onClick = {
                if (enteredBIN != 0) viewModel.searchEnterBIN(enteredBIN)
            }) {
                Text(text = "Поиск")
            }
            Button(onClick = {
                scope.launch { dataStore.saveBIN(searchedBIN) }
            }) {
                Text(text = "Сохранить")
            }
        }
        Text(text = binHistory!!)
    }
}

@Composable
fun PrintBinInfo(binInfo: BinInfo, context: Context) {
    LazyColumn {
        item {
            LazyRow(verticalAlignment = Alignment.CenterVertically) {
                item {
                    ClickableText(
                        text = AnnotatedString(binInfo.bank.city),
                        onClick = {
                            startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + binInfo.bank.city)), null)
                        })
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = binInfo.bank.name)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    ClickableText(
                        text = AnnotatedString(binInfo.bank.phone),
                        onClick = {
                            startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + binInfo.bank.phone)), null)
                        })
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    ClickableText(
                        text = AnnotatedString(binInfo.bank.url),
                        onClick = {
                            startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse("http://" + binInfo.bank.url)), null)
                        })
                }
            }
            Text(text = binInfo.brand)
            LazyRow {
                item {
                    Text(text = binInfo.country.alpha2)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = binInfo.country.currency)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = binInfo.country.emoji)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = binInfo.country.latitude.toString())
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = binInfo.country.longitude.toString())
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = binInfo.country.name)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = binInfo.country.numeric)
                }
            }
            LazyRow {
                item {
                    Text(text = binInfo.number.length.toString())
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = binInfo.number.luhn.toString())
                }
            }
            Text(text = binInfo.prepaid.toString())
            Text(text = binInfo.scheme)
            Text(text = binInfo.type)
        }
    }

}

@Preview
@Composable
private fun PreviewMainScreen() {
    TempProjectTheme {
        MainScreen(
            viewModel = MainViewModel(),
        )
    }
}
