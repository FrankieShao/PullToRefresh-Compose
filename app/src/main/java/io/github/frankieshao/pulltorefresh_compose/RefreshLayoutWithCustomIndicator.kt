package io.github.frankieshao.pulltorefresh_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.frankieshao.refreshlayout.AnimationLoader
import io.github.frankieshao.refreshlayout.RefreshLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * @author Frank Shao
 * @created 28/05/2024
 * Description:
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshLayoutWithCustomIndicator() {
    var itemCount by remember { mutableIntStateOf(15) }
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(1500)
            itemCount += 5
            isRefreshing = false
        }
    }
    val state: PullToRefreshState = rememberPullToRefreshState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                // Provide an accessible alternative to trigger refresh.
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Filled.Refresh, "Trigger Refresh")
                    }
                }
            )
        }
    ) {
        RefreshLayout(
            modifier = Modifier.padding(it),
            isRefreshing = isRefreshing,
            onRefresh = { onRefresh() },
            state = state,
            indicator = {
                CustomIndicator(
                    progress = state.distanceFraction,
                    isRefreshing = isRefreshing
                )
            }
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(itemCount) {
                    ListItem({ Text(text = "Item ${itemCount - it}") })
                }
            }
        }
    }
}

@Composable
fun CustomIndicator(
    progress: Float,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier
) {
    val rowHeight = if (isRefreshing) { 60.dp } else { (progress * 60).roundToInt().dp }
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(rowHeight)
            .padding(top = 16.dp)
    ) {
        AnimationLoader(
            modifier = Modifier.height(50.dp).width(50.dp),
            isPlaying = isRefreshing,
            resId = R.raw.loading_2
        )
    }
}


