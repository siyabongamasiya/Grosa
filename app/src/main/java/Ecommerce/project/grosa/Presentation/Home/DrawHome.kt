package Ecommerce.project.grosa.Presentation.Home

import Ecommerce.project.grosa.Presentation.Components.CustomTab
import Ecommerce.project.grosa.Presentation.Components.TopBar
import Ecommerce.project.grosa.Presentation.Groceries.DrawGroceries
import Ecommerce.project.grosa.Presentation.Products.DrawProducts
import Ecommerce.project.grosa.Utils.Tabs
import Ecommerce.project.grosa.Utils.listofmenuchoices
import Ecommerce.project.grosa.ui.theme.GrosaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@Composable
fun DrawHome(navController: NavController){
    GrosaTheme {


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(isHome = true){
                    navController.navigateUp()
                }
            }) {paddingValues ->
            midSectionHome(paddingValues,navController)
        }
    }
}

@Composable
fun midSectionHome(paddingValues: PaddingValues,navController: NavController) {
    var pagerState = rememberPagerState {
        2
    }

    val coroutine = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primary
            )
            .padding(top = paddingValues.calculateTopPadding(),
                start = 10.dp,
                end = 10.dp)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = {
                if (pagerState.currentPage < 2) {
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(it[pagerState.currentPage]),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.primary
        ) {

            CustomTab(
                tabno = 0,
                text = Tabs.PRODUCTS.value,
                currentPage = pagerState.currentPage
            ) {
                coroutine.launch {
                    pagerState.animateScrollToPage(0)
                }
            }

            CustomTab(
                tabno = 1,
                text = Tabs.GROCERIES.value,
                currentPage = pagerState.currentPage
            ) {

                coroutine.launch {
                    pagerState.animateScrollToPage(1)
                }
            }

        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { index ->
            when (index) {
                0 -> {
                    DrawProducts(navigationController = navController)
                }

                1 -> {
                    DrawGroceries(navigationController = navController)
                }

            }
        }
    }

}