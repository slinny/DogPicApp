package nyc.c4q.finalpracticalretake.dogmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by C4Q on 5/20/18.
 */

public class BreedImages {
    @SerializedName("message")
    private List<String> message;
    @SerializedName("status")
    private String status;

    public List <String> getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
