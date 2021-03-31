package arkun.com.arkuncalagem;

import android.widget.EditText;
import android.widget.Toast;

public class Analise {
    float calcio;
    float magnesio;
    float potassio;
    float Aluminio;
    float AlH;
    float argila;
    String cultura;

    public  Analise(float Ca, float Mg, float K, float Al, float AlH, float argila, String cultura){
        this.calcio = Ca;
        this.magnesio = Mg;
        this.potassio = K;
        this.Aluminio = Al;
        this.AlH = AlH;
        this.argila = argila;
        this.cultura = cultura;
    }
}
