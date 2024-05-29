package io.github.frankieshao.refreshlayout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @author Frank Shao
 * @created 28/05/2024
 * Description:
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshLayout(
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    state: PullToRefreshState = rememberPullToRefreshState(),
    onRefresh: () -> Unit,
    indicator: @Composable () -> Unit = {
        DefaultRefreshIndicator(
            progress = state.distanceFraction,
            isRefreshing = isRefreshing,
            animatorSpec = AnimatorSpec(
                resId = R.raw.loading,
                width = 30,
                height = 30
            )
        )
    },
    content: @Composable BoxScope.() -> Unit
) {
    Column(modifier = modifier) {
        indicator()
        Box(
            Modifier
                .pullToRefresh(
                    state = state,
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh
                )
        ) {
            content()
        }
    }
}

