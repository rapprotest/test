package murzin.andrey.testproject.network;


import io.reactivex.Single;
import murzin.andrey.testproject.data.model.Article;
import murzin.andrey.testproject.data.model.Category;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ModelApi {

    @GET("list.php")
    Single<Category> getCategory(@Query("category") String category);

    @GET("list.php")
    Single<Article> getArticle(@Query("article") String article);
}
