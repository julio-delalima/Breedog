package mx.julio.breedog.framework

import android.app.Activity
import android.content.Context
import mx.julio.breedog.domain.model.User

/**
 * Utils class.
 */
object Utils {

    private const val PREFERENCES = "auth_preferences"
    private const val USER_ID = "id"
    private const val USER_EMAIL = "email"
    private const val USER_TOKEN = "token"

    /**
     * Creates a new session.
     * @param activity used activity.
     * @param user new session value.
     */
    fun setLoggedInUser(activity: Activity, user: User) {
        activity.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE).also {
            it.edit()
                .putLong(USER_ID, user.id)
                .putString(USER_EMAIL, user.email)
                .putString(USER_TOKEN, user.authenticationToken)
                .apply()
        }
    }

    /**
     * Returns, if exists, user session.
     * @param activity used activity.
     * @return logged user.
     */
    fun getLoggedInUser(activity: Activity): User? {
        val prefs = activity.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE) ?: return null
        if (prefs.getLong(USER_ID, 0L) == 0L) return null

        return User(
            prefs.getLong(USER_ID, 0L),
            prefs.getString(USER_EMAIL, "").orEmpty(),
            prefs.getString(USER_TOKEN, "").orEmpty()
        )
    }

    /**
     * Removes user session.
     * @param activity used activity.
     */
    fun logout(activity: Activity) {
        activity.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE).also {
            it.edit()
                .clear()
                .apply()
        }
    }
}