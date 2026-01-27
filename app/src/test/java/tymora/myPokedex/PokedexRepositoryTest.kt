package tymora.myPokedex

import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tymora.myPokedex.data.local.AppDatabase
import tymora.myPokedex.data.local.entity.MiniDataPokemonEntity
import tymora.myPokedex.data.remote.PokedexApi
import tymora.myPokedex.data.repository.PokedexRepositoryImpl
import tymora.myPokedex.domain.PokedexRepository


@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class PokedexRepositoryTest {

    private lateinit var api: PokedexApi
    private lateinit var db: AppDatabase
    private lateinit var repo: PokedexRepository

    @BeforeEach
    fun setUp() {
        api = mockk()
        db = mockk()
        repo = PokedexRepositoryImpl(api, db)
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
    }


    @Test
    fun `getMiniInfo returns cached entity and does not call API`() = runTest {
        // arrange
        val cached = MiniDataPokemonEntity(
            id = 25,
            name = "pikachu",
            sprites = mockk(relaxed = true),
            types = emptyList(),
        )


}}