import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class cd_visual extends PApplet {

float[][] interp_array;
PImage despacito;
PImage postford;
float sinX = 0;
float cosX = 0;
int angle = 0;

public void setup() {
  despacito = loadImage("despacito.png");
  postford = loadImage("postford.jpg");
  despacito.loadPixels();
  size(despacito.width, despacito.height);
  interp_array = new float[width][height];
  makeArray();
  applyColor();
}

// Fill array with Perlin noise (smooth random) values
public void makeArray() {
  for (int r = 0; r < height; r++) {
    for (int c = 0; c < width; c++) {
      // Range is 24.8 - 30.8
      interp_array[c][r] = 130*(sinX+cosX) * noise(r * 0.02f, c * 0.02f);
      // interp_array[c][r] = 24.8 + 6.0 * noise(r * 0.02, c * 0.02);
    }
  }
}

public void applyColor() {  // Generate the heat map
  // background(0,255,0);
  pushStyle(); // Save current drawing style
  // Set drawing mode to HSB instead of RGB
  // colorMode(HSB, 1, 1, 1, 1);
  colorMode(RGB, 1, 1, 1, 1);
  loadPixels();
  despacito.loadPixels();
  int p = 0;
  int i = 0;
  for (int y = 0; y < height; y++) {
    for (int x = 0; x < width; x++) {
      int loc = x + y * width;
      // Get the heat map value
      float value = interp_array[x][y];
      // Constrain value to acceptable range.
      float r = red(despacito.pixels[loc]);
      float g = green(despacito.pixels[loc]);
      float b = blue(despacito.pixels[loc]);
      value = constrain(value, 0, 130);
      // value = constrain(value, 25, 30);
      // Map the value to the hue
      // 0.2 blue
      // 1.0 red
      value = map(value, 0, 130, 0, 1.0f);
      // println(value);
      // value = map(value, 25, 30, 0.2, 1.0);
      // pixels[p++] = color(value, 0.9, 1);
      int col = color(r,g,b,value);
      despacito.set(x,y,col);
    }
  }
  updatePixels();
  image(despacito,0,0);
  popStyle(); // Restore original drawing style
}

public void draw()
{
  // background(255,0,0);
  image(postford,0,0);
  makeArray();
  applyColor();
  sinX = abs(sin(radians(angle*0.2f)));
  cosX = abs(cos(radians(angle)));
  angle++;
  interp_array[angle % width][PApplet.parseInt(random(height))] = interp_array[PApplet.parseInt(random(width))][angle % height];
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "cd_visual" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
