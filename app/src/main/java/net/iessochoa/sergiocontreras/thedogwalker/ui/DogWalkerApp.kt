package net.iessochoa.sergiocontreras.thedogwalker.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.iessochoa.sergiocontreras.thedogwalker.R
import net.iessochoa.sergiocontreras.thedogwalker.data.DogRepository
import net.iessochoa.sergiocontreras.thedogwalker.ui.screens.DogListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogWalkerApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { DogWalkerTopAppBar(
            title = stringResource(R.string.app_name),
            canNavigateBack = false
        ) }
    ) { innerPadding ->
        DogListScreen(
            DogRepository.getAllDogs(),
            {},
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogWalkerTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

