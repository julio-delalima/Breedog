package mx.julio.breedog.framework.data.remote.model

import mx.julio.breedog.domain.model.Dog
import mx.julio.breedog.domain.model.User

/**
 * Map a remote dog into a domain dog.
 * @receiver the dog DTO.
 * @return a domain dog.
 */
fun DogDTO.toDomainDog(): Dog {
    return Dog(
        this.id,
        this.name,
        this.type,
        this.image,
        this.lifeExpectancy,
        this.temperament,
        this.heightFemale,
        this.heightMale,
        this.weightFemale,
        this.weightMale
    )
}

/**
 * Map a remote user into a domain user.
 * @receiver the user DTO.
 * @return a domain user.
 */
fun UserDTO.toDomainUser() : User {
    return User(
        this.id,
        this.email,
        this.authenticationToken
    )
}