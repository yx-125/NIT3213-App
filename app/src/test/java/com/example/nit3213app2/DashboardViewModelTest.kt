package com.example.nit3213app2

import com.example.nit3213app2.data.dashboard.DashboardEntity
import com.example.nit3213app2.data.dashboard.DashboardRepository
import com.example.nit3213app2.ui.dashboard.DashboardViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    private lateinit var repo: DashboardRepository
    private lateinit var viewModel: DashboardViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repo = mockk()
        viewModel = DashboardViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `dashboard loads successfully`() = runTest(dispatcher) {
        val fakeList = listOf(
            DashboardEntity("Mona Lisa", "Da Vinci", "Oil", 1503, "Masterpiece")
        )
        coEvery { repo.fetchDashboard("art") } returns fakeList

        viewModel.loadDashboard("art")
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.items.value.size)
        assertEquals("Mona Lisa", viewModel.items.value[0].artworkTitle)
        assertNull(viewModel.error.value)
    }

    @Test
    fun `dashboard load fails`() = runTest(dispatcher) {
        coEvery { repo.fetchDashboard("badKey") } throws Exception("Unauthorized")

        viewModel.loadDashboard("badKey")
        dispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.items.value.isEmpty())
        assertEquals("Failed to load dashboard: Unauthorized", viewModel.error.value)
    }
}