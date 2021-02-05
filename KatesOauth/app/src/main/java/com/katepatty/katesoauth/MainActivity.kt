package com.katepatty.katesoauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// google sign in service requires  Google's SHA1 certificate in apps keystore
// not firebase, firebase requires google json file
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

// task
import com.google.android.gms.tasks.Task

// no more findViewById
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var gClient: GoogleSignInClient

    // 登入後切換畫面時要輸入的參數編號

    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //config
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("YOUR_WEB_APPLICATION_CLIENT_ID") // generate by step 12 see in github
            .requestEmail()
            .build()

        //create instance using config
        gClient = GoogleSignIn.getClient(this, gso)

        btn.setOnClickListener {
            signIn()
        }

    }

    // 同類別中，與生夢週期同階層。
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }


    private fun signIn() {
        val signInIntent = gClient.signInIntent
        startActivityForResult(
            signInIntent, RC_SIGN_IN
        )
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){

        try{

            // 取得使用者帳號的資料實例
            val account = completedTask.getResult(
                ApiException::class.java
            )

            // Signed in successfully
            // 取出實例的欄位值並且核對
            val googleId = account?.id ?: ""
            Log.i("Google ID",googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)


        }catch(e: ApiException){

            Log.e(
                "failed code=", e.statusCode.toString()
            )

        }


    }


    // catch the behavior
    private fun signOut() {
        gClient.signOut()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }


    //  (This can be used when you have the ‘Delete my account’ option in the user’s profile):
    private fun revokeAccess() {
        gClient.revokeAccess()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }


}