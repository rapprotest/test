package murzin.andrey.testproject.presentation.main.di;


import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import murzin.andrey.testproject.R;
import murzin.andrey.testproject.app.di.scope.ActivityScope;
import murzin.andrey.testproject.app.di.scope.FragmentScope;
import murzin.andrey.testproject.data.category.CategoryGetway;
import murzin.andrey.testproject.data.category.CategoryRepository;
import murzin.andrey.testproject.data.model.Category;
import murzin.andrey.testproject.delegat.ToolbarTitleDelegate;
import murzin.andrey.testproject.navigation.MainActivityNavigation;
import murzin.andrey.testproject.network.ModelApi;
import murzin.andrey.testproject.presentation.article.FragmentArticle;
import murzin.andrey.testproject.presentation.article.di.ArticleModule;
import murzin.andrey.testproject.presentation.category.FragmentCategory;
import murzin.andrey.testproject.presentation.category.di.CategoryModule;
import murzin.andrey.testproject.presentation.main.MainActivity;
import ru.terrakok.cicerone.android.SupportAppNavigator;

@Module
public abstract class MainModule {
    @Provides
    @ActivityScope
    static ToolbarTitleDelegate provideTitleDelegate(MainActivity activity) {
        return new ToolbarTitleDelegate(activity);
    }

    @Provides
    @ActivityScope
    static SupportAppNavigator provideMainActivityNavigation(MainActivity activity) {
        return new MainActivityNavigation(activity, R.id.container);
    }


    @FragmentScope
    @ContributesAndroidInjector(modules = CategoryModule.class)
    abstract FragmentCategory categoryFramgnetInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ArticleModule.class)
    abstract FragmentArticle articleFragmentInjector();
}
