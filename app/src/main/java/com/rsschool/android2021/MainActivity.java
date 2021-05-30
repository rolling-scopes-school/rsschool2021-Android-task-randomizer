package com.rsschool.android2021;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.FirstFragmentListener,
        SecondFragment.SecondFragmentListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment, "FIRST");
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment, "SECOND");
        transaction.commit();
    }


    @Override
    public void actionFirstFragment(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void onBackPressed() {
        back();
    }


    @Override
    public void back() {
        Fragment myFragment = getSupportFragmentManager().findFragmentByTag("FIRST");
        if (myFragment != null && myFragment.isVisible()) {
            super.onBackPressed();
        }
        myFragment = getSupportFragmentManager().findFragmentByTag("SECOND");
        if (myFragment != null && myFragment.isVisible()) {
            openFirstFragment(Integer.parseInt(((TextView) myFragment.getView().findViewById(R.id.result)).getText().toString()));
        }
    }
}
