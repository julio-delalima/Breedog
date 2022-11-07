package mx.julio.breedog.framework.data.remote.dto

import com.squareup.moshi.Json

/**
 * Model for represent a dog DTO.
 * @property id unique identifier.
 * @property index dog index.
 * @property name dog name.
 * @property type dog type.
 * @property image picture.
 * @property lifeExpectancy life expectancy.
 * @property temperament spanish dog's temperament.
 * @property heightFemale height when female.
 * @property heightMale height when male.
 * @property weightFemale weight when female.
 * @property weightMale weight when male.
 */
class DogDTO(
    val id: Long,
    val index: Long,
    @field:Json(name = "name_en") val name: String,
    @field:Json(name = "dog_type") val type: String,
    @field:Json(name = "image_url") val image: String,
    @field:Json(name = "life_expectancy") val lifeExpectancy: String,
    val temperament: String,
    @field:Json(name = "height_female") val heightFemale: String,
    @field:Json(name = "height_male") val heightMale: String,
    @field:Json(name = "weight_female") val weightFemale: String,
    @field:Json(name = "weight_male") val weightMale: String
)