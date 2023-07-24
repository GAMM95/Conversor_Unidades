package TestJSON_Divisas;

import Clases.Conversor;
import Clases.ConversorDivisas;
import java.io.IOException;
import static java.lang.reflect.Array.getDouble;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class TestDivisas {

    public static void main(String[] args) {
//        double moneda = 1.0;
//        String monedaBase = "USD - Dólar estadounidense";
//        String monedaCambio = "PEN - Sol peruano";
//   
//        ConversorDivisas cd = new ConversorDivisas(moneda, monedaBase, monedaCambio);
//        double resultado = cd.convertir(moneda, monedaBase, monedaCambio);
// 
//        // Mostrar resultado
//        System.out.println(moneda + " " + monedaBase + " = " + String.format("%.2f",resultado) + " " + monedaCambio);
//        
        try {
            double moneda = 1.0;
            String monedaBase = "USD";
            String monedaCambio = "PEN";

            URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=f9f7038adb5642d89ea7123623b822ac");
            Scanner urlScanner = new Scanner(url.openStream());
            String json = urlScanner.useDelimiter("\\Z").next();
            urlScanner.close();

            JSONObject obj = new JSONObject(json);
            double tasaOrigen = obj.getJSONObject("rates").getDouble(monedaBase);
            double tasaDestino = obj.getJSONObject("rates").getDouble(monedaCambio);
            double resultado = moneda * tasaDestino / tasaOrigen;
            System.out.println(moneda + " " + monedaBase + " = " + String.format("%.2f", resultado) + " " + monedaCambio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
