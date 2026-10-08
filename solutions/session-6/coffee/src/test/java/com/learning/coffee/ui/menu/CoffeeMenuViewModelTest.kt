package com.learning.coffee.ui.menu

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.learning.apilayer.repository.coffee.GetCoffeeMenuRepository
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRepository
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeRequest
import com.learning.apilayer.repository.coffee.GetRecommendedCoffeeResponse
import com.learning.apilayer.repository.dessert.GetRecommendedDessertRepository
import com.learning.apilayer.usecase.coffee.GetCoffeeMenuUseCase
import com.learning.apilayer.usecase.coffee.GetRecommendedCoffeeUseCase
import com.learning.apilayer.usecase.dessert.GetRecommendedDessertUseCase
import com.learning.networks.model.Failure
import com.learning.networks.model.Response
import com.learning.networks.model.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CoffeeMenuViewModelTest {

    private val getRecommendedCoffeeRepository = mock<GetRecommendedCoffeeRepository>()
    private val getCoffeeMenuRepository = mock<GetCoffeeMenuRepository>()
    private val getRecommendedDessertRepository = mock<GetRecommendedDessertRepository>()

    private val viewModel by lazy {
        CoffeeMenuViewModel(
            getRecommendedCoffeeUseCase = GetRecommendedCoffeeUseCase(getRecommendedCoffeeRepository),
            getCoffeeMenuUseCase = GetCoffeeMenuUseCase(getCoffeeMenuRepository),
            getRecommendedDessertUseCase = GetRecommendedDessertUseCase(getRecommendedDessertRepository)
        )
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get recommended coffee success`() {
        val request = GetRecommendedCoffeeRequest()
        val response = GetRecommendedCoffeeResponse(name = "ลาเต้", price = 65, description = null)
        whenever(getRecommendedCoffeeRepository.getRecommendedCoffee(request))
            .thenReturn(Result.Success(response))

        val states = viewModel.recommendedCoffeeResult.collectStates(count = 2) {
            viewModel.getRecommendedCoffee(request)
        }

        assertTrue(states[0] is Response.Loading)
        assertEquals("ลาเต้", (states[1] as Response.Success).value.name)
    }

    @Test
    fun `get recommended coffee fail`() {
        val request = GetRecommendedCoffeeRequest()
        whenever(getRecommendedCoffeeRepository.getRecommendedCoffee(request))
            .thenReturn(Result.Error(Failure.NetworkConnection()))

        val states = viewModel.recommendedCoffeeResult.collectStates(count = 2) {
            viewModel.getRecommendedCoffee(request)
        }

        assertTrue(states[0] is Response.Loading)
        assertTrue((states[1] as Response.Error).failure is Failure.NetworkConnection)
    }

    /** เก็บค่าที่ LiveData ปล่อยออกมาจนครบ [count] ค่า (UseCase ทำงานข้าม thread จึงต้องรอ) */
    private fun <T> LiveData<T>.collectStates(count: Int, trigger: () -> Unit): List<T> {
        val states = mutableListOf<T>()
        val latch = CountDownLatch(count)
        observeForever {
            states.add(it)
            latch.countDown()
        }
        trigger()
        assertTrue(latch.await(2, TimeUnit.SECONDS), "LiveData ปล่อยค่าไม่ครบ $count ค่า")
        return states
    }
}
