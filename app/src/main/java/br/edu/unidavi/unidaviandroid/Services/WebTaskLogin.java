package br.edu.unidavi.unidaviandroid.Services;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.edu.unidavi.unidaviandroid.model.User;

/**
 * Created by suporte on 03/03/2018.
 */

public class WebTaskLogin extends WebTaskBase {
    private static String SERVICE_URL = "login";
    private String email;
    private String password;

    public WebTaskLogin(Context context, String email, String password) {
        super(context, SERVICE_URL);
        this.email = email;
        this.password = password;
    }

    @Override
    public void handleResponse(String response) {
        User user = new User();
        JSONObject responseAsJSON = null;
        try {
            responseAsJSON = new JSONObject(response);
            String name = responseAsJSON.getString("name");
            user.setName(name);
            String token = responseAsJSON.getString("token");
            user.setToken(token);
            String profile_url = responseAsJSON.getString("profile_url");
            user.setProfile_url(profile_url);

            user.setEmail(email);

            EventBus.getDefault().post(user);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Error("Erro ao ler a resposta."));
        }
    }

    @Override
    public String getRequestBody(){
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("email", email);
        requestMap.put("password", password);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();
        return jsonString;
    }
}
