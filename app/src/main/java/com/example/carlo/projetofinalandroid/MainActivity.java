package com.example.carlo.projetofinalandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper dh;
    EditText etNome, etCPF, etIdade, etTelefone, etEmail;
    Button btInserir, btListar, btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dh = new DBHelper(this);
        etNome = (EditText) findViewById(R.id.etNome);
        etCPF = (EditText) findViewById(R.id.etCPF);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btInserir = (Button) findViewById(R.id.btInserir);
        btListar = (Button) findViewById(R.id.btListar);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNome.getText().length()>0 && etCPF.getText().length()>0 && etIdade.getText().length()>0 && etTelefone.getText().length()>0 && etEmail.getText().length()>0){
                    dh.insert(etNome.getText().toString(),etCPF.getText().toString(), etIdade.getText().toString(),etTelefone.getText().toString(),etEmail.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Cadastro Realizado :)");
                    adb.show();

                    etNome.setText("");
                    etCPF.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
                else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Algo deu errado...");
                    adb.setMessage("Todos os campos devem ser preenchidos ;)");
                    adb.show();

                    etNome.setText("");
                    etCPF.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
            }
        });

        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaCadastrar();
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Contato> contatos = dh.queryGetAll();
                if (contatos == null){
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Alerta!");
                    adb.setMessage("Não há registros cadastrados :(");
                    adb.show();
                    return;
                }
                for (int i=0; i<contatos.size(); i++){
                    Contato contato = (Contato) contatos.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Registro " + i);
                    adb.setMessage("Nome: "+ contato.getNome()+"\nCPF: "+ contato.getCpf()+"\nIdade: "+ contato.getIdade()+"\nTelefone: "+ contato.getTelefone()+"\nEmail: "+ contato.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    adb.show();
                }

            }
        });
    }

    void chamaCadastrar(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, DadosCadastrais.class);
        startActivity(intent);
        finish();

    }

}
