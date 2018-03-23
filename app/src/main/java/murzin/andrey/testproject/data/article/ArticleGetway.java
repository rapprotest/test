package murzin.andrey.testproject.data.article;


import io.reactivex.Single;
import murzin.andrey.testproject.data.model.Article;

public interface ArticleGetway {

    Single<Article> getArticle(String article);
}
