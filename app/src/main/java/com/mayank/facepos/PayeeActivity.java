package com.mayank.facepos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PayeeActivity extends AppCompatActivity {

    final String payaddress = "9650350436@ybl";
    final  String payname = "Saksham";
    final String transactionNode = "testing";
    String ammount="1" ;
    String currencyunit = "INR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payee);
    }

    public void click(View view) {
        Uri uri = Uri.parse("upi://pay?pa="+payaddress
                +"&pn="+ payname
                +"&tn="+transactionNode
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