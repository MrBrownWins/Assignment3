package me.karimoff.myapplication_lecture6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    Button signUpButton;
    EditText usernameEditText;
    EditText passwordEditText;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public static final String USER = "me.karimoff.myapplication_lecture6.USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.LoginButton);
        signUpButton = (Button) findViewById(R.id.SignUpButton);
        usernameEditText = (EditText) findViewById(R.id.UserEditText);
        passwordEditText = (EditText) findViewById(R.id.PasswordEditText);

        settings = getSharedPreferences("sharedPreferancesFile", MODE_PRIVATE);
        editor = settings.edit();


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter username and password", Toast.LENGTH_SHORT).show();

                } else {
                    editor.putString(username, password);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, " User is saved", Toast.LENGTH_SHORT).show();

                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                String oldPassword = settings.getString(username, "");

                if (oldPassword.equals("")){
                    Toast.makeText(LoginActivity.this, "No such user", Toast.LENGTH_SHORT).show();
                } else if (oldPassword.equals(password)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(USER, username);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Username or Passwor is wrong", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
