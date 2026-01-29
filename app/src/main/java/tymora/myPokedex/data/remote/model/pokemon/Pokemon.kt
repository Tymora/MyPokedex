package tymora.myPokedex.data.remote.model.pokemon

data class Pokemon(
    val abilities: List<Ability>,
    // val cries: Cries,
    // val forms: List<Form>,
    val height: Int,
    val id: Int,
    val name: String,
    // val species: Species,
    //val sprites: Sprites,
    // val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)