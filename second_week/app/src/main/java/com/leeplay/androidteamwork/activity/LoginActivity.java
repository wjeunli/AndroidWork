package com.leeplay.androidteamwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leeplay.androidteamwork.R;
import com.leeplay.androidteamwork.utils.Constants;
import com.leeplay.androidteamwork.utils.PreferenceUtils;


public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LoginActivity";

    private Button mLoginButton;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        mLoginButton = findViewById(R.id.loginButton);
        mUsernameEditText = findViewById(R.id.editTextUsername);
        mPasswordEditText = findViewById(R.id.editTextPassword);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mUsernameEditText.getText())) {
                    Toast.makeText( LoginActivity.this, "Error: empty username", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(mPasswordEditText.getText())) {
                    Toast.makeText( LoginActivity.this, "Error: empty password", Toast.LENGTH_SHORT).show();
                }
                String login = mUsernameEditText.getText().toString();
                PreferenceUtils.setLogin(login);
                startActivity(makeIntentForMainActivity(login));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    Intent makeIntentForMainActivity(String user) {
        Bundle extra = new Bundle();
        extra.putString(Constants.Login.EXTRA_LOGIN, user);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(extra);

        return intent;

    }
}
