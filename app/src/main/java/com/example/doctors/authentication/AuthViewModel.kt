package com.example.doctors.authentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel: ViewModel() {
    private val firebaseAuth : FirebaseAuth= FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState : StateFlow<AuthState> = _authState

    fun signUp(email : String,password : String){
        _authState.value=AuthState.Loading
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if(task.isSuccessful){
                    _authState.value=AuthState.Authenticated
                }else{
                    _authState.value=AuthState.ErrorMassage(task.exception?.message ?: "Authentication failed")
                }
            }
    }
    fun signIn(email : String,password : String) {
        _authState.value = AuthState.Loading
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.ErrorMassage(
                        task.exception?.message ?: "Authentication failed"
                    )
                }
            }
    }
    fun sendResetPassword(email : String){
        _authState.value = AuthState.Loading
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener {task->
                if (task.isSuccessful){
                    _authState.value= AuthState.PasswordRestSent
                }else{
                    _authState.value= AuthState.ErrorMassage(
                        task.exception?.message ?: "Failed to send reset email"
                    )
                    }
            }

    }
}

sealed interface AuthState{
    object Idle: AuthState
    object Authenticated: AuthState
    object Loading: AuthState
    object PasswordRestSent : AuthState
    data class ErrorMassage(val massage: String): AuthState
}