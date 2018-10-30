package codeart.bhagyawant.com.codeartassignment.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import codeart.bhagyawant.com.codeartassignment.ApiClient;
import codeart.bhagyawant.com.codeartassignment.R;
import codeart.bhagyawant.com.codeartassignment.fragments.MoviesListFragment;
import codeart.bhagyawant.com.codeartassignment.interfaces.ApiInterface;

public class MainActivity extends AppCompatActivity {
    public static ApiInterface sApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addFirstFragment();
    }

    private void addFirstFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_movies, MoviesListFragment.newInstance());
        fragmentTransaction.commit();
    }

    private void init() {
        sApiService = ApiClient.getClient().create(ApiInterface.class);
    }

}
