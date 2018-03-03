package br.edu.unidavi.unidaviandroid.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

/**
 * Created by suporte on 02/03/2018.
 */

public class Session {
    public final String FIELD_USERNAME = "username";
    public final String PREF_NAME = "session";
    public final String IMAGEM = "imagem";

    public void saveEmailInSession(Context context, String emailValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_USERNAME, emailValue);
        editor.commit();
    }

    public String getEmailInSession(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(FIELD_USERNAME,"");
    }

    public void saveImagemInSession(Context context, String imagemValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(IMAGEM, imagemValue);
        editor.commit();
    }
    public String getImagemInSession(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(IMAGEM,"https://i2.wp.com/omnitos.com/wp-content/uploads/2017/08/21268588_1549435878449453_1622013216_n-e1504194379215.jpg?resize=458%2C782&ssl=1");
    }
}