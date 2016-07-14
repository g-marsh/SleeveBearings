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
    TextView tvLblBrgVel;
    TextView tvLblBrgPressure;
    TextView tvLblBrgPV;
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
    double dblMaxP;
    double dblMaxV;
    double dblMaxPV;
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
        tvLblBrgVel = (TextView)findViewById(R.id.textView20);
        tvBrgPressure = (TextView)findViewById(R.id.textView21);
        tvLblBrgPressure = (TextView)findViewById(R.id.textView22);
        tvBrgPV = (TextView)findViewById(R.id.textView23);
        tvLblBrgPV = (TextView)findViewById(R.id.textView24);
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

        // Calculate and View Results
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMaterial = String.valueOf(spnMaterial.getSelectedItem());
                if (strMaterial.equals("Delrin")) {
                    dblWearFactor = 0.000000006;
                    dblMaxP = 1000.0;
                    dblMaxV = 1000.0;
                    dblMaxPV = 3000.0;
                    tvLblBrgVel.setText(R.string.brg_vel_delrin);
                    tvLblBrgPressure.setText(R.string.brg_pres_delrin);
                    tvLblBrgPV.setText(R.string.brg_pv_delrin);
                }
                else if (strMaterial.equals("Bronze")) {
                    dblWearFactor = 0.00000000006;
                    dblMaxP = 2000.0;
                    dblMaxV = 1200.0;
                    dblMaxPV = 50000.0;
                    tvLblBrgVel.setText(R.string.brg_vel_bronze);
                    tvLblBrgPressure.setText(R.string.brg_pres_bronze);
                    tvLblBrgPV.setText(R.string.brg_pv_bronze);
                }
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

                dblLoadSpeedFPM = Double.valueOf(etLoadSpeedIPS.getText().toString())*60/12;
                tvLoadSpeedFPM.setText(String.format("%.2f",dblLoadSpeedFPM));
                dblBrgRPM = dblLoadSpeedFPM * 12 /(PI*Double.valueOf(etRollDia.getText().toString()));
                tvBrgRPM.setText(String.format("%.2f",dblBrgRPM));
                dblBrgVel = dblLoadSpeedFPM *Double.valueOf(etID.getText().toString())/Double.valueOf(etRollDia.getText().toString());
                tvBrgVel.setText(String.format("%.2f",dblBrgVel));
                dblBrgPress = Double.valueOf(etLoad.getText().toString())/(Double.valueOf(etBrgLength.getText().toString())*Double.valueOf(etID.getText().toString()));
                tvBrgPressure.setText(String.format("%.1f",dblBrgPress));
                dblBrgPV = dblBrgVel * dblBrgPress;
                tvBrgPV.setText(String.format("%.0f",dblBrgPV));
                if (dblBrgPress <= dblMaxP && dblBrgVel <= dblMaxV && dblBrgPV <= dblMaxPV) {
                    dblBrgLife = 3 * Double.valueOf(etBrgLength.getText().toString()) * Double.valueOf(spnWear.getSelectedItem().toString()) /
                            (dblMotionCoefficient * dblEnvironmentCoefficient * dblWearFactor * Double.valueOf(etLoad.getText().toString()) * dblBrgRPM);
                    tvBrgLifeHrs.setText(String.format("%.1f", dblBrgLife));
                    tvBrgVel.setTextColor(getResources().getColor(R.color.colorText));
                    tvBrgPressure.setTextColor(getResources().getColor(R.color.colorText));
                    tvBrgPV.setTextColor(getResources().getColor(R.color.colorText));
                    tvBrgLifeHrs.setTextColor(getResources().getColor(R.color.colorText));
                }
                else
                {
                    tvBrgLifeHrs.setText(R.string.fail);
                    if (dblBrgVel > dblMaxV) tvBrgVel.setTextColor(getResources().getColor(R.color.colorError));
                    if (dblBrgPress > dblMaxP) tvBrgPressure.setTextColor(getResources().getColor(R.color.colorError));
                    if (dblBrgPV > dblMaxPV) tvBrgPV.setTextColor(getResources().getColor(R.color.colorError));
                    tvBrgLifeHrs.setTextColor(getResources().getColor(R.color.colorError));
                }

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
