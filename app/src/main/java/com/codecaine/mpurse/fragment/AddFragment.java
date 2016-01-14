package com.codecaine.mpurse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codecaine.mpurse.R;
import com.codecaine.mpurse.entity.DBConstants;
import com.codecaine.mpurse.helper.SQLiteHandler;

import java.util.HashMap;


public class AddFragment extends Fragment {

    private Button bAdd;
    private EditText etAmountAdd;
    private SQLiteHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        bAdd = (Button) view.findViewById(R.id.bAdd);
        etAmountAdd = (EditText) view.findViewById(R.id.etAmountAdd);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new SQLiteHandler(getContext());
                HashMap<String, String> user = db.getUserDetails();
                double currAmt = Double.parseDouble(user.get(DBConstants.KEY_WALLET));
                double addAmount = 0.0;
                if (!etAmountAdd.getText().toString().equals(""))
                    addAmount = Double.parseDouble(etAmountAdd.getText().toString());
                db.addAmountToWallet(currAmt + addAmount);
                db.close();
                etAmountAdd.setText("");
                Toast.makeText(getContext(), addAmount + " successfullly added!!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
