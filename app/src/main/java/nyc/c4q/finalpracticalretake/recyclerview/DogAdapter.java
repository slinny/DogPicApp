package nyc.c4q.finalpracticalretake.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.finalpracticalretake.R;

/**
 * Created by C4Q on 5/20/18.
 */

public class DogAdapter extends RecyclerView.Adapter<DogViewHolder> {

    private List<String> dogImageList;

    public DogAdapter(List <String> dogImageList) {
        this.dogImageList = dogImageList;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_layout,parent,false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.onBind(dogImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return dogImageList.size();
    }
}
