package mx.julio.breedog.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Model for represent a domain dog.
 * @property id unique identifier.
 * @property name dog's name.
 * @property type dog's type.
 * @property image picture.
 * @property lifeExpectancy life expectancy.
 * @property temperament spanish dog's temperament.
 * @property heightFemale height when female.
 * @property heightMale height when male.
 * @property weightFemale weight when female.
 * @property weightMale weight when male.
 */
@Parcelize
data class Dog(
    val id: Long,
    val name: String,
    val type: String,
    val image: String,
    val lifeExpectancy: String,
    val temperament: String,
    val heightFemale: String,
    val heightMale: String,
    val weightFemale: String,
    val weightMale: String,
) : Parcelable