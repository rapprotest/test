package murzin.andrey.testproject.presentation.category.di;

import dagger.Module;
import dagger.Provides;
import murzin.andrey.testproject.app.di.scope.FragmentScope;
import murzin.andrey.testproject.data.category.CategoryGetway;
import murzin.andrey.testproject.data.category.CategoryRepository;
import murzin.andrey.testproject.network.ModelApi;

@Module
public class CategoryModule {

    @Provides
    @FragmentScope
    static CategoryGetway provideCategoryGetway(ModelApi modelApi) {
        return new CategoryRepository(modelApi);
    }

}
