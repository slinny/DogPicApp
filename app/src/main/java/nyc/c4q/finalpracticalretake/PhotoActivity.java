package nyc.c4q.finalpracticalretake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import nyc.c4q.finalpracticalretake.networking.Common;
import nyc.c4q.finalpracticalretake.service.DogService;

import static nyc.c4q.finalpracticalretake.util.Constants.SHARED_PREFS;
import static nyc.c4q.finalpracticalretake.util.Constants.URL;
import static nyc.c4q.finalpracticalretake.util.Constants.USER_KEY;

public class PhotoActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private ImageView dogPhotoImageView;
    private Intent intent;
    private DogService dogService;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        setViews();
    }

    private void setViews(){
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        dogPhotoImageView = findViewById(R.id.dog_photo_imageview);
        intent = getIntent();
        dogService = Common.getBreed();
        url = intent.getStringExtra(URL);
        Picasso.with(this).load(url).into(dogPhotoImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                preferences.edit().remove(USER_KEY).apply();
                startActivity(new Intent(PhotoActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
