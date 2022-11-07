package mx.julio.breedog.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Model for represent a domain dog.
 * @property id unique identifier.
 * @property index dog's index.
 * @property name dog's name.
 * @property type dog's type.
 * @property image picture.
 * @property lifeExpectancy life expectancy.
 * @property temperament spanish dog's temperament.
 * @property heightFemale height when female.
 * @property heightMale height when male.
 * @property weightFemale weight when female.
 * @property weightMale weight when male.
 * @property inCollection if the user has the dog in the collection.
 */
@Parcelize
data class Dog(
    val id: Long,
    val index: Long,
    val name: String,
    val type: String,
    val image: String,
    val lifeExpectancy: String,
    val temperament: String,
    val heightFemale: String,
    val heightMale: String,
    val weightFemale: String,
    val weightMale: String,
    val inCollection: Boolean = true
) : Parcelable, Comparable<Dog> {

    companion object {

        /**
         * Generates a fake dog with the index.
         * @param index dog's index.
         * @return the dog.
         */
        fun fakeWithIndex(index: Long) = Dog(
            0,
            index,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            false
        )
    }

    override fun compareTo(other: Dog): Int {
        return if(index > other.index) 1
        else -1
    }
}