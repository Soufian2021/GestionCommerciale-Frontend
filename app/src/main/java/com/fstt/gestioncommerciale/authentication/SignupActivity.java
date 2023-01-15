package com.fstt.gestioncommerciale.authentication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fstt.gestioncommerciale.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private Button mSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mSignup = (Button) findViewById(R.id.signup);

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                System.out.println("Données " + username + " " + password);
                // Envoi des données d'inscription au serveur
                sendSignupDataToServer(username, password);
            }
        });

    }



    private void sendSignupDataToServer(String username, String password) {
        SignupService signupService = NetworkService.getInstance().getSignupService();
        SignupRequest signupRequest = new SignupRequest(username, password);
        Call<SignupResponse> call = signupService.signup(signupRequest);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    // traitement de la réponse du serveur
                    SignupResponse signupResponse = response.body();
                    // informer l'utilisateur de l'état de l'inscription
                } else {
                    // gérer les erreurs de la réponse
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                // gérer les erreurs de la requête
            }
        });
    }


}

