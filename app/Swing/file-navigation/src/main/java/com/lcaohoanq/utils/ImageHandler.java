package com.lcaohoanq.utils;

import com.lcaohoanq.Main;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class ImageHandler {

    public URL iconURL = Main.class.getResource("/logo.png");
    public Image icon = Toolkit.getDefaultToolkit().createImage(iconURL);

}
