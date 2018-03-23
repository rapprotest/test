package murzin.andrey.testproject.app.di;



import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import murzin.andrey.testproject.app.di.scope.ActivityScope;
import murzin.andrey.testproject.presentation.main.MainActivity;
import murzin.andrey.testproject.presentation.main.di.MainModule;

@Module
interface ActivityBinderModule {


    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    MainActivity mainActivityInjector();
}
