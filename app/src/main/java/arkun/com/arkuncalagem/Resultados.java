package arkun.com.arkuncalagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;

public class Resultados extends AppCompatActivity {
    //DECLARANDO VARIÁVEIS
    Intent intent;
    float ca, mg, k, al, alh, arg;
    String cultura;
    Analise analise;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    SeekBar barraV2, barraPNRT, barraProfundidade;
    TextView contV2, contPRNT, contProfundidade;
    float minasNC, minasY, minasMt, minasX, minasCTC, saturacaoNC, saturacaoV1, saturacaoV2, saturacaoCTC, minasParte1, minasParte2;
    TextView txtStauracao, txtMinas;
    float minasQC, saturacaoQC;
    DecimalFormat df;
    TextView txtQCSaturação, txtQCMinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        df = new DecimalFormat("0.00");

        //CARREGAR DADOS ANTERIORES
        carrregarDadosAnalise();
        carregarSpinner();

        //REFERENCIANDO INTENS DO LAYOUT
        barraV2 = findViewById(R.id.barraV2);
        barraPNRT = findViewById(R.id.barraPRNT);
        barraProfundidade = findViewById(R.id.barraProfundidade);
        contV2 = findViewById(R.id.contV2);
        contPRNT = findViewById(R.id.contPRNT);
        contProfundidade = findViewById(R.id.contProfundidade);
        txtMinas = findViewById(R.id.txtMinas);
        txtStauracao = findViewById(R.id.txtsaturacao);
        txtQCSaturação = findViewById(R.id.txtQCSaturação);
        txtQCMinas = findViewById(R.id.txtQCMinas);

        barraV2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        contV2.setText(String.valueOf(progress + 50));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        calcular();
                    }
                }
        );
        barraPNRT.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        contPRNT.setText(String.valueOf(progress + 60));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        calcular();
                    }
                }
        );
        barraProfundidade.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        contProfundidade.setText(String.valueOf(progress + 20));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        calcular();
                    }
                }
        );

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                calcular();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void calcular(){
        calcularNCMinas();
        calcularNCSaturacao();
        calcularQCMinas();
        calcularQCSaturacao();
    }

    private void calcularQCSaturacao() {
        float PRNT = Float.parseFloat(contPRNT.getText().toString());
        float profundidade = Float.parseFloat(contProfundidade.getText().toString());
        saturacaoQC = saturacaoNC * 100 / PRNT * profundidade / 20;
        txtQCSaturação.setText(df.format(saturacaoQC));
    }

    private void calcularQCMinas() {
        float PRNT = Float.parseFloat(contPRNT.getText().toString());
        float profundidade = Float.parseFloat(contProfundidade.getText().toString());
        minasQC = minasNC * 100 / PRNT * profundidade / 20;
        txtQCMinas.setText(df.format(minasQC));
    }

    private void exibirNCMinas() {
        if (minasNC < 0 ) {
            minasNC = 0;
        }
        txtMinas.setText(df.format(minasNC));
    }

    private void calcularNCSaturacao() {
        calcularCTC();
        calcularV1();
        saturacaoV2 = Float.parseFloat(contV2.getText().toString());
        saturacaoNC = (saturacaoV2 - saturacaoV1 * 100) * saturacaoCTC / 100;
        exibirNCSaturacao();
    }

    private void exibirNCSaturacao() {
        if (saturacaoNC < 0.0){
            saturacaoNC = 0;
        }
        txtStauracao.setText(df.format(saturacaoNC));
    }

    private void calcularV1() {
        saturacaoV1 = (analise.calcio + analise.magnesio + analise.potassio)/saturacaoCTC;
    }

    private void calcularCTC() {
        saturacaoCTC = analise.calcio + analise.magnesio + analise.potassio + analise.AlH;
    }

    private void calcularNCMinas() {
        calcularY();
        calcularX();
        minasCTC = analise.calcio + analise.magnesio + analise.potassio + analise.Aluminio;
        minasParte1 = (minasY * ( analise.Aluminio - ( minasMt * minasCTC / 100)));
        minasParte2 = (minasX - (analise.calcio + analise.magnesio));
        if (minasParte1 < 0 ){
            minasParte1 = 0;
        }
        if (minasParte2 < 0 ){
            minasParte2 = 0;
        }
        minasNC = (float) minasParte1 + minasParte2;
        exibirNCMinas();

    }

    private void calcularY() {
        float arg2 = (float) analise.argila * analise.argila;
        minasY =(float) (0.0302 + 0.06532 * analise.argila - 0.000257 * arg2) ;
    }

    private void calcularX() {
        int i = (int) spinner.getSelectedItemId();
        //Toast.makeText(getApplicationContext(),String.valueOf(i), Toast.LENGTH_SHORT).show();
        if (i == 0 ){
            //Toast.makeText(getApplicationContext(),"Milho", Toast.LENGTH_SHORT).show();
            minasMt = 15;
            minasX = 2;
        }else if (i == 1){
            //Toast.makeText(getApplicationContext(),"Soja", Toast.LENGTH_SHORT).show();
            minasMt = 20;
            minasX = 2;
        }else if (i == 2){
            //Toast.makeText(getApplicationContext(),"Cana", Toast.LENGTH_SHORT).show();
            minasMt = 30;
            minasX = (float) 2.5;
        }
    }

    public void carrregarDadosAnalise(){
        intent = getIntent();
        ca  = intent.getFloatExtra("calcio", 0);
        mg  = intent.getFloatExtra("magnesio", 0);
        k   = intent.getFloatExtra("potassio", 0);
        al  = intent.getFloatExtra("aluminio", 0);
        alh = intent.getFloatExtra("alh", 0);
        arg = intent.getFloatExtra("arg", 0);
        cultura= intent.getStringExtra("cultura");
        //Toast.makeText(getApplicationContext(), String.valueOf(ca) + mg + k + al + alh + arg + cultura ,Toast.LENGTH_LONG).show();OK
        analise = new Analise(ca,mg,k,al,alh,arg,cultura);
    }

    public void carregarSpinner(){
        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Culturas , android.R.layout.simple_list_item_1 );
        spinner.setAdapter(adapter);
    }


}
