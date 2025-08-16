package tymora.myPokedex.data.remote.model.pokemon

data class Pokemon(
    val abilities: List<Ability>,
    val base_experience: Int,
    val cries: Cries,
    val forms: List<Form>,
    val height: Int,
    val id: Int,
    val is_default: Boolean,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)