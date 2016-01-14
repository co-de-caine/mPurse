package com.codecaine.mpurse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.codecaine.mpurse.R;
import com.codecaine.mpurse.config.AppConfig;
import com.codecaine.mpurse.server.TransactionManager;

import java.util.HashMap;
import java.util.Map;


public class RequestFragment extends Fragment {

    private EditText etPhoneno;
    private EditText etAmount;
    private Button bRequest;
    private TransactionManager transactionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        etPhoneno = (EditText) view.findViewById(R.id.etPhoneRequest);
        etAmount = (EditText) view.findViewById(R.id.etAmountRequest);
        bRequest = (Button) view.findViewById(R.id.bRequest);
        bRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = etPhoneno.getText().toString();
                String amount = etPhoneno.getText().toString();
                sendRequest(phoneno, amount);
            }
        });
        return view;
    }

    private void sendRequest(String phoneno, String amount) {
        transactionManager = new TransactionManager(getContext());
        Map<String, String> params = new HashMap<>();
        params.put("recvrphone", phoneno);
        params.put("amount", amount);
        transactionManager.sendRequest(Request.Method.POST , params, AppConfig.LOCAL_URL_REQUESTMONEY);
    }


}
