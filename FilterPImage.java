package com.dansd.FilterPImage;

import processing.core.*;

public class FilterPImage extends PImage {
    private static final int HUE = 122;
    private static final int SATURATION = 123;
    private static final int BRIGHTNESS = 124;

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
        this.modifyHSB(SATURATION, factor);
    }

    public void hue(float factor){
        this.modifyHSB(HUE, factor);
    }

    public void brightness(float factor){
        this.modifyHSB(BRIGHTNESS, factor);
    }

    private void modifyHSB(int mode,  float factor) {
        this.parent.colorMode(HSB);
        this.loadPixels();
        for(int i=0; i<this.pixels.length; i++){
            int thisColor = parent.color(pixels[i]);
            int newColor;
            switch(mode){
                case HUE:
                    newColor = parent.color(parent.hue(thisColor) * factor, parent.saturation(thisColor) , parent.brightness(thisColor));
                    break;
                case SATURATION:
                    newColor = parent.color(parent.hue(thisColor), parent.saturation(thisColor) * factor , parent.brightness(thisColor));
                    break;
                case BRIGHTNESS:
                    newColor = parent.color(parent.hue(thisColor), parent.saturation(thisColor) , parent.brightness(thisColor)*factor);
                    break;
                default:
                    newColor = thisColor;
            }
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

    // tint() at the moment doesn't really tint it (i.e. applies a translucent layer over the image),
    // but averages each pixel in the image with the specified colour

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

    public void setFilterByName(int filterName){
        switch(filterName){
            case FilterPImage.THRES: this.filter(THRESHOLD);
            case FilterPImage.MORE_YELLOW: this.makeItMoreYellow();
            case FilterPImage.SMURFIFY: this.smurfify();
            case FilterPImage.SATURATE: this.saturate(0.1f);
            case FilterPImage.CONTRAST: this.contrast(50);
            case FilterPImage.SEPIA: this.sepia(20);
            case FilterPImage.INV: this.filter(INVERT);
            case FilterPImage.TRAINSPOTTING: this.trainspotting();
            case FilterPImage.OCEAN: this.ocean();
        }
    }

    private static final int THRES = 12345;
    private static final int MORE_YELLOW = 12346;
    private static final int SMURFIFY = 12347;
    private static final int SATURATE = 12348;
    private static final int CONTRAST = 12349;
    private static final int TRAINSPOTTING = 12350;
    private static final int SEPIA = 12351;
    private static final int INV = 12352;
    private static final int OCEAN = 12353;

    public static final int[] filters = {THRES,MORE_YELLOW,SMURFIFY,SATURATE,CONTRAST,SEPIA,INV,TRAINSPOTTING,OCEAN};
}
