package com.codecaine.mpurse.server;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codecaine.mpurse.connection.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Deepankar on 14-01-2016.
 */
public class NetworkManager {

    //TODO Implement Network manager using RequestFuture Volley API

    private static final String TAG = NetworkManager.class.getSimpleName();
    private static final int MAX_TIMEOUT_MS = 120000;
    private JSONObject jsonObject = null;

    public JSONObject sendNetworkRequest(Context context, final int method, final String URL, final Map<String, String> params) {

        StringRequest stringRequest = new StringRequest(method, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Network Response: " + response);
                try {
                    convertToJSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(MAX_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        return jsonObject;
    }

    private void convertToJSONObject(String response) throws JSONException {
        jsonObject = new JSONObject(response);
    }
}

