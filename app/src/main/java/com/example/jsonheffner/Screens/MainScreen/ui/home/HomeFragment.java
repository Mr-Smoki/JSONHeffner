package com.example.jsonheffner.Screens.MainScreen.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.jsonheffner.R;
import com.example.jsonheffner.Screens.MainScreen.MovieWithFlagFragment;
import com.example.jsonheffner.common.URLs;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Context context;
    LayoutInflater inflater;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCover(view);
        initMoviesFragments(view);
    }
    ViewPager2 fragmentMoviesWithFlag;
    private void initMoviesFragments(View view) {
        fragmentMoviesWithFlag = view.findViewById(R.id.fragmentMoviesWithFlag);
        fragmentMoviesWithFlag.setAdapter(new FragmentMovieAdapter(this));
        new TabLayoutMediator((TabLayout) view.findViewById(R.id.tabMovies),
                fragmentMoviesWithFlag, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                String flag;

                switch (position) {
                    case 0:
                        flag = "Новое";
                        break;
                    case 1:
                        flag="Тренды";
                        break;
                    default:
                        flag="Для вас";

                }
                tab.setText(flag);
            }
        }).attach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        context = inflater.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    ImageView previewPoster,fPreviewPoster;
    int coverMovieId;
    private void initCover(View view) {
        previewPoster = view.findViewById(R.id.previewPoster);
        fPreviewPoster = view.findViewById(R.id.fPreviewPoster);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest coverReq = new JsonObjectRequest(Request.Method.GET,
                URLs.getCOVER(),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String back = URLs.getImageUrl(response.getString("backgroundImage"));
                    String foreground = URLs.getImageUrl(response.getString("foregroundImage"));
                    coverMovieId = response.getInt("movieId");
                    Glide.with(context).load(back).into(previewPoster);
                    Glide.with(context).load(foreground).into(fPreviewPoster);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(coverReq);
    }

    private class FragmentMovieAdapter extends FragmentStateAdapter {

        public FragmentMovieAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            String flag;
            switch (position) {
                case 0:
                    flag = "new";
                    break;
                case 1:
                    flag="inTrend";
                    break;
                default:
                    flag="forMe";

            }

            return new MovieWithFlagFragment(flag);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}