package com.example.nit3213app2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nit3213app2.data.auth.AuthRepository
import com.example.nit3213app2.data.auth.AuthResponse
import com.example.nit3213app2.ui.auth.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repo: AuthRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repo = mockk(relaxed = true) // relaxed so we don’t need to stub everything
        viewModel = LoginViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `student found from api`() = runTest {
        // Arrange
        coEvery { repo.login("footscray", "Sandra", "8079691") } returns AuthResponse("art")

        // Act
        viewModel.campus = "footscray"
        viewModel.login("Sandra", "8079691")
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        Assert.assertEquals("art", viewModel.keypass.value)
        Assert.assertNull(viewModel.error.value)
        Assert.assertFalse(viewModel.loading.value)
    }

    @Test
    fun `student not found from api`() = runTest {
        // Arrange
        coEvery { repo.login("footscray", "Sandra", "8069291") } throws
                retrofit2.HttpException(retrofit2.Response.error<Any>(401, okhttp3.ResponseBody.create(null, "")))

        // Act
        viewModel.campus = "footscray"
        viewModel.login("Sandra", "8069291")
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        Assert.assertTrue(viewModel.error.value?.contains("Login failed") == true)
        Assert.assertNull(viewModel.keypass.value)
        Assert.assertFalse(viewModel.loading.value)
    }
}
