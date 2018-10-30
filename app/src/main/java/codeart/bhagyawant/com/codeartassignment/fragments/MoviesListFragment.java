package codeart.bhagyawant.com.codeartassignment.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import codeart.bhagyawant.com.codeartassignment.R;
import codeart.bhagyawant.com.codeartassignment.activities.MainActivity;
import codeart.bhagyawant.com.codeartassignment.adapter.MoviesAdapter;
import codeart.bhagyawant.com.codeartassignment.model.MovieResult;
import codeart.bhagyawant.com.codeartassignment.model.Result;
import codeart.bhagyawant.com.codeartassignment.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListFragment extends Fragment {
    private static final String TAG = "MoviesListFragment";
    @BindView(R.id.recycler_movie_list)
    RecyclerView recyclerMovieList;
    @BindView(R.id.text_connectivity_message)
    TextView textConnectivityMessage;
    private Call<MovieResult> mCallMovieList;

    public MoviesListFragment() {
    }

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);
        ButterKnife.bind(this, view);
        init();
        getActivity().setTitle(getString(R.string.upcoming_movies));
        return view;
    }

    private void init() {
        recyclerMovieList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCallMovieList = MainActivity.sApiService.getUpcomingMovies(Constants.API_KEY);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCallMovieList.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (textConnectivityMessage.isShown())
                    textConnectivityMessage.setVisibility(View.GONE);
                List<Result> movieList = response.body().getResults();
                recyclerMovieList.setAdapter(new MoviesAdapter(movieList, getActivity()));
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                textConnectivityMessage.setVisibility(View.VISIBLE);
                Log.d(TAG, "onFailure: " + t.toString());

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_movies, MyInfoFragment.newInstance());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
