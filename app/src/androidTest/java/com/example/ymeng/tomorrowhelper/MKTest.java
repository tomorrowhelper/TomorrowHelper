package com.example.ymeng.tomorrowhelper;

import android.app.Instrumentation;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Author:YMeng
 * Time:2018/7/31  10:39
 * This is MKTest
 */
@RunWith(AndroidJUnit4.class)
public class MKTest {
    public Instrumentation mInstrumentation;
    public UiDevice mUiDevice;
    @Before
    public void setUp(){
        mInstrumentation = InstrumentationRegistry.getInstrumentation();
        mUiDevice = UiDevice.getInstance(mInstrumentation);
    }
    @Test
    public void DemoTest() throws RemoteException {
        mUiDevice.pressRecentApps();
    }

}
