package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText home_team, away_team;
    private ImageView home_logo;
    private ImageView away_logo;
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1 & 2;
    private static final int GALLERY_REQUEST = 1;
    public static final String homeTeam="home";
    public static final String awayTeam="away";
    public static final String logoHome="logohome";
    public static final String logoAway="logoaway";
    private Uri image1 = null;
    private Uri image2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        home_team = findViewById(R.id.home_team);
        away_team = findViewById(R.id.away_team);
        home_logo = findViewById(R.id.home_logo);
        away_logo = findViewById(R.id.away_logo);
        //5. Next Button Pindah Ke MatchActivity
    }

    public void handleNext(View view) {
        String team_home = home_team.getText().toString();
        String team_away = away_team.getText().toString();
        if(team_home.isEmpty()){
            home_team.setError("Fill home team name");
        } else if(team_away.isEmpty()){
            away_team.setError("Fill away team name");
        } else{
            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra(homeTeam, team_home);
            intent.putExtra(awayTeam, team_away);
            home_logo.buildDrawingCache();
            Bitmap image = home_logo.getDrawingCache();
            Bundle extras = new Bundle();
            extras.putParcelable("logoHome", image);
            intent.putExtras(extras);
            away_logo.buildDrawingCache();
            Bitmap gambar = away_logo.getDrawingCache();
            Bundle ext = new Bundle();
            ext.putParcelable("logoAway", gambar);
            intent.putExtras(ext);
            startActivity(intent);
        }
    }
    public void imageHome(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    public void imageAway(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 1){
            if (data != null){
                try{
                    image1 = data.getData();
                    Bitmap bitmap =  MediaStore.Images.Media.getBitmap(this.getContentResolver(), image1);
                    home_logo.setImageBitmap(bitmap);

                } catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        if (requestCode == 2){
            if (data != null){
                try{
                    image2 = data.getData();
                    Bitmap bitmap =  MediaStore.Images.Media.getBitmap(this.getContentResolver(), image2);
                    away_logo.setImageBitmap(bitmap);

                } catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

}
