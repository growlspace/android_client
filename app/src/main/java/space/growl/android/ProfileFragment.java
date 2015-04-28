package space.growl.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import space.growl.android.entity.User;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences prefs = rootView.getContext().getSharedPreferences(HomeActivity.PREFS_NAME, 0);
        TextView textView = (TextView) rootView.findViewById(R.id.profile_text);
        User profileUser = HomeActivity.getUserFromPreferences(prefs);
        textView.setText(profileUser.toString());
        return rootView;
    }

}
