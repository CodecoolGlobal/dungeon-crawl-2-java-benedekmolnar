package com.codecool.dungeoncrawl.model;

import java.util.HashMap;
import java.util.Map;

public class InventoryModel extends BaseModel {
    private int arrow;
    private int key;

    public InventoryModel(int arrow, int key) {
        this.arrow = arrow;
        this.key = key;
    }

    public InventoryModel(Map<String, Integer> inventoryDic) {
        if (inventoryDic.get("arrow") != null)
            arrow = inventoryDic.get("arrow");
        else
            arrow = 0;
        if (inventoryDic.get("key") != null)
            key = inventoryDic.get("key");
        else
            key = 0;
    }

    public Map<String, Integer> createInventoryDir() {
        Map<String, Integer> inventoryDir = new HashMap<>();
        if (key != 0)
            inventoryDir.put("key", key);
        if (arrow != 0)
            inventoryDir.put("arrow", arrow);
        return inventoryDir;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getArrow() {
        return arrow;
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }
}
