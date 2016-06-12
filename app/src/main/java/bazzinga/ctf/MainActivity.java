package bazzinga.ctf;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.widget.TextView;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {
    private EditText name, email, usrname, pwd,cpwd;
    private TextView title,submit;
    private Bitmap bitmap;
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText);
        usrname = (EditText) findViewById(R.id.editText4);
        pwd = (EditText)findViewById(R.id.editText5);
        cpwd = (EditText)findViewById(R.id.editText6);
        title = (TextView)findViewById(R.id.titlee);
        submit = (TextView)findViewById(R.id.sub);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Hamurz.ttf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "fonts/Broken.ttf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "fonts/Quango.ttf");
        title.setTypeface(type);
        submit.setTypeface(type);

        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.img);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.dp);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            imageView.buildDrawingCache();
            bitmap = imageView.getDrawingCache();
            ImageView sub = (ImageView) findViewById(R.id.imageView);
            sub.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Intent mainIntent = new Intent(MainActivity.this, profile.class);
                    mainIntent.putExtra("name", name.getText().toString());
                    mainIntent.putExtra("email", email.getText().toString());
                    mainIntent.putExtra("usrname", usrname.getText().toString());
                    mainIntent.putExtra("pwd", pwd.getText().toString());
                    mainIntent.putExtra("cname", cpwd.getText().toString());
                    mainIntent.putExtra("BitmapImage", bitmap);

                    if (pwd.getText().toString().equals(cpwd.getText().toString())) {
                        startActivity(mainIntent);
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("oops!");
                        alertDialog.setMessage("Confirmation password doesn't match");
                        alertDialog.setCanceledOnTouchOutside(true);
                        alertDialog.show();
                    }
                    return false;
                }
            });

        }


    }
}
