package com.growlspace.growlspace;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.view.MaterialListView;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Nicholas on 4/25/2015.
 */
public class FeedFragment extends Fragment {
    private MaterialListView mListView;

    public FeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mListView = (MaterialListView) rootView.findViewById(R.id.material_listview);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.attachToRecyclerView(mListView);
        return rootView;
    }
}
