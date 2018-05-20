package nyc.c4q.finalpracticalretake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static nyc.c4q.finalpracticalretake.util.Constants.DEFAULT_USER;
import static nyc.c4q.finalpracticalretake.util.Constants.SHARED_PREFS;
import static nyc.c4q.finalpracticalretake.util.Constants.USER_KEY;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private String username;
    private Button submitBtn;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkUser();
        setViews();
        setButtonOnClick();
    }

    private void checkUser() {
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        username = preferences.getString(USER_KEY, DEFAULT_USER);
        if (!username.equalsIgnoreCase(DEFAULT_USER)) {
            startActivity(new Intent(LoginActivity.this, BreedsActivity.class));
            finish();
        }
    }

    private void setViews(){
        usernameET = findViewById(R.id.username_edittext);
        passwordET = findViewById(R.id.password_edittext);
        submitBtn = findViewById(R.id.submit_button);
    }

    private void setButtonOnClick(){

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameET.getText().toString().equals("") || passwordET.getText().toString().equals("")) {
                    if (usernameET.getText().toString().equals("")) {
                        usernameET.setHint(R.string.enter_username);
                    } else if (passwordET.getText().toString().equals("")) {
                        passwordET.setHint(R.string.enter_password);
                    }
                } else if (usernameET.getText() != null && passwordET.getText() != null) {
                    username = usernameET.getText().toString();
                    if (passwordET.getText().toString().toLowerCase().contains(username.toLowerCase())) {
                        passwordET.setText("");
                        passwordET.setHint(R.string.contains_username);
                    } else {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(USER_KEY, username).apply();
                        startActivity(new Intent(LoginActivity.this, BreedsActivity.class));
                    }
                }
            }
        });
    }


}
