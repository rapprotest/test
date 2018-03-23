package murzin.andrey.testproject.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import murzin.andrey.testproject.R;
import murzin.andrey.testproject.app.Layout;
import murzin.andrey.testproject.data.category.CategoryGetway;
import murzin.andrey.testproject.navigation.Screen;
import murzin.andrey.testproject.presentation.base.BaseToolbarActivity;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;

@Layout(R.layout.activity_main)
public class MainActivity extends BaseToolbarActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    SupportAppNavigator navigator;
    @Inject
    Router router;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarWithDrawerToggle(toolbar, drawerLayout);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) router.newRootScreen(Screen.FOOTBAL_SCREEN);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_basketball:
                router.navigateTo(Screen.BASKETBALL_SCREEN);
                break;
            case R.id.nav_cybersport:
                router.navigateTo(Screen.CYBER_SPORT_SCREEN);
                break;
            case R.id.nav_football:
                router.navigateTo(Screen.FOOTBAL_SCREEN);
                break;
            case R.id.nav_hockey:
                router.navigateTo(Screen.HOCKEY_SCREEN);
                break;
            case R.id.nav_tennis:
                router.navigateTo(Screen.TENNIS_SCREEN);
                break;
            case R.id.nav_volleyball:
                router.navigateTo(Screen.VOLLEYBALL_SCREEN);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
