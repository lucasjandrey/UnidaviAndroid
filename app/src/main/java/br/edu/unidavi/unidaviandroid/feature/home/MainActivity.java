package br.edu.unidavi.unidaviandroid.feature.home;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.edu.unidavi.unidaviandroid.R;
import br.edu.unidavi.unidaviandroid.data.Session;
import br.edu.unidavi.unidaviandroid.feature.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private int login;
    private int button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Session session = new Session();
        String username = session.getEmailInSession(getApplicationContext());
        String imagem = session.getImagemInSession(getApplicationContext());

        TextView textViewUsername = findViewById(R.id.label_lucas);
        textViewUsername.setText(username);

        //String url = "https://vignette.wikia.nocookie.net/meme/images/9/93/Serj%C3%A3o_Berranteiro.png/revision/latest/scale-to-width-down/310?cb=20170223013415&path-prefix=pt-br";
        ImageView myImageView = findViewById(R.id.bolsobirama);
        Picasso.with(this).load(imagem).into(myImageView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLE","START");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFECYCLE","PAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE","RESUME");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFECYCLE","STOP");
    }
}
