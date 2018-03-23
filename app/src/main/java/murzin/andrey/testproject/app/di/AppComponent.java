package murzin.andrey.testproject.app.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import murzin.andrey.testproject.app.App;
import murzin.andrey.testproject.navigation.NavigationModule;


@Singleton
@Component(modules = {
        AppModule.class,
        NavigationModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBinderModule.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder withContext(Context context);

        AppComponent build();
    }
}
