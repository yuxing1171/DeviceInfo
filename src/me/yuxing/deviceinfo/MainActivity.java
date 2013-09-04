package me.yuxing.deviceinfo;

import android.app.Activity;
import android.os.Bundle;
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
}
