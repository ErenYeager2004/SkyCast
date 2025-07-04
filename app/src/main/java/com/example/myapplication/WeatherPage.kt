import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.WeatherViewModel
import com.example.myapplication.api.NetworkResponse
import com.example.myapplication.api.WeatherModel
import java.nio.file.WatchEvent

@Composable
fun WeatherPage(viewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("") }

    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally// adjust as needed
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = { city = it },
                label = { Text("Search for any location") },

            )
            IconButton(onClick ={
                viewModel.getData(city)
                keyboardController?.hide()
            } ) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search for any location")
            }
        }
        when(val result  = weatherResult.value) {
            is NetworkResponse.Error ->
            {
                Text(text = result.message)
            }
            NetworkResponse.Loading ->
            {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success ->{
                WeatherDetails(data = result.data)
            }
            null -> {}
        }
    }
}

@Composable
fun WeatherDetails(data : WeatherModel)
{
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ){
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location icon",
            modifier = Modifier.size(40.dp)
        )
        Text(text = data.location?.name.toString(), fontSize = 30.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = data.location?.country.toString(), fontSize = 18.sp, color = Color.Gray)
    }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${data.current?.tempC.toString()} °C",
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current?.condition?.icon}".replace("65x64","128x128"),
            contentDescription = "Condition icon"
        )
        Text(
            text = data.current?.condition?.text.toString(),
            fontSize = 20.sp,
            color = Color.Gray ,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card{
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyVal("Humidity", data.current?.humidity.toString())
                    WeatherKeyVal("Wind Speed", data.current?.windKph.toString() + " km/h")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyVal("UV", data.current?.uv.toString())
                    WeatherKeyVal("Precipitation", data.current?.precipMm.toString() + " mm")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyVal("Local Time", data.location?.localtime.toString().split(" ")[1])
                    WeatherKeyVal("Local Date", data.location?.localtime.toString().split(" ")[0])
                }
            }

        }
    }
}

@Composable
fun WeatherKeyVal(key : String, value : String){
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = key, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Text(text = value,fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}
