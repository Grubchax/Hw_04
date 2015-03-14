package com.grubchax.hw_04;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    int nextLogin = 3;
    String[] name = new String[10];
    String[] password = new String[10];
    String[] email = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name[0] = "Ukrop";
        password[0] = "";
        email[0] = "ukrop@ukr.net";

        name[1] = "Android";
        password[1] = "KitKat";
        email[1] = "developer@android.com";

        name[2] = "vvp";
        password[2] = "vata";
        email[2] = "putin@huylo.ru";

        Button signupButton = (Button) findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivityForResult(intent, 1);
            }
        });

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean nameFound = false;
                int index = 0;
                EditText loginEdit;
                EditText passwordEdit;

                loginEdit = (EditText) findViewById(R.id.login_edit);
                for (int i = 0; i < nextLogin; i++){
                    if (name[i].compareTo(loginEdit.getText().toString()) == 0 ){
                        Log.d("Grubchax", "equal" + i);
                        nameFound = true;
                        index = i;
                        break;
                    }
                }
                if (nameFound){
                    passwordEdit = (EditText) findViewById(R.id.password_edit);
                    if (password[index].compareTo(passwordEdit.getText().toString()) == 0){
                        Intent intent = new Intent(getApplicationContext(), Info.class);
                        intent.putExtra("name", name[index]);
                        intent.putExtra("email", email[index]);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent == null){
            return;
        }
        if (intent.hasExtra("name")){
            name[nextLogin] = intent.getStringExtra("name");
        }
        if (intent.hasExtra("email")){
            email[nextLogin] = intent.getStringExtra("email");
        }
        if (intent.hasExtra("password")){
            password[nextLogin] = intent.getStringExtra("password");
        }
        nextLogin++;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
