package nyc.c4q.finalpracticalretake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.finalpracticalretake.dogmodels.Breed;
import nyc.c4q.finalpracticalretake.networking.Common;
import nyc.c4q.finalpracticalretake.service.DogService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nyc.c4q.finalpracticalretake.util.Constants.DEFAULT_USER;
import static nyc.c4q.finalpracticalretake.util.Constants.SHARED_PREFS;
import static nyc.c4q.finalpracticalretake.util.Constants.USER_KEY;

public class BreedsActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;
    private TextView usernameText;
    private String[] breedName = {"terrier", "spaniel", "retriever", "poodle"};
    private String username;
    private ImageView terrierImage, spanielImage, retrieverImage, poodleImage, imageView;
    private DogService dogService;
    public static final String BREED_NAME = "breedName";
    private ScrollView breedsScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);
        checkUser();
        setViews();

        for (int i = 0; i < breedName.length; i++) {
            requestBreedImage(breedName[i]);
        }

    }

    private void checkUser() {
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        username = preferences.getString(USER_KEY, DEFAULT_USER);
        if (username.equalsIgnoreCase(DEFAULT_USER)) {
            startActivity(new Intent(BreedsActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void setViews() {
        breedsScrollView = findViewById(R.id.breeds_scrollview);
        usernameText = (TextView) findViewById(R.id.breeds_username);

        usernameText.setText(String.format("%s%s?", getString(R.string.what_kind_dog), username));



        terrierImage = (ImageView) findViewById(R.id.terrier_image);
        spanielImage = (ImageView) findViewById(R.id.spaniel_image);
        retrieverImage = (ImageView) findViewById(R.id.retriever_image);
        poodleImage = (ImageView) findViewById(R.id.poodle_image);

        dogService = Common.getBreed();

        findViewById(R.id.terrier_card).setOnClickListener(this);
        findViewById(R.id.spaniel_card).setOnClickListener(this);
        findViewById(R.id.retriever_card).setOnClickListener(this);
        findViewById(R.id.poodle_card).setOnClickListener(this);
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
                startActivity(new Intent(BreedsActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestBreedImage(final String breedName) {
        dogService.getBreed(breedName).enqueue(new Callback<Breed>() {
            @Override
            public void onResponse(Call<Breed> call, Response<Breed> response) {
                String url = response.body().getMessage();
                switch (breedName) {
                    case "terrier":
                        imageView = terrierImage;
                        break;
                    case "spaniel":
                        imageView = spanielImage;
                        break;
                    case "retriever":
                        imageView = retrieverImage;
                        break;
                    case "poodle":
                        imageView = poodleImage;
                        break;
                }
                Picasso.with(getApplicationContext()).load(url).into(imageView);
            }

            @Override
            public void onFailure(Call<Breed> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void onClick(View v) {
        String breedName = "";
        switch (v.getId()) {
            case R.id.terrier_card:
                breedName = "terrier";
                break;
            case R.id.spaniel_card:
                breedName = "spaniel";
                break;
            case R.id.retriever_card:
                breedName = "retriever";
                break;
            case R.id.poodle_card:
                breedName = "poodle";
                break;
        }
        Intent intent = new Intent(this, DogsActivity.class);
        intent.putExtra(BREED_NAME, breedName);
        startActivity(intent);
    }

}
