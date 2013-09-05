FilterPImage
============

Extension of Processing's PImage that adds filters and image settings like contrast, saturation etc.

To use, do not init the object with Processing's loadImage. Instead, use the following constructor:

    FilterPImage image = new FilterPImage(context, path);
    
Where context is the PApplet, and path a String to get the file.

Current filters/adjustments available
-------------------------------------

    contrast(double value)
    
Changes the image's contrast

    tint(int r, int g, int b)
    
Tints (kind of) the image with the specified colour

    saturate(float factor)
    
Multiplies the saturation of the image by the factor.

    makeItMoreYellow() 
    
Makes the image, indeed, more yellow.

    smurfify()
    
Turns people into smurfs


    
    sepia(int depth)
    
Applies a sepia filter to the image, with more or less depth
    
    trainspotting(int depth)
    
Makes everything a bit more orange


