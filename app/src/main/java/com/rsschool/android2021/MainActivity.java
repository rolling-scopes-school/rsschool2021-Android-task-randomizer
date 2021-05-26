package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FirstFragment())
                    .setReorderingAllowed(true)
                    .commit();
        }
    }

    @Override
    public void moveToSecondFragment(int min, int max) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SecondFragment.newInstance(min, max))
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void popBackstack() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void setPreviousResult(int prevResult) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof FirstFragmentInterface) {
            FirstFragmentInterface firstFragmentInterface = (FirstFragmentInterface) fragment;
            firstFragmentInterface.setPreviousResult(prevResult);
        }
    }
}

