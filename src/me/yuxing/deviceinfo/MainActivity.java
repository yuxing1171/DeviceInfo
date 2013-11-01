package me.yuxing.deviceinfo;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    
    private TableLayout mTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTableLayout = (TableLayout) findViewById(R.id.table);
        
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        
        addTableRow(R.string.screen_width, outMetrics.widthPixels + " px");
        addTableRow(R.string.screen_height, outMetrics.heightPixels + " px");
        addTableRow(R.string.density, outMetrics.density);
        addTableRow(R.string.density_dpi, outMetrics.densityDpi + " ppi");
        addTableRow(R.string.screen_width_dip, Math.round(outMetrics.widthPixels/outMetrics.density) + " dp");
        addTableRow(R.string.screen_height_dip, Math.round(outMetrics.heightPixels/outMetrics.density) + " dp");
        addTableRow(R.string.android_id, android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID));
        addTableRow(R.string.imei, getImei(this));
        addTableRow(R.string.mac_address, getMacAddress(this));
    }
    
    private void addTableRow(int label, String value) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.table_row, null);
        ((TextView) view.findViewById(R.id.label)).setText(label);
        ((TextView) view.findViewById(R.id.value)).setText(value);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        mTableLayout.addView(view, layoutParams);
    }
    
    private void addTableRow(int label, int value) {
        addTableRow(label, String.valueOf(value));
    }
    
    private void addTableRow(int label, float value) {
        addTableRow(label, String.valueOf(value));
    }
    
    public static String getImei(Context context) {
    	TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    	return tm.getDeviceId();
    }
    
    public static String getMacAddress(Context context) {
    	WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    	WifiInfo wifiInfo = wifi.getConnectionInfo();
    	return wifiInfo.getMacAddress();
    }
}
