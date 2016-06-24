package com.gregmarsh.sleevebearings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import static java.lang.Math.PI;

public class MainActivity extends AppCompatActivity {

    // Spinners
    Spinner spnMaterial;
    Spinner spnMotion;
    Spinner spnEnvironment;
    Spinner spnWear;
    // String
    String strMaterial;
    String strMotion;
    String strEnvironment;
    // Text Views
    TextView tvWearFactor;
    TextView tvMotionCoefficient;
    TextView tvEnviornmentCoefficient;
    TextView tvLoadSpeedFPM;
    TextView tvBrgRPM;
    TextView tvBrgVel;
    TextView tvBrgPressure;
    TextView tvBrgPV;
    TextView tvBrgLifeHrs;
    // EditTexts
    EditText etID;
    EditText etRollDia;
    EditText etBrgLength;
    EditText etLoadSpeedIPS;
    EditText etLoad;
    // Variables
    double dblWearFactor;
    double dblMotionCoefficient;
    double dblEnvironmentCoefficient;
    double dblInsideDia;
    double dblRollingDia;
    double dblBrgLength;
    double dblLoadSpeedFPM;
    double dblWearLimit;
    double dblBrgRPM;
    double dblBrgVel;
    double dblBrgPress;
    double dblBrgPV;
    double dblBrgLife;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate Variables
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        // Spinners
        spnMaterial = (Spinner)findViewById(R.id.spinner);
        spnMotion = (Spinner)findViewById(R.id.spinner1);
        spnEnvironment = (Spinner)findViewById(R.id.spinner2);
        spnWear = (Spinner)findViewById(R.id.spinner3);
        // Edit Text
        etID = (EditText)findViewById(R.id.editText);
        etRollDia = (EditText)findViewById(R.id.editText1);
        etBrgLength = (EditText)findViewById(R.id.editText2);
        etLoadSpeedIPS = (EditText)findViewById(R.id.editText3);
        etLoad = (EditText)findViewById(R.id.editText4);
        // Text Views
        tvWearFactor = (TextView)findViewById(R.id.textView9);
        tvMotionCoefficient = (TextView)findViewById(R.id.textView11);
        tvEnviornmentCoefficient = (TextView)findViewById(R.id.textView13);
        tvLoadSpeedFPM = (TextView)findViewById(R.id.textView15);
        tvBrgRPM = (TextView)findViewById(R.id.textView17);
        tvBrgVel = (TextView)findViewById(R.id.textView19);
        tvBrgPressure = (TextView)findViewById(R.id.textView21);
        tvBrgPV = (TextView)findViewById(R.id.textView23);
        tvBrgLifeHrs = (TextView)findViewById(R.id.textView25);

        // Declare and populate arrays
        String [] aryMaterial = getResources().getStringArray(R.array.aryMaterial);
        String [] aryMotion = getResources().getStringArray(R.array.aryMotion);
        String [] aryEnvironment = getResources().getStringArray(R.array.aryEnvironment);
        String [] aryWear = getResources().getStringArray(R.array.aryWear);

        // Bind the arrays and apply settings to the Spinners.
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aryMaterial);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aryMotion);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aryEnvironment);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aryWear);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaterial.setAdapter(dataAdapter);
        spnMotion.setAdapter(dataAdapter1);
        spnEnvironment.setAdapter(dataAdapter2);
        spnWear.setAdapter(dataAdapter3);
        //

        // Calculate and View Results
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMaterial = String.valueOf(spnMaterial.getSelectedItem());
                if (strMaterial.equals("Delrin")) dblWearFactor = 0.000000006;
                else if (strMaterial.equals("Bronze")) dblWearFactor = 0.00000000006;
                else /* error handling */ dblWearFactor = 0;
//                tvWearFactor.setText(String.format("%.2e",Double.valueOf(spnWear.getSelectedItem().toString())));
                tvWearFactor.setText(String.format("%.2e",dblWearFactor));

                strMotion = String.valueOf(spnMotion.getSelectedItem());
                if (strMotion.equals("Rotary")) dblMotionCoefficient = 1.3;
//                else if (strMotion.equals("Linear")) dblMotionCoefficient = 1.5;
                else dblMotionCoefficient = 2.0;
                tvMotionCoefficient.setText(String.format("%.1f",dblMotionCoefficient));

                strEnvironment = String.valueOf(spnEnvironment.getSelectedItem());
                if (strEnvironment.equals("Clean")) dblEnvironmentCoefficient = 1;
                else dblEnvironmentCoefficient = 3;
                tvEnviornmentCoefficient.setText(String.valueOf(dblEnvironmentCoefficient));
//                http://stackoverflow.com/questions/6438061/can-i-scroll-a-scrollview-programmatically-in-android
//                http://stackoverflow.com/questions/8015313/how-to-programmatically-scroll-a-scrollview-to-bottom

//                EditText etRollDia;
//                EditText etBrgLength;
//                EditText etLoadSpeedIPS;
//                EditText etLoad;
//                EditText etWearLimit;
//                double dblWearFactor;
//                double dblMotionCoefficient;
//                double dblEnvironmentCoefficient; //above done
//                double dblInsideDia;
//                double dblRollingDia;
//                double dblBrgLength;
//                double dblLoadSpeedFPM; x
//                double dblWearLimit;
//                double dblBrgRPM;
//                double dblBrgVel;
//                double dblBrgPress;
//                double dblBrgPV;
//                double dblBrgLife;
                dblLoadSpeedFPM = Double.valueOf(etLoadSpeedIPS.getText().toString())*60/12;
                tvLoadSpeedFPM.setText(String.valueOf(dblLoadSpeedFPM));
                dblBrgRPM = dblLoadSpeedFPM * 12 /(PI*Double.valueOf(etRollDia.getText().toString()));
                tvBrgRPM.setText(String.format("%.2f",dblBrgRPM));
                dblBrgVel = dblLoadSpeedFPM *Double.valueOf(etID.getText().toString())/Double.valueOf(etRollDia.getText().toString());
                tvBrgVel.setText(String.format("%.2f",dblBrgVel));

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
