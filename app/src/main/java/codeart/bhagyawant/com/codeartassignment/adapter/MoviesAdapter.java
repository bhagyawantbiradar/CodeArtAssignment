package codeart.bhagyawant.com.codeartassignment.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import codeart.bhagyawant.com.codeartassignment.R;
import codeart.bhagyawant.com.codeartassignment.activities.MainActivity;
import codeart.bhagyawant.com.codeartassignment.fragments.MovieDetailsFragment;
import codeart.bhagyawant.com.codeartassignment.model.Result;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private static final String TAG = "MoviesAdapter";
    private final List<Result> mMovieList;
    private final Activity mActivity;

    public MoviesAdapter(List<Result> mMovieList, Activity mActivity) {
        this.mMovieList = mMovieList;
        this.mActivity = mActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Result result = mMovieList.get(position);
        holder.textDate.setText(parseDate(result.getReleaseDate()));
        holder.textName.setText(result.getTitle());
        setMovieRating(holder, result);
        setMovieThumbnail(holder, result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(result);
            }
        });
    }

    private void addFragment(Result result) {
        Fragment fragment = MovieDetailsFragment.newInstance(result.getId(), result.getTitle());
        FragmentTransaction fragmentTransaction = ((MainActivity) mActivity).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_movies, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setMovieThumbnail(MyViewHolder holder, Result result) {
        Picasso.with(mActivity)
                .load("https://image.tmdb.org/t/p/w500" + result.getPosterPath())
                .centerCrop()
                .fit()
                .placeholder(R.drawable.ic_loading)
                .into(holder.imageThumbnail);
    }

    private void setMovieRating(MyViewHolder holder, Result result) {
        if (result.getAdult())
            holder.textRating.setText("(A)");
        else
            holder.textRating.setText("(U/A)");
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_date)
        TextView textDate;
        @BindView(R.id.text_rating)
        TextView textRating;
        @BindView(R.id.image_thumbnail)
        ImageView imageThumbnail;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String parseDate(String time) {
        Date date;
        String formattedDate = null;
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);

        try {
            date = inputFormat.parse(time);
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
}
