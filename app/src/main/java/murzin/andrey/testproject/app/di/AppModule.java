package murzin.andrey.testproject.app.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import murzin.andrey.testproject.BuildConfig;
import murzin.andrey.testproject.network.ModelApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class AppModule {

    private static final String BASE_URL = "http://mikonatoruri.win/";

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                // TODO: 28.02.2018 добавить обработку ошибок вместо RxJava2CallAdapterFactory
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(new AuthorizeInterceptor(sessionManager))
                .addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BODY)
                        .log(Log.INFO)
                        .request("Request")
                        .response("Response")
                        .build())
                .build();
    }

    @Provides
    @Singleton
    ModelApi provideNeuronApi(Retrofit.Builder builder) {
        return builder
                .baseUrl(BASE_URL)
                .build()
                .create(ModelApi.class);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences("modeles-pref", Context.MODE_PRIVATE);
    }

}
