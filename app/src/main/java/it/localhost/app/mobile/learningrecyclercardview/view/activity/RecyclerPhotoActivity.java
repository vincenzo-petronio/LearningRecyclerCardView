package it.localhost.app.mobile.learningrecyclercardview.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.localhost.app.mobile.learningrecyclercardview.GsonRequest;
import it.localhost.app.mobile.learningrecyclercardview.R;
import it.localhost.app.mobile.learningrecyclercardview.VolleySingleton;
import it.localhost.app.mobile.learningrecyclercardview.model.Photo;
import it.localhost.app.mobile.learningrecyclercardview.view.adapter.PhotosAdapter;

public class RecyclerPhotoActivity extends AppCompatActivity {

    private List<Photo> photos;
    private Context myCtx;
    private RecyclerView rvPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCtx = this;

        // VIEW
        setContentView(R.layout.activity_recycler);
        rvPhotos = (RecyclerView) findViewById(R.id.rvItems);

        // REST API
        RequestQueue req = VolleySingleton.getInstance(this).getRequestQueue();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        GsonRequest<Photo[]> gsonReq = new GsonRequest<>(Request.Method.GET,
                "http://jsonplaceholder.typicode.com/photos",
                Photo[].class, params, headers, onSuccLstPhotos(), onErrLstPhotos());
        req.add(gsonReq);
    }

    private void setView() {
        PhotosAdapter adapter = new PhotosAdapter(photos, myCtx);
        rvPhotos.setAdapter(adapter);
        // use a built-in layout managers
        rvPhotos.setLayoutManager(new GridLayoutManager(myCtx, 2));
    }

    private Response.Listener<Photo[]> onSuccLstPhotos() {
        return new Response.Listener<Photo[]>() {
            @Override
            public void onResponse(Photo[] response) {
                photos = Arrays.asList(response);
                Log.d("onResponse", "" + Integer.toString(photos.size()));
                setView();
            }
        };
    }

    private Response.ErrorListener onErrLstPhotos() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        };
    }


}
