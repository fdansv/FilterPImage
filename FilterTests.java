package com.dansd.FilterTests;

import processing.core.PApplet;

/**
 * Created with IntelliJ IDEA.
 * User: franciscodans
 * Date: 04/09/2013
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class FilterTests extends PApplet {
    FilterPImage testImage;

    @Override
    public void setup() {
        size(800, 600);
        testImage = new FilterPImage(this,"/cat.jpg");
        testImage.trainspotting();


    }

    @Override
    public void draw() {
        image(testImage,0,0,500,500);
    }
}