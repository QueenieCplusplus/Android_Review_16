# Android_Review_16
Login &amp; Oauth 


remember to use Web_app Clien Id , not Anroid_app_clien_id.

1.code


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

![](https://raw.githubusercontent.com/QueenieCplusplus/Android_Review_16/main/gauth.png)
