package com.example.carlo.projetofinalandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DadosCadastrais extends AppCompatActivity {

    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cadastrais);

        btVoltar = (Button) findViewById(R.id.btVoltar);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarParaPrimeiraTela();
            }
        });
    }

    void voltarParaPrimeiraTela(){
        Intent intent = new Intent();
        intent.setClass(DadosCadastrais.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
