package io.solsticio.core.graphics;


import java.io.Serializable;

public class Font implements Serializable {

    public enum FontType {
        NORMAL, BOLD, ITALIC, BOLD_ITALIC
    }

    private String name;
    private int size;
    private FontType fontType;

    public Font(String name, int size, FontType fontType) {
        this.name = name;
        this.size = size;
        this.fontType = fontType;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public FontType getFontType() {
        return fontType;
    }
}
