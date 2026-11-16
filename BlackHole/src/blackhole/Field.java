/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackhole;

import java.awt.Color;

/**
 *
 * @author anash
 */
public class Field {

    private Color color;
    private boolean isHole;

    public Field() {
        this.color = null;
        this.isHole = false;
    }

    public Field(boolean isHole) {
        this.isHole = isHole;
        this.color = null;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isHole() {
        return isHole;
    }

    public void setHole(boolean isHole) {
        this.isHole = isHole;
    }
}