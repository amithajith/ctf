package bazzinga.ctf;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.VideoView;

public class SplashActivity extends Activity {
    VideoView videoHolder;

    public SplashActivity() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            DisplayMetrics disp=new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(disp);
            int h=disp.heightPixels;
            int w=disp.widthPixels;
            this.videoHolder = new VideoView(this);
            this.setContentView(this.videoHolder);
            Uri ex = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.splash1);
            this.videoHolder.setVideoURI(ex);
            this.videoHolder.setOnCompletionListener(new OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {

                    SplashActivity.this.jump();
                }
            });
            this.videoHolder.start();
        } catch (Exception var3) {
            this.jump();
        }

    }

    private void jump() {
        if(!this.isFinishing()) {
            this.startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }
}
