package arkun.com.arkuncalagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DadosDaAnalise extends AppCompatActivity {

    EditText edtCalcio, edtMagnesio, edtPotassio, edtAluminio, edtAlH, edtMO, edtArgila;
    Button btnEviar;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_da_analise);
        edtCalcio = findViewById(R.id.edtCa);
        edtMagnesio = findViewById(R.id.edtMg);
        edtPotassio = findViewById(R.id.edtK);
        edtAluminio = findViewById(R.id.edtAl);
        edtAlH = findViewById(R.id.edtAlH);
        edtArgila = findViewById(R.id.edtArgila);
        btnEviar = findViewById(R.id.btnEnviar);

        btnEviar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //String valorSpinner = spinner.getSelectedItem().toString()
                Intent intent = new Intent(DadosDaAnalise.this, Resultados.class);
                intent.putExtra("calcio",   Float.parseFloat(edtCalcio  .getText().toString()));
                intent.putExtra("magnesio", Float.parseFloat(edtMagnesio.getText().toString()));
                intent.putExtra("potassio", Float.parseFloat(edtPotassio.getText().toString()));
                intent.putExtra("aluminio", Float.parseFloat(edtAluminio.getText().toString()));
                intent.putExtra("alh",      Float.parseFloat(edtAlH     .getText().toString()));
                intent.putExtra("arg",      Float.parseFloat(edtArgila  .getText().toString()));
                startActivity(intent);
            }
        });

    }


}
