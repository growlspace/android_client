package space.growl.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dexafree.materialList.cards.BasicButtonsCard;
import com.dexafree.materialList.cards.OnButtonPressListener;
import com.dexafree.materialList.cards.WelcomeCard;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.view.MaterialListView;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import space.growl.android.entity.Post;
import space.growl.android.media.AudioPlayer;
import space.growl.android.media.AudioRecorder;

/**
 * Created by Nicholas on 4/25/2015.
 */
public class FeedFragment extends Fragment {
    private static final String LOG_TAG = "FeedFragment";
    private MaterialListView mListView;

    private AudioPlayer mAudioPlayer = new AudioPlayer();

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
                String audioFilePath = data.getStringExtra(Post.AUDIO_FILE_PATH);
                String caption = data.getStringExtra(Post.CAPTION);

                Post post = new Post(audioFilePath, caption);

                Ion.with(this)
                        .load("https://koush.clockworkmod.com/test/echo")
                        .setLogging(LOG_TAG, Log.DEBUG)
                        .progressBar(progressBar)
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
        BasicButtonsCard card;
        Context context = mListView.getContext();

        WelcomeCard welcomeCard = new WelcomeCard(context);
        welcomeCard.setTitle("Welcome to gr(_)wl");
        welcomeCard.setSubtitle("pronounced \"growl space\"");
        welcomeCard.setDescription("Touch the floating action button below to create your first growl, or just scroll your feed.");
        welcomeCard.setButtonText("SWIPE TO DISMISS");
        welcomeCard.setBackgroundColor(getResources().getColor(R.color.primary));
        welcomeCard.setDescriptionColor(getResources().getColor(R.color.md_white_1000));
        welcomeCard.setDismissible(true);
        welcomeCard.setOnButtonPressedListener(new OnButtonPressListener() {
            @Override
            public void onButtonPressedListener(View view, Card card) {
                Log.d(LOG_TAG, "This is where I would dismiss the welcome card... IF I COULD");
            }
        });
        mListView.add(welcomeCard);

        ArrayList<Post> posts = new ArrayList<>(); // gotta fill that with Ion

        for (Post post : posts) {
            card = CardFactory.PostToCard(post, context);
            // LEFT
            card.setOnLeftButtonPressedListener(new OnButtonPressListener() {
                @Override
                public void onButtonPressedListener(View view, Card card) {
                    // send a server request to like things
                    ((BasicButtonsCard) card).setLeftButtonTextColor(R.color.primary_dark);
                }
            });

            // RIGHT
            card.setOnRightButtonPressedListener(new AudioButtonListener(post.getAudioPath()));
            mListView.add(card);
        }
    }

    private class AudioButtonListener implements OnButtonPressListener {
        String audioPath;

        private AudioButtonListener(String audioPath) {
            this.audioPath = audioPath;
        }

        @Override
        public void onButtonPressedListener(View view, Card card) {
            mAudioPlayer.startPlaying(audioPath);
        }
    }
}
