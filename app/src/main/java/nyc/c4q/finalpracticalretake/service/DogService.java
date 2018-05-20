package nyc.c4q.finalpracticalretake.service;

import nyc.c4q.finalpracticalretake.dogmodels.Breed;
import nyc.c4q.finalpracticalretake.dogmodels.BreedImages;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by C4Q on 5/20/18.
 */

public interface DogService {
    @GET("/api/breed/{breedName}/images/random")
    Call<Breed> getBreed(@Path("breedName") String name);

    @GET("/api/breed/{breedName}/images")
    Call<BreedImages> getBreedImages(@Path("breedName") String name);
}
