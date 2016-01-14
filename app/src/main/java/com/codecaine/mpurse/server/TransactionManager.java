package com.codecaine.mpurse.server;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codecaine.mpurse.entity.DBConstants;
import com.codecaine.mpurse.helper.SQLiteHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Deepankar on 14-01-2016.
 */
public class TransactionManager {

    private static final String TAG = TransactionManager.class.getSimpleName();
    private SQLiteHandler db;
    private Context context;


    public TransactionManager(Context context) {
        this.context = context;
    }

    public void sendRequest(final int post, final Map<String, String> params, final String URL) {
        final HashMap<String, String> user = db.getUserDetails();
        StringRequest stringRequest = new StringRequest(post, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("error").equals("0")) {
                        String refid = jsonObject.getString("refid");
                        db.addRequest(refid, params.get("recvrphone"), params.get("amount"), 0, "opened");
                        db.close();
                    } else if (jsonObject.getString("error").equals("1")) {
                    } else if (jsonObject.getString("error").equals("2")) {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, volleyError.toString());
                Toast.makeText(context, "Request not submitted", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                params.put("uuid",user.get(DBConstants.KEY_UUID));
                return params;
            }
        };
    }
}
