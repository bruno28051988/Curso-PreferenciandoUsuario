package com.br.cursopreferenciadousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btnSalvar;
    private TextInputEditText campoNome;
    private TextView txtResultado;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = findViewById(R.id.button_salvar);
        campoNome = findViewById(R.id.edit_nome);
        txtResultado = findViewById(R.id.text_resultado);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                //Validar o nome
                if (campoNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Preencha o nome",
                            Toast.LENGTH_SHORT).show();
                }else{

                    String nome = campoNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    txtResultado.setText("Olá, "+ nome);
                }
            }
        });

        //Recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //Valida se temos o nome em preferencias
        if (preferences.contains("nome")){

            String nome = preferences.getString("nome", "Usuário não definifo");
            txtResultado.setText("Olá, "+ nome);
        }else{

            txtResultado.setText("Olá, usuário não definido");
        }
    }
}