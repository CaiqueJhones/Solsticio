/*
 * J2DImageManager.java
 *
 * Copyright 2017 Caique Jhones
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.solsticio.java2d.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.solsticio.core.graphics.Image;
import io.solsticio.core.io.ImageManager;
import io.solsticio.java2d.graphics.J2DImage;

/**
 * @author Caique Jhones
 * @version 1
 * @since 0.1.0
 */
public class J2DImageManager implements ImageManager {

    private static final Logger log = LoggerFactory.getLogger(J2DImageManager.class);
    private static final ImageManager instance;
    private Map<String, Image> cache;

    static {
        instance = new J2DImageManager();
    }

    public static ImageManager getInstance() {
        return instance;
    }

    private J2DImageManager() {
        cache = new HashMap<>();
    }

    @Override
    public Image createImage(String fileName) {
        if (cache.containsKey(fileName))
            return cache.get(fileName);
        File file = new File(fileName);
        if (!file.exists())
            throw new RuntimeException("Arquivo " + fileName + " não existe!");
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = new J2DImage(bufferedImage);
            cache.put(fileName, image);
            return image;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Image createImage(InputStream stream) {
        try {
            BufferedImage image = ImageIO.read(stream);
            return new J2DImage(image);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
