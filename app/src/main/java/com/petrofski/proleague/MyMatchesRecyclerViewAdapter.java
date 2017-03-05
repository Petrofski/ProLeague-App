package com.petrofski.proleague;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.petrofski.proleague.anim.AnimationUtils;
import com.petrofski.proleague.model.Match;
import com.petrofski.proleague.model.Team;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyMatchesRecyclerViewAdapter extends RecyclerView.Adapter<MyMatchesRecyclerViewAdapter.MatchesViewHolder> {

    private final List<Match> mMatches;
    private FrameLayout container;

    private int previousPosition = 0;

    public MyMatchesRecyclerViewAdapter(List<Match> matches) {
        mMatches = matches;
    }

    @Override
    public MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_matches_list_layout, parent, false);
        return new MatchesViewHolder(view);
    }

    public List<Match> getmMatches() {
        return mMatches;
    }

    @Override
    public void onBindViewHolder(final MatchesViewHolder holder, int index) {
        holder.mMatch = mMatches.get(index);
        holder.mScoreView.setText(holder.mMatch.getHomeGoals() + ":" + holder.mMatch.getAwayGoals());
        holder.mDateView.setText("" + getFormattedDate(holder.mMatch.getStart()));
        if(holder.mMatch.getCurrentState() == 0) {
            holder.mScoreView.setText(getFormattedTime(holder.mMatch.getStart()));
        }
        if(holder.mMatch.getCurrentState() == 9) {
            holder.mDateView.setText("" + getFormattedDate(holder.mMatch.getStart()) + " - END");
        }
        if(holder.mMatch.getCurrentState() > 0) {

        }
        holder.mHomeFlagView.setImageResource(Team.flags.get(holder.mMatch.getHomeTeamId()));
        holder.mHomeTeamView.setText(holder.mMatch.getHomeTeam());
        holder.mAwayFlagView.setImageResource(Team.flags.get(holder.mMatch.getAwayTeamId()));
        holder.mAwayTeamView.setText(holder.mMatch.getAwayTeam());

        if(index > previousPosition) {
            AnimationUtils.animate(holder, true);
        } else {
            AnimationUtils.animate(holder, false);
        }

        previousPosition = index;

    }

    public String getFormattedDate(long date) {
        Date currentDate = new Date(date);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(currentDate);
    }

    public String getFormattedTime(long date) {
        Date currentDate = new Date(date);
        DateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(currentDate);
    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mDateView;
        public final TextView mHomeTeamView;
        public final ImageView mHomeFlagView;
        public final TextView mScoreView;
        public final TextView mAwayTeamView;
        public final ImageView mAwayFlagView;

        public Match mMatch;

        public MatchesViewHolder(View view) {
            super(view);
            mView = view;
            mDateView = (TextView) view.findViewById(R.id.date);
            mHomeTeamView = (TextView) view.findViewById(R.id.home_team);
            mHomeFlagView = (ImageView) view.findViewById(R.id.logo_team_home);
            mScoreView = (TextView) view.findViewById(R.id.score);
            mAwayTeamView = (TextView) view.findViewById(R.id.away_team);
            mAwayFlagView = (ImageView) view.findViewById(R.id.logo_team_away);
        }

        @Override
        public String toString() {
            return super.toString() + ": " + mScoreView.getText();
        }
    }
}
