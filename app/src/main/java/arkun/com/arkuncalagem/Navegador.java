package arkun.com.arkuncalagem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Navegador extends AppCompatActivity {
    private WebView webView ;

    //Activity Em que Vai abrir
    final Activity activityAtual = this;
    Intent intent;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        url = intent.getStringExtra("url");
        //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_navegador);
        //WebView
        setContentView(R.layout.activity_navegador);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //Fim Web View

    }

    //Quando botão voltar for clicado
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();

        }else {
            super.onBackPressed();
        }
    }

}
