package pickahu.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import pickahu.customview.volControl.VolView;


public class MainActivity extends AppCompatActivity {


    private VolView mVolView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVolView = (VolView)findViewById(R.id.vol);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode)
        {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(MainActivity.this,"down",Toast.LENGTH_SHORT).show();
                mVolView.down();
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(MainActivity.this,"up",Toast.LENGTH_SHORT).show();
                mVolView.up();
                return true;
        }

        return super.onKeyDown(keyCode, event);

    }
}
