package com.petrofski.proleague.model;

import com.petrofski.proleague.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by georgespetrofski on 24/11/15.
 */
public class Team {

    /*"goalsAway": 12,
            "shirtUrl": "https://static.crowdscores.com/kits/default.svg",
            "draws": 6,
            "name": "Gent",
            "pointsAdjustments": null,
            "wins": 9,
            "leagueTableClass": "top1",
            "flagUrl": "https://static.crowdscores.com/flags/default.png",
            "isNational": false,
            "badgeUrl": "https://static.crowdscores.com/badges/default.svg",
            "points": 33,
            "dbid": 1258,
            "goalsAgainst": 15,
            "losses": 1,
            "goalDiff": 17,
            "position": 1,
            "shortName": "Gent",
            "gamesPlayed": 16,
            "shortCode": "GEN",
            "goalsFor": 32*/

    public static Map<Integer, Integer> flags = new HashMap<Integer, Integer>() {{
        put(446, R.drawable.dbid446);
        put(473, R.drawable.dbid473);
        put(494, R.drawable.dbid494);
        put(506, R.drawable.dbid506);
        put(974, R.drawable.dbid974);
        put(1033, R.drawable.dbid1033);
        put(1212, R.drawable.dbid1212);
        put(1254, R.drawable.dbid1254);
        put(1258, R.drawable.dbid1258);
        put(1260, R.drawable.dbid1260);
        put(1263, R.drawable.dbid1263);
        put(1264, R.drawable.dbid1264);
        put(1267, R.drawable.dbid1267);
        put(1269, R.drawable.dbid1269);
        put(1271, R.drawable.dbid1271);
        put(2566, R.drawable.dbid2566);
    }};

    private int goalsAway;
    private String shirtUrl;
    private int draws;
    private String name;
    private int pointsAdjustments;
    private int wins;
    private String leagueTableClass;
    private String flagUrl;
    private boolean isNational;
    private String badgeUrl;
    private int points;
    private int dbid;
    private int goalsAgainst;
    private int losses;
    private int goalDiff;
    private int position;
    private String shortName;
    private int gamesPlayed;
    private String shortCode;
    private int goalsFor;

    public Team() {

    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }

    public String getShirtUrl() {
        return shirtUrl;
    }

    public void setShirtUrl(String shirtUrl) {
        this.shirtUrl = shirtUrl;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPointsAdjustments() {
        return pointsAdjustments;
    }

    public void setPointsAdjustments(int pointsAdjustments) {
        this.pointsAdjustments = pointsAdjustments;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getLeagueTableClass() {
        return leagueTableClass;
    }

    public void setLeagueTableClass(String leagueTableClass) {
        this.leagueTableClass = leagueTableClass;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public boolean isNational() {
        return isNational;
    }

    public void setIsNational(boolean isNational) {
        this.isNational = isNational;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGoalDiff() {
        return goalDiff;
    }

    public void setGoalDiff(int goalDiff) {
        this.goalDiff = goalDiff;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }
}
