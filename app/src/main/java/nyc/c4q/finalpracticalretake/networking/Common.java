package nyc.c4q.finalpracticalretake.networking;

import nyc.c4q.finalpracticalretake.service.DogService;

/**
 * Created by C4Q on 5/20/18.
 */

public class Common {

    private static final String BASE_URL = "https://dog.ceo";

    public static DogService getBreed() {
        return RetrofitClient.getRetrofitClient(BASE_URL).create(DogService.class);
    }
}
