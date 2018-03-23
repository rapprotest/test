package murzin.andrey.testproject.navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;

import murzin.andrey.testproject.presentation.article.FragmentArticle;
import murzin.andrey.testproject.presentation.category.FragmentCategory;
import murzin.andrey.testproject.presentation.main.MainActivity;
import ru.terrakok.cicerone.android.SupportAppNavigator;

public class MainActivityNavigation extends SupportAppNavigator {

    private MainActivity activity;

    public MainActivityNavigation(MainActivity activity, int containerId) {
        super(activity, containerId);
        this.activity = activity;
    }

    @Override
    protected Intent createActivityIntent(String screenKey, Object data) {
        return null;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
            case Screen.BASKETBALL_SCREEN:
                return FragmentCategory.newInstance("basketball");
            case Screen.FOOTBAL_SCREEN:
                return FragmentCategory.newInstance("football");
            case Screen.HOCKEY_SCREEN:
                return FragmentCategory.newInstance("hockey");
            case Screen.VOLLEYBALL_SCREEN:
                return FragmentCategory.newInstance("volleyball");
            case Screen.CYBER_SPORT_SCREEN:
                return FragmentCategory.newInstance("cybersport");
            case Screen.TENNIS_SCREEN:
                return FragmentCategory.newInstance("tennis");
            case Screen.ARTICLE_SCREEN:
                if (data instanceof String) {
                    String article = (String) data;
                    return FragmentArticle.newInstance(article);
                } else {
                    return FragmentArticle.newInstance(null);
                }
        }
        return null;
    }
}
