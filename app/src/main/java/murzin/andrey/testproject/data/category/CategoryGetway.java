package murzin.andrey.testproject.data.category;

import io.reactivex.Single;
import murzin.andrey.testproject.data.model.Category;

public interface CategoryGetway {
    Single<Category> getCategory(String category);
}
