package tymora.myPokedex.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tymora.myPokedex.data.local.AppDatabase
import tymora.myPokedex.data.remote.PokedexApi
import tymora.myPokedex.data.repository.PokedexRepositoryImpl
import tymora.myPokedex.domain.PokedexRepository
import tymora.myPokedex.ui.viewmodel.ListPokemonsViewModel


val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokedexApi::class.java)
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "pokedex_db"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    single<PokedexRepository> {
        PokedexRepositoryImpl(get(), get())
    }


    viewModel {
        ListPokemonsViewModel(get())
    }


}