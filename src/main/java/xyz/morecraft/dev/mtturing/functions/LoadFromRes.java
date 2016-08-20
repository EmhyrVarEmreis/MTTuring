package xyz.morecraft.dev.mtturing.functions;

import javax.swing.*;
import java.awt.*;

public class LoadFromRes {

    public static String getURLOfResource(String path) {
        return LoadFromRes.class.getClass().getResource("/img/" + path).toString();
    }

    public static Image loadImageAsImage(String path) {
        Image ret = null;
        try {
            ret = Toolkit.getDefaultToolkit().createImage(LoadFromRes.class.getClass().getResource("/img/" + path));
        } catch (Exception e) {
            // TODO Exception
        }
        return ret;
    }

    public static ImageIcon loadImageAsIconImage(String path) {
        ImageIcon ret = null;
        try {
            ret = new ImageIcon(loadImageAsImage(path));
        } catch (Exception e) {
            // TODO Exception
        }
        return ret;
    }

}
