package com.system.xpreader;

import java.util.ArrayList;

import com.system.TextRenderer;

/**
 * Created by bison on 02-01-2016.
 */
public class XPFile {
    int version;
    int noLayers;
    ArrayList<XPLayer> layers;

    public XPFile(int version, int noLayers, ArrayList<XPLayer> layers) {
        this.version = version;
        this.noLayers = noLayers;
        this.layers = layers;
    }

    public void render(TextRenderer renderer, int x, int y) {
        if(layers.size() < 1)
            return;
        layers.get(0).render(renderer, x, y);
    }
    
    public XPLayer layer(int i)
    {
        return layers.get(i);
    }

    public int noLayers()
    {
        return noLayers;
    }
}
