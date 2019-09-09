package com.example.myonem2msimulatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lguplus.onem2m.device.Onem2mAgent;
import com.lguplus.onem2m.device.OperationListener;
import com.lguplus.onem2m.device.attributes.domain.Attributes;
import com.lguplus.onem2m.device.attributes.domain.ConditionalRequest;
import com.lguplus.onem2m.device.common.exception.IotpException;
import com.lguplus.onem2m.device.resources.domain.Resource;
import com.lguplus.onem2m.mef.connector.domain.auth.request.DeviceInfo;

public class MainActivity extends AppCompatActivity {

    private String devicemodel;
    private String deviceserno;
    private String servicecode;
    private String devicetype;
    private String ctn;
    private String iccid;
    private String url;
    private Boolean saveAuthData;

    private EditText dmText;
    private EditText snText;
    private EditText scText;
    private EditText dtText;
    private EditText ctnText;
    private EditText iccidText;
    private Button mefBtn;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        mefAuthDataload();

        url = "testmef.onem2m.uplus.co.kr/mef";


        dmText = (EditText) findViewById(R.id.deviceModel);
        snText = (EditText) findViewById(R.id.deviceSerNo);
        scText = (EditText) findViewById(R.id.serviceCode);
        dtText = (EditText) findViewById(R.id.deviceType);
        ctnText = (EditText) findViewById(R.id.ctn);
        iccidText = (EditText) findViewById(R.id.iccId);
        mefBtn = (Button) findViewById(R.id.mefAuthBttn);

        if(saveAuthData) {
            dmText.setText(devicemodel);
            snText.setText(deviceserno);
            scText.setText(servicecode);
            dtText.setText(devicetype);
            ctnText.setText(ctn);
            iccidText.setText(iccid);
        }

        mefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mefAuthDataSave();
                new oneM2MInitialize().mefAuth(devicemodel, deviceserno, servicecode, devicetype, ctn, iccid);

            }
        });


    }

    private void mefAuthDataSave() {
        SharedPreferences.Editor mEditor = sharedPreferences.edit();

        mEditor.putString("DEVICE_MODEL", dmText.getText().toString().trim());
        mEditor.putString("DEVICE_SERIAL_NUMBER", snText.getText().toString().trim());
        mEditor.putString("SERVICE_CODE", scText.getText().toString().trim());
        mEditor.putString("DEVICE_TYPE", dtText.getText().toString().trim());
        mEditor.putString("CTN", ctnText.getText().toString().trim());
        mEditor.putString("ICCID", iccidText.getText().toString().trim());

        mEditor.apply();

    }

    private void mefAuthDataload(){
        saveAuthData = sharedPreferences.getBoolean("SAVE_MEFAUTH_DATA", false);

        devicemodel = sharedPreferences.getString("DEVICE_MODEL", "");
        deviceserno = sharedPreferences.getString("DEVICE_SERIAL_NUMBER", "");
        servicecode = sharedPreferences.getString("SERVICE_CODE", "");
        devicetype = sharedPreferences.getString("DEVICE_TYPE", "");
        ctn = sharedPreferences.getString("CTN", "");
        iccid = sharedPreferences.getString("ICCID", "");
    }
}
