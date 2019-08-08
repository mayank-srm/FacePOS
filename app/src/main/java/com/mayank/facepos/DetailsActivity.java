package com.mayank.facepos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    EditText address,name,note,ammount;
    String payaddress ;
    String payname ;
    String transactionNote ;
    String payammount;
    String currencyunit = "INR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        address = findViewById(R.id.payeaddress);
        name = findViewById(R.id.payename);
        note  = findViewById(R.id.transaction_note);
        ammount = findViewById(R.id.payammount);
    }

    public void pay(View view) {
        payaddress = address.getText().toString();
        payname = name.getText().toString();
        transactionNote = note.getText().toString();
        payammount = ammount.getText().toString();

        Uri uri = Uri.parse("upi://pay?pa="+payaddress+
                "&pn="+ payname
                +"&tn="+transactionNote
                +"&am"+ammount
                +"&cu"+currencyunit);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode , int resultCode, Intent data){
        if(data != null){
            String res = data.getStringExtra("response");
            String search = "SUCCESS";
            if (res != null) {
                if(res.toLowerCase().contains(search.toLowerCase())){
                    Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Payment Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}