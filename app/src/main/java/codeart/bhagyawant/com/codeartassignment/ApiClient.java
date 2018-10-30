package codeart.bhagyawant.com.codeartassignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit sRetrofit = null;


    public static Retrofit getClient() {
        if (sRetrofit ==null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
