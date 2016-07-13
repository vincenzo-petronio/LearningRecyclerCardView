package it.localhost.app.mobile.learningrecyclercardview.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.localhost.app.mobile.learningrecyclercardview.R;
import it.localhost.app.mobile.learningrecyclercardview.model.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private List<Photo> photos;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public ImageView ivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
        }
    }

    /**
     * @param photos
     * @param context
     */
    public PhotosAdapter(List<Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View photoView = inflater.inflate(R.layout.activity_recycler_photo_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotosAdapter.ViewHolder holder, int position) {
        Photo photo = photos.get(position);

        //GUARD-CLAUSE
        if (photo == null) {
            return;
        }

        holder.tvTitle.setText(photo.getTitle());
        Log.v("PhotosAdapter: ", photo.getThumbnailUrl().toString());


        Picasso.with(context)
                //.load(Uri.parse(photo.getThumbnailUrl()))
                // FIXME Picasso bug! https://github.com/square/picasso/issues/463
                .load(photo.getThumbnailUrl())
                .error(R.drawable.information)
                //.tag(context)
                //.placeholder(R.drawable.information)
                //.resize(120, 60)
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.size() : 0;
    }
}
