package com.gregmarsh.sleevebearings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    // Variables
    Spinner spnMaterial;
    Spinner spnMotion;
    Spinner spnEnvironment;
    String strMaterial;
    double dblWearFactor;
    EditText etOD;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate Variables
        spnMaterial = (Spinner)findViewById(R.id.spinner);
        spnMotion = (Spinner)findViewById(R.id.spinner1);
        spnEnvironment = (Spinner)findViewById(R.id.spinner2);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        // Declare and populate arrays
        String [] aryMaterial = getResources().getStringArray(R.array.aryMaterial);
        String [] aryMotion = getResources().getStringArray(R.array.aryMotion);
        String [] aryEnvironment = getResources().getStringArray(R.array.aryEnvironment);

        // Bind the arrays and apply settings to the Spinners.
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aryMaterial);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aryMotion);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaterial.setAdapter(dataAdapter);
        spnMotion.setAdapter(dataAdapter1);

        // Calculate and View Results
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMaterial = String.valueOf(spnMaterial.getSelectedItem());
                if (strMaterial.equals("Delrin")) dblWearFactor = .00001;
                else if (strMaterial.equals("Bronze")) dblWearFactor = .00002;
                else /* error handling */ ;

//                http://stackoverflow.com/questions/6438061/can-i-scroll-a-scrollview-programmatically-in-android
//                http://stackoverflow.com/questions/8015313/how-to-programmatically-scroll-a-scrollview-to-bottom
//
                scrollView.post(new Runnable() {
                    @Override

                    public void run() {

//                        scrollView.scrollTo(0, scrollView.getBottom());
                        scrollView.fullScroll(View.FOCUS_DOWN);

                    }
                });



            }
        });


    }
}
