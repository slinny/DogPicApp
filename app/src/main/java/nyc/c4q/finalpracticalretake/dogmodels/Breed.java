package nyc.c4q.finalpracticalretake.dogmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by C4Q on 5/20/18.
 */

public class Breed {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

}
