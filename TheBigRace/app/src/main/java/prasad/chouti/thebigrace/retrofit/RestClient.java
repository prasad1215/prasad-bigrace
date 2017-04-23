

package prasad.chouti.thebigrace.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class RestClient {
    private final Retrofit retrofitClient;


    public RestClient() {
        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.connectTimeout(30, TimeUnit.SECONDS);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClient.interceptors().add(httpLoggingInterceptor);


        Gson gson = new GsonBuilder().setLenient().create();
        retrofitClient = new Retrofit.Builder()
                .baseUrl("http://849fairmount.com")
                .client(okClient.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    private <T> T create(Class<T> apiInterfaceClass) {
        if (retrofitClient == null) {
            new RestClient();
        }
        return retrofitClient.create(apiInterfaceClass);
    }

    public RestApi createNewApi() {
        return create(RestApi.class);
    }


}
