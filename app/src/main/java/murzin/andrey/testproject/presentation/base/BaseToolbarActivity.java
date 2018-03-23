package murzin.andrey.testproject.presentation.base;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.Stack;

import murzin.andrey.testproject.R;

public abstract class BaseToolbarActivity extends BaseActivity implements ToolbarManager {

    Stack<String> titleStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleStack = new Stack<>();
    }

    @Override
    public void changeTitle(String title){
        titleStack.push(title);
        setTitle(title);
    }

    private void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        if (titleStack.size() > 1) {
            titleStack.pop();
            setTitle(titleStack.peek());
        }
    }

    protected void setToolbarWithDrawerToggle(Toolbar toolbar, DrawerLayout drawerLayout){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = setupDrawerToggle(toolbar, drawerLayout);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }



    private ActionBarDrawerToggle setupDrawerToggle(Toolbar toolbar, DrawerLayout drawerLayout) {

        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
    }
}
