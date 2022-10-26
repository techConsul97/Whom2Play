package com.sebqv97.whom2play.feature_authentification.util
class AuthDataValidator {
    companion object {
        @JvmStatic
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

         internal fun isEmailValid(email: String?): Boolean {
             if(email.isNullOrEmpty())
                 return false
            return EMAIL_REGEX.toRegex().matches(email);
        }

        internal fun isValidPassword(password: String?): Boolean {
            if(password.isNullOrEmpty()) return false
            if (password.length < 8) return false
            if (password.firstOrNull { it.isDigit() } == null) return false
            if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
            if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
            if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false
            return true
        }
    }
}