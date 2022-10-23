package org.example;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EAN13Generator {
    @Getter @Setter private int imageWidth = 300;
    @Getter @Setter private int imageHeight = 300;
    @Getter @Setter private int defaultGuardHeight = 180;
    @Getter @Setter private int defaultLineHeight = 90;
    @Getter @Setter private int startXPos = 100;
    @Getter @Setter private int startYPos = 80;

    private final BarcodeBytecoder bytecoder;

    public EAN13Generator() {
        bytecoder = new BarcodeBytecoder();
    }
    public BufferedImage generateEAN13(String barcode) {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();

        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, imageWidth, imageHeight);

        graphics2D.setColor(Color.black);
        drawBytecode(barcode, graphics2D);

        return image;
    }

    private void drawBytecode(String barcode, Graphics2D graphics2D) {
        String bytecode = bytecoder.barcodeToBytecode(barcode);

        for (int i = 0; i < bytecode.length(); i++) {
            if (bytecode.charAt(i) == '0') continue;

            int lineHeight = defaultLineHeight, y = (defaultGuardHeight - defaultLineHeight) / 2;

            if (i < 3 || (44 < i && i < 50) || i > 91) {
                lineHeight = defaultGuardHeight;
                y = 0;
            }

            graphics2D.fillRect(i + startXPos, y + startYPos, 1, lineHeight);
        }
    }
}
