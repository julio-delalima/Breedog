package mx.julio.breedog.framework.data.remote.response

import mx.julio.breedog.framework.data.remote.dto.UserDTO

/**
 * Represents the remote data for user.
 * @property user the user.
 * @constructor Creates an instance.
 */
data class UserData(val user: UserDTO)