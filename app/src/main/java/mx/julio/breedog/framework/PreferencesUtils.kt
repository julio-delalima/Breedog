package mx.julio.breedog.framework

import android.content.SharedPreferences
import mx.julio.breedog.domain.model.User
import javax.inject.Inject

/**
 * Utils class.
 */
class PreferencesUtils @Inject constructor(private val preferences: SharedPreferences) {

    companion object {
        private const val USER_ID = "id"
        private const val USER_EMAIL = "email"
        private const val USER_TOKEN = "token"
    }


    /**
     * Creates a new session.
     * @param user new session value.
     */
    fun setLoggedInUser(user: User) {
        preferences.also {
            it.edit()
                .putLong(USER_ID, user.id)
                .putString(USER_EMAIL, user.email)
                .putString(USER_TOKEN, user.authenticationToken)
                .apply()
        }
    }

    /**
     * Returns, if exists, user session.
     * @return logged user.
     */
    fun getLoggedInUser(): User? {
        if (preferences.getLong(USER_ID, 0L) == 0L) return null

        return User(
            preferences.getLong(USER_ID, 0L),
            preferences.getString(USER_EMAIL, "").orEmpty(),
            preferences.getString(USER_TOKEN, "").orEmpty()
        )
    }

    /**
     * Returns, if exists, the user authentication token.
     * @return the token.
     */
    fun getToken(): String? {
        if (preferences.getLong(USER_ID, 0L) == 0L) return null

        return preferences.getString(USER_TOKEN, "")
    }

    /**
     * Removes the user session.
     */
    fun logout() {
        preferences.also {
            it.edit()
                .clear()
                .apply()
        }
    }
}