package org.example;

import javax.imageio.ImageIO;
import java.io.File;

public class Main {
    static public void main(String args[]) throws Exception {
        EAN13Generator generator = new EAN13Generator();
        // Additional arguments
        generator.setImageHeight(120);
        generator.setImageWidth(130);
        generator.setStartYPos(0);
        generator.setStartXPos(0);
        generator.setDefaultGuardHeight(120);
        generator.setDefaultLineHeight(80);

        ImageIO.write(generator.generateEAN13("9443044102032"), "PNG", new File("barcodeTest.PNG"));
    }
}