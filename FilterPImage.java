package com.dansd.FilterTests;

import processing.core.*;

public class FilterPImage extends PImage {
    public FilterPImage(PApplet applet, String imgPath){
        super();
        PImage img = applet.loadImage(imgPath);
        this.width = img.width;
        this.height = img.height;
        this.parent = applet;
        img.loadPixels();
        this.pixels = img.pixels;
        this.updatePixels();
    }

    protected FilterPImage(int width, int height) {
        super(width, height);
    }

    protected FilterPImage(int width, int height, int format) {
        super(width, height, format);
    }

    protected FilterPImage() {
        super();
    }

    public void saturate(float factor){
        this.parent.colorMode(HSB);
        this.loadPixels();
        for(int i=0; i<this.pixels.length; i++){
            int thisColor = parent.color(pixels[i]);

            int newColor = parent.color(parent.hue(thisColor), parent.saturation(thisColor) * factor, parent.brightness(thisColor));
            pixels[i] = newColor;
        }
        this.updatePixels();
        this.parent.colorMode(RGB);
    }

    public void makeItMoreYellow() {
        this.loadPixels();
        for(int i=0; i<this.pixels.length; i++){
            int thisColor = parent.color(pixels[i]);

            int newColor = parent.color(parent.red(thisColor) * 1.5f, parent.green(thisColor) * 1.5f, parent.blue(thisColor));
            pixels[i] = newColor;
        }
        this.updatePixels();
    }
    public void sinCity(){
        this.loadPixels();
        for(int i=0; i<this.pixels.length; i++){
            int thisColor = parent.color(pixels[i]);
            if(parent.red(thisColor)>100&&parent.green(thisColor)<100&&parent.blue(thisColor)<100){
                pixels[i] = parent.color(parent.red(thisColor), parent.green(thisColor), parent.blue(thisColor));
            }
            else{
                //colorMode(HSB);
                pixels[i] = parent.color(parent.red(thisColor) * 0.6f, parent.green(thisColor) * 0.6f, parent.blue(thisColor) * 0.6f);
                //pixels[i] = color(hue(thisColor),saturation(thisColor)*0.8f,brightness(thisColor)*0.8f);
                //colorMode(RGB);
            }
        }
        this.updatePixels();
    }
    public void smurfify(){
        this.loadPixels();
        for(int i=0; i<this.pixels.length; i++){
            int thisColor = parent.color(pixels[i]);
            parent.colorMode(HSB);
            pixels[i] = parent.color(150, parent.saturation(thisColor), parent.brightness(thisColor));
            parent.colorMode(RGB);
        }

        this.updatePixels();
    }
    public void contrast(double value){
        this.loadPixels();
        double contrast = Math.pow((100 + value) / 100, 2);
        for(int i=0; i<this.pixels.length; i++){
            int R = (int )parent.red(pixels[i]);
            int newr = (int)(((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
            int G = (int )parent.green(pixels[i]);
            int newg = (int)(((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
            int B = (int )parent.blue(pixels[i]);
            int newb = (int)(((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
            pixels[i] = parent.color(newr, newg, newb);
        }
        this.updatePixels();
    }
    public void sepia(int depth){
        this.loadPixels();
        for(int i=0; i<this.pixels.length; i++){
            int thisColor = parent.color(pixels[i]);
            int grey = (int) ((parent.red(thisColor)+parent.green(thisColor)+parent.blue(thisColor))/3);
            pixels[i] = parent.color(grey +depth*2 , parent.green(thisColor)+depth, parent.blue(thisColor)-depth);
        }
        this.updatePixels();
    }

    public void trainspotting(){
        this.tint(255,127,0);
        this.contrast(20);
    }

    public void ocean(){
        this.tint(0,0,80);
        this.saturate(0.5f);
    }

    public void tint(int r, int g, int b){
        this.loadPixels();
        for(int i=0; i<this.pixels.length; i++){
            int thisColor = parent.color(pixels[i]);
            int tintColor = parent.color(r,g,b);
            int newColor = parent.color(
                    (parent.red(thisColor)+parent.red(tintColor))/2,
                    (parent.green(thisColor)+parent.green(tintColor))/2,
                    (parent.blue(thisColor)+parent.blue(tintColor))/2
            );
            pixels[i] = newColor;
        }
        this.updatePixels();

    }
}
