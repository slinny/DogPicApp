package nyc.c4q.finalpracticalretake.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import nyc.c4q.finalpracticalretake.PhotoActivity;
import nyc.c4q.finalpracticalretake.R;

import static nyc.c4q.finalpracticalretake.util.Constants.URL;

/**
 * Created by C4Q on 5/20/18.
 */

public class DogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ImageView dogImage;
    private String url;

    public DogViewHolder(View itemView) {
        super(itemView);
        dogImage = itemView.findViewById(R.id.itemview_image);
        dogImage.setOnClickListener(this);
    }

    public void onBind(String str){
        Picasso.with(itemView.getContext()).load(str).into(dogImage);
        url = str;
    }

    @Override
    public void onClick(View view) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }
}
