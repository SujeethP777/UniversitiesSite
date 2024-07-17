package com.aagnia.universitiessite.fetching

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aagnia.domain.responseModels.UniversitiesResponseContainer

@Composable
fun Universities(
    modifier: Modifier = Modifier,
    universitiesViewModel: UniversitiesViewModel = viewModel(),
    country: String
) {

    val universitiesState = universitiesViewModel.universities.collectAsState()

    LaunchedEffect(true){
        universitiesViewModel.universities(country)
    }
    LazyColumn {
        items(universitiesState.value) { university ->
            UniversityItem(modifier, university)
        }
    }
}

@Composable
fun UniversityItem(
    modifier: Modifier = Modifier,
    university: UniversitiesResponseContainer) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .clickable {
                val webPage = university.webPages.firstOrNull()
                if (webPage != null) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webPage))
                    context.startActivity(intent)
                }
            }
            .padding(16.dp)
    ) {
        Text(text = "Name: ${university.name}")
        Text(text = "Country: ${university.country}")
        Text(text = "State/Province: ${university.stateProvince ?: "N/A"}")
        Text(text = "Domains: ${university.domains.joinToString(", ")}")
        Text(text = "Web Pages: ${university.webPages.joinToString(", ")}")
        HorizontalDivider(thickness = 1.dp, color = Color.Black)
    }
}