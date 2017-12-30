package com.system.xpreader;

import com.system.TextRenderer;
import com.system.util.Utilities;

/**
 * Created by bison on 02-01-2016.
 */
public class XPLayer
{
    public int width;
    public int height;
    public XPChar[][] data;

    public void render(TextRenderer renderer, int xp, int yp) {
        for(int y=0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(data[x][y].code != 32) {                	
                	renderer.text(data[x][y].code + "", xp + x, yp + y, Utilities.convertColor(data[x][y].fgColor), Utilities.convertColor(data[x][y].bgColor));
                }
                    //terminal.write(data[x][y].code, x, y, data[x][y].fgColor, data[x][y].bgColor);
            }
        }
    }
}
