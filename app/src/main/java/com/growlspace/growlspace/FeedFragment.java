package com.growlspace.growlspace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.cards.WelcomeCard;
import com.dexafree.materialList.view.MaterialListView;
import com.google.gson.JsonObject;
import com.growlspace.growlspace.entity.Post;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Nicholas on 4/25/2015.
 */
public class FeedFragment extends Fragment {
    private static final String LOG_TAG = "FeedFragment";
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), ComposePostActivity.class), 1);
            }
        });
        populateList();
        fab.attachToRecyclerView(mListView);
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String audioFilePath = data.getStringExtra(Post.AUDIO_FILE_PATH);
                String caption = data.getStringExtra(Post.CAPTION);

                Post post = new Post(audioFilePath, caption);

                Ion.with(this)
                        .load("https://koush.clockworkmod.com/test/echo")
                        .setJsonPojoBody(post)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (e != null) {
                                    Log.e(LOG_TAG, e.toString());
                                }
                                Log.d(LOG_TAG, result.toString());
                                Toast.makeText(getActivity(), "File uploaded successfully.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }

    private void populateList() {
        BigImageCard card;
        Context context = getActivity().getApplicationContext();

        WelcomeCard welcomeCard = new WelcomeCard(context);
        welcomeCard.setTitle("Welcome to GrowlSpace!");
        welcomeCard.setSubtitle("The Sound Social Network");
        welcomeCard.setDescription("Touch the floating action button below to create your first growl, or just scroll your feed.");
        welcomeCard.setButtonText("Okay");
        welcomeCard.setBackgroundColor(getResources().getColor(R.color.primary));
        welcomeCard.setDescriptionColor(getResources().getColor(R.color.md_white_1000));
        welcomeCard.setDismissible(true);
        mListView.add(welcomeCard);

        for (int i = 1; i < 11; i++) {
            card = new BigImageCard(context);
            card.setDescription("Hello, for the " + i + " time.");
            card.setTitle("Greeting #" + i);
            mListView.add(card);
        }
    }
}
