package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView home_team, away_team;
    private TextView home_score, away_score;
    private ImageView home_logo;
    private ImageView away_logo;
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    public static final String homeTeam="home";
    public static final String awayTeam="away";
    public static final String logoHome="logohome";
    public static final String logoAway="logoaway";
    public static final String hasilHome="hasilhome";
    public static final String hasilAway="hasilaway";
    public static final String hasilDraw="hasildraw";
    private Button addHome;
    private Button addAway;
    private Uri image1 = null;
    private int nilaiHome, nilaiAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO

        home_team=findViewById(R.id.txt_home);
        away_team=findViewById(R.id.txt_away);
        home_logo=findViewById(R.id.home_logo);
        away_logo=findViewById(R.id.away_logo);
        home_score=findViewById(R.id.score_home);
        away_score=findViewById(R.id.score_away);
        addHome=findViewById(R.id.btn_add_home);
        addAway=findViewById(R.id.btn_add_away);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            // TODO: display value here
            String value1 = getIntent().getExtras().getString(homeTeam);
            String value2 = getIntent().getExtras().getString(awayTeam);
            home_team.setText(value1);
            away_team.setText(value2);
        }
        Bitmap bmp = (Bitmap)extras.getParcelable("logoHome");
        home_logo.setImageBitmap(bmp);
        Bitmap bit = (Bitmap)extras.getParcelable("logoAway");
        away_logo.setImageBitmap(bit);

        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }
    public void teamHome(int score1){
        home_score.setText(String.valueOf(score1));
    }
    public void teamAway(int score2){
        away_score.setText(String.valueOf(score2));
    }
    public void addHome (View view){
        nilaiHome=nilaiHome+1;
        teamHome(nilaiHome);
    }
    public void addAway (View view){
        nilaiAway=nilaiAway+1;
        teamAway(nilaiAway);
    }

    public void hasil(View view){
        Intent intent = new Intent (this, ResultActivity.class);
        String home = home_team.getText().toString();
        String away = away_team.getText().toString();
        if(nilaiHome>nilaiAway){
            intent.putExtra(hasilHome, home);
            startActivity(intent);
        } else if(nilaiAway>nilaiHome){
            intent.putExtra(hasilHome, away);
            startActivity(intent);
        } else{
            intent.putExtra(hasilHome,"Skor sama");
            startActivity(intent);
        }

    }
}
