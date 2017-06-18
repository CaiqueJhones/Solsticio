/*
 * J2DImageTransform.java
 *
 * Copyright 2017 Caique Jhones
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package io.solsticio.java2d.graphics;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import io.solsticio.core.graphics.Image;
import io.solsticio.core.graphics.ImageTransform;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class J2DImageTransform implements ImageTransform {
    @Override
    public Image rotate(Image src, double angle, double locationX, double locationY) {
        BufferedImage source = ((J2DImage) src).getImage();

        AffineTransform tx = AffineTransform.getTranslateInstance(locationX, locationY);
        tx.concatenate(AffineTransform.getRotateInstance(angle, locationX, locationY));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return new J2DImage(op.filter(source, null));
    }

    @Override
    public Image flipHorizontal(Image src) {
        BufferedImage source = ((J2DImage) src).getImage();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-source.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return new J2DImage(op.filter(source, null));
    }

    @Override
    public Image flipVertical(Image src) {
        BufferedImage source = ((J2DImage) src).getImage();

        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -source.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return new J2DImage(op.filter(source, null));
    }

    @Override
    public Image flip(Image src) {
        BufferedImage source = ((J2DImage) src).getImage();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-source.getWidth(), -source.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return new J2DImage(op.filter(source, null));
    }

    @Override
    public Image scale(Image src, double dx, double dy) {
        BufferedImage source = ((J2DImage) src).getImage();

        AffineTransform tx = AffineTransform.getScaleInstance(dx, dy);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        return new J2DImage(op.filter(source, null));
    }
}
