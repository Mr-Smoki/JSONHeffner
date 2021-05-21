package com.example.jsonheffner.Screens.MainScreen;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.jsonheffner.R;
import com.example.jsonheffner.common.Entity.MovieItem;
import com.example.jsonheffner.common.Entity.User;
import com.example.jsonheffner.common.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MovieWithFlagFragment extends Fragment {

    String title,flag;

    public MovieWithFlagFragment( String flag) {

        this.flag = flag;
    }
    Context context;
    LayoutInflater inflater;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        context = inflater.getContext();
        return inflater.inflate(R.layout.fragment_movie_with_flag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPosters(view);
    }
    RecyclerView postersView;
    private void initPosters(View view) {
        postersView = view.findViewById(R.id.postersView);
        RequestQueue requestQueue = Volley.newRequestQueue((context));
        JsonArrayRequest arrayRequest =  new JsonArrayRequest(URLs.getMoviesWithFilter(flag), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                initMoviesList(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> authMap = new HashMap<>();
                authMap.put("Authorization","Bearer "+ User.getCurrentUser().getToken());
                return authMap;
            }
        };
        requestQueue.add(arrayRequest);
    }
    List<MovieItem> movieItems = new ArrayList<>();
    private void initMoviesList(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            try {
                movieItems.add(new MovieItem((JSONObject) response.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        postersView.setAdapter(new PosterAdapter());
    }


    private class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_movie_poster,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MovieItem movieItem = movieItems.get(position);
            holder.titleView.setText(movieItem.getName());
            Glide.with(context).load(URLs.getImageUrl(movieItem.getPoster())).into(holder.posterView);
        }

        @Override
        public int getItemCount() {
            return movieItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final TextView titleView;
            final ImageView posterView;
            public ViewHolder(@NonNull View itemView) {

                super(itemView);
                titleView = itemView.findViewById(R.id.titleView);
                posterView = itemView.findViewById(R.id.postersView);
            }
        }
    }
}