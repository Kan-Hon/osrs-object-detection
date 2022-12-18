package com.examplescript;


import org.osbot.rs07.api.Display;
import org.osbot.rs07.api.ai.util.Time;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.util.Utilities;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.NPCS;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.gson.*;

@ScriptManifest(author = "KH", name = "BoundingBoxCollector", info = "Collect images on cows - Redacted Version", version = 0.1)
public final class BBCollector extends Script {
    List<NPC> npcList;
    Gson gson;
    List<NPC> validNpcs;
    NPCS npcs;

    Rectangle boundingBox;
    List<ObjectLabel> objectLabelsPaint;
    List<ObjectLabel> objectLabelsDebug;
    Rectangle viewport;

    Display display;

    int size = 1200;

    int screenshotCount = 0;
    String savePath;

    Utilities utilities;
    @Override
    public final int onLoop() throws InterruptedException {

        npcs = getNpcs();
        npcList = npcs.filter(npcFilter);
        List<ObjectLabel> newLabels = new ArrayList<ObjectLabel>();
        objectLabelsPaint = new ArrayList<ObjectLabel>();

        addLabels(npcList, newLabels);
        objectLabelsPaint = new ArrayList<ObjectLabel>(newLabels);
        saveFrame(newLabels);
        return random(1000, 1500);
    }

    @Override
    public final void onStart() {
        log("This will be printed to the logger when the script starts");
        npcs = getNpcs();
        npcList = npcs.getAll();
        display = getDisplay();
        screenshot(savePath, "image", new ArrayList<ObjectLabel>());
    }

    @Override
    public final void onExit() {
        log("This will be printed to the logger when the script exits");
    }

    Filter<NPC> npcFilter = new Filter<NPC>(){
        @Override
        public boolean match(final NPC e) {
            if (e == null) return false;
            return true;
        }
    };

    public void addLabels(List<? extends Entity> entities, List<ObjectLabel> labels){
        for (Entity e: entities) {
            addLabel(e,labels);
        }

    }

    public void addLabel(Entity e, List<ObjectLabel> labels){
        String entityName;
        boolean status;
        int gridX;
        int gridY;
        int gridZ;
        Rectangle entityBoundingBox;
        labels.add(new ObjectLabel(entityName, entityBoundingBox));

    }
    public void saveImage(BufferedImage image, String folder, String filename) {
        File file = new File(folder);
        try {
            ImageIO.write(image, "png",
                    new File(String.format("%s/%s.png", folder, filename)));
        } catch (Exception e) {
            log(e);
        }
    }


    public synchronized void screenshot(String folder, String filename, List<ObjectLabel> newLabels) {
        BufferedImage image;
        saveImage(image, folder, filename);
        screenshotCount++;
    }


    public void saveFrame(List<ObjectLabel> newLabels) {
        String imageName = String.format("%d", System.currentTimeMillis());
        Metadata m; 
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        String json = gson.toJson(m);
        screenshot(savePath, imageName, newLabels);
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json);
            bw.newLine();
            bw.close();
        } catch(Exception e) {
        }



    }


}