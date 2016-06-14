package com.gregmarsh.sleevebearings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    // Variables
    Spinner spnMaterial;
    String strMaterial;
    double dblWearFactor;
    EditText etOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate Variables
        spnMaterial = (Spinner)findViewById(R.id.spinner);

        // Declare and populate array
        String [] aryMaterial = getResources().getStringArray(R.array.aryMaterial);

        // Bind the array and apply settings to the Spinner.
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aryMaterial);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaterial.setAdapter(dataAdapter);

        // Calculate and View Results
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMaterial = String.valueOf(spnMaterial.getSelectedItem());
                if (strMaterial.equals("Delrin")) dblWearFactor = .00001;
                else if (strMaterial.equals("Bronze")) dblWearFactor = .00002;
                else /* error handling */ ;



            }
        });


    }
}
