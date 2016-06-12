package bazzinga.ctf;

import android.graphics.Typeface;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsic;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

/**
 * Created by ab219tx on 11-06-2016.
 */
public class profile extends ActionBarActivity {

    private TextView sign,n,e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        sign = (TextView)findViewById(R.id.sign);
        n=(TextView)findViewById(R.id.name);
        e=(TextView)findViewById(R.id.email);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Hamurz.ttf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "fonts/Broken.ttf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "fonts/Quango.ttf");
        sign.setTypeface(type1);
        n.setTypeface(type2);
        e.setTypeface(type2);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String usrname = intent.getStringExtra("usrname");
        String pwd = intent.getStringExtra("pwd");
        String cpwd = intent.getStringExtra("cpwd");
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        TextView dname = (TextView) findViewById(R.id.name);
        TextView dmail = (TextView) findViewById(R.id.email);
        TextView dusername = (TextView) findViewById(R.id.usrname);
        dname.setText(name);
        dmail.setText(email);
        dusername.setText(usrname);
        ImageView tv1;
        tv1= (ImageView) findViewById(R.id.foto);
        tv1.setImageBitmap(bitmap);
    }

}
