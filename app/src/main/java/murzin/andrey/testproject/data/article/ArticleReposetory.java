package murzin.andrey.testproject.data.article;


import javax.inject.Inject;

import io.reactivex.Single;
import murzin.andrey.testproject.data.model.Article;
import murzin.andrey.testproject.network.ModelApi;

public class ArticleReposetory implements ArticleGetway {

    private ModelApi modelApi;

    @Inject
    public ArticleReposetory(ModelApi modelApi) {
        this.modelApi = modelApi;
    }

    @Override
    public Single<Article> getArticle(String article) {
        return modelApi.getArticle(article);
    }
}
