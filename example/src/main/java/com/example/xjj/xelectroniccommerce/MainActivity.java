package com.example.xjj.xelectroniccommerce;

import android.os.Bundle;

import com.example.xjj.core.activities.ProxyActivity;
import com.example.xjj.core.delegates.EquinoxDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public EquinoxDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


    }


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    // Example of a call to a native method
    // TextView tv = (TextView) findViewById(R.id.sample_text);
    // tv.setText(stringFromJNI());

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
