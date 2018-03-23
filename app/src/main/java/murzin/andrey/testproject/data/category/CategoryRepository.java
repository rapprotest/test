package murzin.andrey.testproject.data.category;

import javax.inject.Inject;

import io.reactivex.Single;
import murzin.andrey.testproject.data.model.Category;
import murzin.andrey.testproject.network.ModelApi;

public class CategoryRepository implements CategoryGetway {

    private ModelApi modelApi;


    public CategoryRepository(ModelApi modelApi) {
        this.modelApi = modelApi;
    }

    @Override
    public Single<Category> getCategory(String category) {
        return modelApi.getCategory(category);
    }
}
