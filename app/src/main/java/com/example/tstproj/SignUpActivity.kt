package com.example.tstproj

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    lateinit var uname:EditText
    lateinit var pass:EditText
    lateinit var confpass:EditText
    lateinit var signUpButton:Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        uname=findViewById(R.id.username)
        pass=findViewById(R.id.password)
        confpass=findViewById(R.id.confirm_password)
        signUpButton=findViewById(R.id.SignUpButton)
        auth= Firebase.auth


        signUpButton.setOnClickListener {
            validateAndSignUp()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }



    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"run:",Toast.LENGTH_SHORT).show()
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(SignUpActivity.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    Toast.makeText(this,"run:sing",Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(SignUpActivity.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                    Toast.makeText(this,"run:no",Toast.LENGTH_SHORT).show()
                }
            }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun validateAndSignUp(){
  //      validate()
        createAccount(uname.text.toString(),pass.text.toString())

    }

    companion object {
        private const val TAG = "EmailPassword"
    }

    private fun reload() {
        TODO("Not yet implemented")
    }
}