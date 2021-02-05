# Android_Review_16
Login &amp; Oauth 


remember to use Web_app Clien Id , not Anroid_app_clien_id.

1.exchange key

  see file called KatesOauh.

2.code


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


3. 

![](https://raw.githubusercontent.com/QueenieCplusplus/Android_Review_16/main/output.png)

ref: https://johncodeos.com/how-to-add-google-login-button-to-your-android-app-using-kotlin/
