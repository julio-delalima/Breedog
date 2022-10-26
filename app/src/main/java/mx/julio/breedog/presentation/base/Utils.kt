package mx.julio.breedog.presentation.base

import android.util.Patterns

/**
 * General utils.
 */
object Utils {

    /**
     * Checks if the given email is valid.
     * @param email email to valid.
     * @return indicates if the email is valid or not.
     */
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}