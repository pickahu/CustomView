package pickahu.customview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import pickahu.customview.ImageAndDes.ImageDes;
import pickahu.customview.VerificationCode.ui.OnDataChangeListener;
import pickahu.customview.VerificationCode.ui.VerificationCode;
import pickahu.customview.circleProgressBar.CircleProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        VerificationCode code = (VerificationCode) findViewById(R.id.code);

        code.setText("8888").setBgColor(Color.BLUE).setTextSize(26);

        code.setOnDataChangedListener(new OnDataChangeListener() {
            @Override
            public void onDataChanged(String data) {
                Log.e("VerificationCode", data);
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

        ImageDes imageDes = (ImageDes) findViewById(R.id.imagedes);
        imageDes.setText("风景不错哦").setImage(R.drawable.fengjing);


        final CircleProgressBar circleProgress = (CircleProgressBar) findViewById(R.id.cicle_progress);
        circleProgress.setCircleRadius(50);



        new Thread() {
            @Override
            public void run() {

                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                        circleProgress.setProgress(i);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();

    }
}
