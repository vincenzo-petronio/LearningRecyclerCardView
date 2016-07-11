package it.localhost.app.mobile.learningrecyclercardview.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import it.localhost.app.mobile.learningrecyclercardview.view.adapter.UsersAdapter;
import it.localhost.app.mobile.learningrecyclercardview.VolleySingleton;
import it.localhost.app.mobile.learningrecyclercardview.model.User;

public class RecyclerActivity extends AppCompatActivity {

    private List<User> users;
    private Context myCtx;
    private RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCtx = this;

        // VIEW
        setContentView(R.layout.activity_recycler);
        rvUsers = (RecyclerView) findViewById(R.id.rvUsers);

        // REST API
        RequestQueue req = VolleySingleton.getInstance(this).getRequestQueue();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        GsonRequest<User[]> gsonReq = new GsonRequest<>(Request.Method.GET,
                "http://jsonplaceholder.typicode.com/users",
                User[].class, params, headers, onSuccLstUsers(), onErrLstUsers());
        req.add(gsonReq);
    }

    private void setView() {
        // create and attach the adapter to the recyclerview
        UsersAdapter adapter = new UsersAdapter(users, myCtx);
        rvUsers.setAdapter(adapter);
        rvUsers.setLayoutManager(new LinearLayoutManager(myCtx));
    }

    private Response.Listener<User[]> onSuccLstUsers() {
        return new Response.Listener<User[]>() {
            @Override
            public void onResponse(User[] response) {
                users = Arrays.asList(response);
                Log.d("onResponse", "" + Integer.toString(users.size()));
                setView();
            }
        };
    }

    private Response.ErrorListener onErrLstUsers() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        };
    }
}