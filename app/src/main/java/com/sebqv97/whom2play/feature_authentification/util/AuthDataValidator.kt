package com.sebqv97.whom2play.feature_authentification.util
class AuthDataValidator {
    companion object {
      internal fun isEmailValid(email: String?): Boolean {
          if(email.isNullOrEmpty())
              return false
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        internal fun isValidPassword(password: String?): Boolean {
            if(password.isNullOrEmpty()) return false
            if (password.length < 8) return false
            if (password.firstOrNull { it.isDigit() } == null) return false
            if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
            if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
         //   if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false
            return true
        }
    }
}