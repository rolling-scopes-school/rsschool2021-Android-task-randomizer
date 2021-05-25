package com.rsschool.android2021;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnGenerateClicked,
        SecondFragment.OnBackButtonClick {
    private static final String SECOND_FRAGMENT_TAG = "secondFragment";
    private static final String FIRST_FRAGMENT_TAG = "firstFragment";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if ( savedInstanceState == null ) {
            openFirstFragment(0);
        }
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, firstFragment, FIRST_FRAGMENT_TAG)
                .addToBackStack(FIRST_FRAGMENT_TAG)
                .commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.container, secondFragment, SECOND_FRAGMENT_TAG)
                .addToBackStack(SECOND_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void onGenerateClicked(int min, int max) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                openSecondFragment(min, max);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment secondFragment = getSupportFragmentManager().findFragmentByTag(SECOND_FRAGMENT_TAG);

        if ( secondFragment.isAdded() ) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    //TODO: make replace not backstack or recreate fragments

    @Override
    public void onBackButtonClick(int result) {
        FirstFragment firstFragment = (FirstFragment) getSupportFragmentManager()
                .findFragmentByTag(FIRST_FRAGMENT_TAG);

        firstFragment.updatePreviousResult(result);
        getSupportFragmentManager().popBackStack();
    }
}
