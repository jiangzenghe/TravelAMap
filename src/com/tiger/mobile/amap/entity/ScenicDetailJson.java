package com.tiger.mobile.amap.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2015/5/2.
 */
public class ScenicDetailJson extends ScenicAreaJson {
    private String mapSize;
    private ArrayList<Recommend> recommendScenicList;
    private ArrayList<Recommend> souvenirList; // ---纪念品列表
    private ArrayList<Recommend> gameList;

    public String getMapSize() {
        return mapSize;
    }

    public void setMapSize(String mapSize) {
        this.mapSize = mapSize;
    }

    public ArrayList<Recommend> getRecommendScenicList() {
        return recommendScenicList;
    }

    public void setRecommendScenicList(ArrayList<Recommend> recommendScenicList) {
        this.recommendScenicList = recommendScenicList;
    }

    public List<Recommend> getSouvenirList() {
        return souvenirList;
    }

    public void setSouvenirList(ArrayList<Recommend> souvenirList) {
        this.souvenirList = souvenirList;
    }

    public List<Recommend> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Recommend> gameList) {
        this.gameList = gameList;
    }
}
