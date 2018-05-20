package nyc.c4q.finalpracticalretake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.finalpracticalretake.dogmodels.BreedImages;
import nyc.c4q.finalpracticalretake.networking.Common;
import nyc.c4q.finalpracticalretake.recyclerview.DogAdapter;
import nyc.c4q.finalpracticalretake.service.DogService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nyc.c4q.finalpracticalretake.util.Constants.DEFAULT_USER;
import static nyc.c4q.finalpracticalretake.util.Constants.SHARED_PREFS;
import static nyc.c4q.finalpracticalretake.util.Constants.USER_KEY;

public class DogsActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private TextView dogTextView;
    private RecyclerView dogRecyclerView;
    private DogService dogService;
    private DogAdapter dogAdapter;
    private List<String> dogImageList = new ArrayList<>();
    private String breedName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        setViews();
        checkOrientation();
        breedNetworkingCall();
    }

    private void setViews(){
        dogTextView = findViewById(R.id.dog_textview);
        dogRecyclerView = findViewById(R.id.dog_recyclerview);
        intent = getIntent();
        dogService = Common.getBreed();
        breedName = intent.getStringExtra(BreedsActivity.BREED_NAME);
        preferences = getApplicationContext().getSharedPreferences(intent.getStringExtra("shared"), MODE_PRIVATE);
        dogTextView.setText(breedName);
    }

    private void checkOrientation() {
        if (DogsActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            dogRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            dogRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
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
                startActivity(new Intent(DogsActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void breedNetworkingCall() {
        dogService.getBreedImages(breedName).enqueue(new Callback<BreedImages>() {
            @Override
            public void onResponse(Call<BreedImages> call, Response<BreedImages> response) {
                dogImageList = response.body().getMessage();
                dogAdapter = new DogAdapter(dogImageList);
                dogRecyclerView.setAdapter(dogAdapter);
            }

            @Override
            public void onFailure(Call<BreedImages> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
