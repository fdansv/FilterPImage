package com.dansd.FilterPImage;

import processing.core.PApplet;

public class FilterTests extends PApplet {
    FilterPImage testImage;

    @Override
    public void setup() {
        size(800, 600);
        testImage = new FilterPImage(this,"/cat.jpg"); //Change this path to your image
        testImage.brightness(2); //This is how you call the effect


    }

    @Override
    public void draw() {
        image(testImage,0,0,500,500); //Now draw the image as you would with a PImage
    }
}
