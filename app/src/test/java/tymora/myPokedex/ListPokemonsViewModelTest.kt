package tymora.myPokedex

import androidx.paging.PagingData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tymora.myPokedex.data.remote.model.MiniDataPokemon
import tymora.myPokedex.domain.PokedexRepository
import tymora.myPokedex.ui.viewmodel.ListPokemonsViewModel

class ListPokemonsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val main = UnconfinedTestDispatcher()
    private lateinit var repo: PokedexRepository
    private lateinit var viewModel: ListPokemonsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(main)
        repo = mockk(relaxed = true)
        every { repo.getPokemonPaged() } returns flowOf(PagingData.empty())
        viewModel = ListPokemonsViewModel(repo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadMiniInfo emits error then success`() = runBlocking {
        val mini = MiniDataPokemon(
            id = 1,
            name = "squirtle",
            sprites = mockk(relaxed = true),
            types = emptyList()
        )
        var calls = 0
        coEvery { repo.getMiniInfo("squirtle") } coAnswers {
            if (calls++ == 0) throw IllegalStateException("boom")
            mini
        }

        viewModel.loadMiniInfo("squirtle")

        val (errName, err) = withTimeout(5_000) { viewModel.miniErrors.first() }
        assertEquals("squirtle", errName)
        assertEquals("boom", err.message)

        viewModel.loadMiniInfo("squirtle")

        val result = withTimeout(5_000) {
            viewModel.miniFlow("squirtle").filterNotNull().first()
        }
        assertEquals(mini, result)

        coVerify(exactly = 2) { repo.getMiniInfo("squirtle") }
    }
}
