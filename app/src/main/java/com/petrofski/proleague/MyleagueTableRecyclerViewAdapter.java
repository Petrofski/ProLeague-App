package com.petrofski.proleague;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.petrofski.proleague.LeagueTableFragment.OnListFragmentInteractionListener;
import com.petrofski.proleague.anim.AnimationUtils;
import com.petrofski.proleague.model.Team;

import java.util.List;

public class MyleagueTableRecyclerViewAdapter extends RecyclerView.Adapter<MyleagueTableRecyclerViewAdapter.ViewHolder> {

    private final List<Team> mTeams;
    private final OnListFragmentInteractionListener mListener;
    private FrameLayout container;

    private int previousPosition = 0;

    public MyleagueTableRecyclerViewAdapter(List<Team> teams, OnListFragmentInteractionListener listener) {
        mTeams = teams;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_leaguetable_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int index) {
        holder.mTeam = mTeams.get(index);
        holder.mPositionView.setText("" + holder.mTeam.getPosition());
        holder.mFlagView.setImageResource(Team.flags.get(holder.mTeam.getDbid()));
        holder.mTeamNameView.setText(holder.mTeam.getName());
        holder.mTeamPoints.setText("" + holder.mTeam.getPoints());
        holder.mTeamGamesPlayed.setText("" + holder.mTeam.getGamesPlayed());
        holder.mTeamGoals.setText(holder.mTeam.getGoalsFor() + ":" + holder.mTeam.getGoalsAgainst());
        holder.mTeamGoalsDifference.setText("" + (holder.mTeam.getGoalsFor() - holder.mTeam.getGoalsAgainst()));

        if(index > previousPosition) {
            AnimationUtils.animate(holder, true);
        } else {
            AnimationUtils.animate(holder, false);
        }

        previousPosition = index;

    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mPositionView;
        public final ImageView mFlagView;
        public final TextView mTeamNameView;
        public final TextView mTeamPoints;
        public final TextView mTeamGamesPlayed;
        public final TextView mTeamGoals;
        public final TextView mTeamGoalsDifference;

        public Team mTeam;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPositionView = (TextView) view.findViewById(R.id.teamPosition);
            mFlagView = (ImageView) view.findViewById(R.id.flag);
            mTeamNameView = (TextView) view.findViewById(R.id.teamName);
            mTeamPoints = (TextView) view.findViewById(R.id.teamPoints);
            mTeamGamesPlayed = (TextView) view.findViewById(R.id.teamGamesPlayed);
            mTeamGoals = (TextView) view.findViewById(R.id.teamGoals);
            mTeamGoalsDifference = (TextView) view.findViewById(R.id.teamGoalsDifference);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPositionView.getText() + "'" + mTeamNameView.getText() + "'";
        }
    }
}
