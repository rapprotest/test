package murzin.andrey.testproject.presentation.article.di;


import dagger.Module;
import dagger.Provides;
import murzin.andrey.testproject.app.di.scope.FragmentScope;
import murzin.andrey.testproject.data.article.ArticleGetway;
import murzin.andrey.testproject.data.article.ArticleReposetory;
import murzin.andrey.testproject.data.category.CategoryGetway;
import murzin.andrey.testproject.data.category.CategoryRepository;
import murzin.andrey.testproject.network.ModelApi;

@Module
public class ArticleModule {

    @Provides
    @FragmentScope
    static ArticleGetway provideCategoryGetway(ModelApi modelApi) {
        return new ArticleReposetory(modelApi);
    }
}
