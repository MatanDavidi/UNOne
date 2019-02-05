
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/*
 * Copyright (C) 2018 Matan Davidi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * La classe ImageHelper è
 *
 * @author Matan Davidi
 * @version 24-dic-2018
 *
 */
public class ImageHelper {

    /**
     * Taken directly from
     * https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
     * Thanks to Suken Shah and Mr. Polywhirl
     *
     * @param srcImg
     * @param w
     * @param h
     * @return
     */
    public static Image getScaledImage(Image srcImg, int w, int h) {

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;

    }

    /**
     * Adjusted from
     * https://stackoverflow.com/questions/4287499/rotate-jlabel-or-imageicon-on-java-swing
     * Thanks to trashgod and the StackOverflow community
     *
     * @param srcImg
     * @param imgWidth
     * @param imgHeight
     * @param rad
     * @return
     */
    public static Image getRotatedImage(Image srcImg, int imgWidth, int imgHeight, double rad) {

        BufferedImage rotatedImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = rotatedImg.createGraphics();

        g2.rotate(rad, imgWidth / 2, imgHeight / 2);
        g2.drawImage(srcImg, 0, 0, imgWidth, imgHeight, null);
        g2.dispose();

        return rotatedImg;

    }

}
