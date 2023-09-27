package com.leeplay.lessonhomework.activity;

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

import com.leeplay.lessonhomework.R;
import com.leeplay.lessonhomework.utils.Constants;

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

                startActivity(makeIntentForQuizActivity(mUsernameEditText.getText().toString()));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    Intent makeIntentForQuizActivity(String info) {
        Bundle extra = new Bundle();
        extra.putString(Constants.LOGIN_EXTRA_KEYS, info);

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtras(extra);

        return intent;

    }
}
