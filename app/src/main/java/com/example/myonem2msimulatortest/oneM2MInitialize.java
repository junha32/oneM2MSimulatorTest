package com.example.myonem2msimulatortest;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import com.lguplus.onem2m.device.Onem2mAgent;
import com.lguplus.onem2m.device.OperationListener;
import com.lguplus.onem2m.device.attributes.domain.Attributes;
import com.lguplus.onem2m.device.attributes.domain.ConditionalRequest;
import com.lguplus.onem2m.device.common.exception.IotpException;
import com.lguplus.onem2m.device.resources.domain.Resource;
import com.lguplus.onem2m.mef.connector.domain.auth.request.DeviceInfo;

public class oneM2MInitialize implements OperationListener{

    private static final String TAG = "oneM2MInitialize";
    Onem2mAgent agent;


    public oneM2MInitialize() {
        agent = new Onem2mAgent();
    }

    public void mefAuth(String deviceModel, String deviceSerNo, String serviceCode, String deviceType, String ctn, String iccid  ) {


        // Initialize
        try {
            agent.initilaize("R.raw.configuration");
        } catch (IotpException e) {
            System.out.println("Error. Error Code : " + e.getCode() + ", message : " + e.getMessage());
        }

        // Authenticate
        try {
            DeviceInfo deviceInfo = new DeviceInfo();

            deviceInfo.setDeviceModel(deviceModel);
            deviceInfo.setServiceCode(serviceCode);
            deviceInfo.setDeviceType(deviceType); // adn : "AE" or mn : "RemoteCSE"
            deviceInfo.setCtn(ctn);
            deviceInfo.setMacAddress("");
            deviceInfo.setDeviceSerialNo(deviceSerNo);
            deviceInfo.setIccId(iccid);

            agent.authenticate(deviceInfo);
            String EntityId = agent.getEntityId();
        } catch (IotpException e) {
            System.out.println("Error. Errmedor Code : " + e.getCode() + ", message : " + e.getMessage());
        }

        // Finalize
//        try {
//            agent.finalize();
//        } catch ( IotpException e ) {
//            System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
//        }

    }

    @Override
    public Resource onRequestCreate(Attributes attributes, String s, String s1) throws IotpException {
        return null;
    }

    @Override
    public Resource onRequestRetrieve(String s, String s1, ConditionalRequest conditionalRequest) throws IotpException {
        return null;
    }

    @Override
    public Resource onRequestUpdate(Attributes attributes, String s, String s1, ConditionalRequest conditionalRequest) throws IotpException {
        return null;
    }

    @Override
    public Resource onRequestDelete(String s, String s1, ConditionalRequest conditionalRequest) throws IotpException {
        return null;
    }

    @Override
    public void onNotify(Attributes attributes, Resource resource, String s, String s1) throws IotpException {

    }
}
