package identify.dai.com.accelerationball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartServiceClick(View view) {
        Intent intent = new Intent(this,MyFloatService.class);
        startService(intent);
    }
}
