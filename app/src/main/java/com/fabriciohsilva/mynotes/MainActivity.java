package com.fabriciohsilva.mynotes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private ViewHolder mViewHolder = new ViewHolder();
    private static final String NOME_ARQUIVO = "notes.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.txtNotes = (EditText) findViewById(R.id.edit_notes);
        this.mViewHolder.btn_Salvar = (ImageView) findViewById(R.id.btn_salvar);

        this.mViewHolder.btn_Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Notes = mViewHolder.txtNotes.getText().toString();
                gravarNoArquivo(Notes);
                Toast.makeText(MainActivity.this, "Anotação salva com sucesso!", Toast.LENGTH_SHORT);
            }//end public void onClick
        });//end this.mViewHolder.btn_Salvar.setOnClickListener

        //lendo o que está gravado
        if (lerDoArquivo() != null) {
            this.mViewHolder.txtNotes.setText(lerDoArquivo());

        }//end if ( lerDoArquivo() != null )

    }//end protected void onCreate

    private void gravarNoArquivo(String Notes) {

        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(Notes);
            outputStreamWriter.close();

        }//end try
        catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }//end catch ( IOException e)
    }//end private void gravarNoArquivo( String Notes )

    private String lerDoArquivo() {
        String resultado = "";
        String linha_arquivo = "";

        try {
            //abrir arquivo
            InputStream arquivo = openFileInput(NOME_ARQUIVO);

            if (arquivo != null) {

                //ler arquivo
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);

                //gerar buffer do conteúdo
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                while ((linha_arquivo = bufferedReader.readLine()) != null) {
                    resultado += linha_arquivo;
                }//end while (bufferedReader.readLine() != null)

            }//end if ( arquivo != null )
        }//end try
        catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }//end catch ( IOException e)

        return resultado;

    }//end private  String lerDoArquivo ()

    private static class ViewHolder {
        EditText txtNotes;
        ImageView btn_Salvar;
    }//end private static class mviewHolder

}//end public class MainActivity extends Activity