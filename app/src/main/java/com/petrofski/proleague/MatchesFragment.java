package com.petrofski.proleague;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.petrofski.proleague.model.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Firebase leagueTableRef;
    private RecyclerView recyclerView;

    public MatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaguetable_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            this.recyclerView = recyclerView;
            fillMatches();
        }
        return view;
    }

    private void fillMatches() {
        leagueTableRef = new Firebase("https://foot-app.firebaseio.com/matches");

        leagueTableRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int pos = -1;
                int foundPos = 0;
                boolean isFirst = false;
                List<Match> matches = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    try {
                        Match match = postSnapshot.getValue(Match.class);
                        matches.add(match);
                        if(!isFirst && match.isResult() == false) {
                            foundPos = pos;
                            isFirst = true;
                        }
                        pos++;
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setAdapter(new MyMatchesRecyclerViewAdapter(matches));
                recyclerView.scrollToPosition(foundPos);
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }

        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Match match);
    }
}
