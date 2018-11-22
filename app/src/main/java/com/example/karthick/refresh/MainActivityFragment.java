package com.example.karthick.refresh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View roundsListView = inflater.inflate(R.layout.fragment_game, container, false);

        String[] roundScores = {"20", "30", "80", "10", "D", "45"};
        createColumns();

        return roundsListView;
    }

    private void createColumns(){

    }
}
