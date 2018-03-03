package br.edu.unidavi.unidaviandroid.feature.login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.edu.unidavi.unidaviandroid.R;
import br.edu.unidavi.unidaviandroid.Services.WebTaskLogin;
import br.edu.unidavi.unidaviandroid.data.Session;
import br.edu.unidavi.unidaviandroid.feature.home.MainActivity;
import br.edu.unidavi.unidaviandroid.model.User;

public class LoginActivity extends AppCompatActivity {

    private Session session = new Session();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonEnter = findViewById(R.id.entrar);
        buttonEnter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tryLogin();
                    }
                }
        );

        EditText editTextEmail = findViewById(R.id.usuario);
        editTextEmail.setText(session.getEmailInSession(getApplicationContext()));

        EditText editTextPassword = findViewById(R.id.senha);
        editTextPassword.requestFocus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void tryLogin() {
        EditText inputEmail = findViewById(R.id.usuario);
        String emailValue = inputEmail.getText().toString();
        ShowDialog();
        EditText inputPassword = findViewById(R.id.senha);
        String passwordValue = inputPassword.
                getText().toString();
        WebTaskLogin webTaskLogin = new WebTaskLogin(this, emailValue, passwordValue);
        webTaskLogin.execute();
    }


    private void goToHome(){
        Intent intent = new Intent(this,
                MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
//    public void login(View v){
//        EditText inputUsuario = findViewById(R.id.usuario);
//        String emailValue = inputUsuario.getText().toString();
//
//        EditText inputSenha = findViewById(R.id.senha);
//        String senhaValue = inputSenha.getText().toString();
//
//        if ("lucas".equals(emailValue) && "123".equals(senhaValue)){
//            Log.d("DEBUG",inputSenha+"Login efetuado com sucesso!"+inputUsuario);
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//        }else {
//            Log.d("DEBUG",inputSenha+"Login efetuado com sucesso!"+inputUsuario);
//            Log.d("DEBUG", "Usuario ou Senha incorretos");
//        }
//    }

    @Subscribe
    public void onEvent(User user){
        session.saveEmailInSession(getApplicationContext(),user.getEmail());
        session.saveImagemInSession(getApplicationContext(),user.getProfile_url());
        hideDialog();
        goToHome();
    }

    @Subscribe
    public void onEvent(Error error){
        hideDialog();
        Snackbar.make(findViewById(R.id.container),error.getMessage(), Snackbar.LENGTH_SHORT);
        Log.d("TESTE","");
    }
    public void ShowDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(
                R.string.message_wait));
        progressDialog.setProgressStyle(
                progressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    public void hideDialog(){
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }
}
