package arkun.com.arkuncalagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void abrirTela(View view) {
        intent = new Intent(MainActivity.this, Navegador.class);
        intent.putExtra("url", "http://arkun.com.br");
        startActivity(intent);
    }


    public void abrirEadArkunfert(View view) {
        intent = new Intent(MainActivity.this, Navegador.class);
        intent.putExtra("url", "https://arkun.com.br/product/arkunfert-ead/");
        startActivity(intent);
    }

    public void abrirWebinar(View view) {
        intent = new Intent(MainActivity.this, Navegador.class);
        intent.putExtra("url", "https://www.youtube.com/watch?v=LiUNDwk_ZM8");
        startActivity(intent);
    }

    public void abrirCalagem(View view) {
        intent = new Intent(MainActivity.this, DadosDaAnalise.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        super.onBackPressed();
    }

}
