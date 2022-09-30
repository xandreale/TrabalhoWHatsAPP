package com.example.trabalhowhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText numero, mensagem;
    Button sendbtn;
    String mensagemstr, numerostr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = findViewById(R.id.editTextNumber);
        mensagem = findViewById(R.id.editTextTextMessage);
        sendbtn = findViewById(R.id.sendbtn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mensagemstr = mensagem.getText().toString();
                numerostr = numero.getText().toString();

                if (!mensagemstr.isEmpty() && !numerostr.isEmpty()){

                    if (IsWhatsappInstalled()){
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=55"+ numerostr +
                                "&text="+ mensagemstr));
                        startActivity(i);
                        mensagem.setText("");
                        numero.setText("");
                    }else{
                        Toast.makeText( MainActivity.this,"Instale o Whatsapp primeiro!",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Insira um número e uma mensagem", Toast.LENGTH_LONG).show();
                }



            }
        });


    }

    private boolean IsWhatsappInstalled(){
        PackageManager packageManager = getPackageManager();
        boolean whatsappInstalled;


        try {
            packageManager.getPackageInfo("com.whatsapp",PackageManager.GET_ACTIVITIES);
            whatsappInstalled = true;
        }catch (PackageManager.NameNotFoundException e){

            whatsappInstalled = false;
        }
        return whatsappInstalled;

    }

}