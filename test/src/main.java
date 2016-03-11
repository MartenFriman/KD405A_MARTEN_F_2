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

public class main extends PApplet {
	public static void main(String[] args){
int w = 800;
int h = 480;

int w2 = w/2;
int w4 = w/4;
int w8 = w/8;

int h2 = h/2;
int h4 = h/4;
int h8 = h/8;

float redLine = 6300;
float idle    = 1300;

int xRPM = ceil((redLine/3)*2/1000);
int yRPM = floor((redLine/3)/1000);

float revLineSpacing = (h-h4-h8)/yRPM;

float baseRPM = map(redLine, 0, ceil(redLine/1000)*1000, 0, h-h4-h8+(revLineSpacing*xRPM));
float idleRPM = map(idle, 0, ceil(redLine/1000)*1000, 0, h-h4-h8+(revLineSpacing*xRPM));

float RPM = 0;
int speed = 0;
int fuelLevel = h-h4;
boolean accelerating = true;

PImage yamahaMotor_logo;
PImage fuel_icon;
PImage gps_icon;
PImage race_icon;
PImage dash_icon;
PImage settings_icon;

PShape warning_triangle;

PFont speedFont;

int UIcolor = color(0);
int selectedColor = color(0, 255, 0);

//nt width/2-menuOffset = w2;
float menuOffset = 0;
//float width/2-menuOffset_Y = height/2;

final int BOOTUP       = 0;
final int MAIN_G       = 1;
final int TRACK        = 2;
final int GPS          = 3;
final int SETTINGS     = 4;

int state = BOOTUP;
int menuSelection = MAIN_G;

boolean menu = false;


int boot = 0;
float fade = 0;
boolean fadeIn = true;

final int ON = 0;
final int OFF = 1;

int ENGINE = OFF;

public void setup() {
 
  background(0);
  frameRate(60);
  ellipseMode(CENTER);
  imageMode(CENTER);
  textAlign(CENTER, CENTER);
  rectMode(CENTER); 
  yamahaMotor_logo = loadImage("logo-yamaha-motor.jpg");
  fuel_icon = loadImage("fuel-icon.png");
  gps_icon = loadImage("gps-icon.png");
  dash_icon = loadImage("dash-icon.png");
  race_icon = loadImage("race-icon.png");
  settings_icon = loadImage("settings-icon.png");
}

public void draw() {
  print(menu);
  switch(state) {

  case BOOTUP:
    bootUp();
    break;

  case MAIN_G:
    noStroke();
    if (h > 485) image(yamahaMotor_logo, width/2-(menuOffset/2), height/2);
    else image(yamahaMotor_logo, width/2-(menuOffset/2), height/2, h-82, h-82);
    fill(255, 255, 255, 200);
    rect(width/2-menuOffset, height/2, w, h);

    revGagueMain();
    speedIndicatorMain();
    fuelGagueMain();
    throttleSimulationMain();
    break;

  case GPS:
    break;

  case SETTINGS:
    break;
  }
  sideMenuMain();
  warnings();
  smooth();
  noFill();
  strokeWeight(5);
  stroke(0);
  rect(width/2, height/2, w, h);
}

public void speedIndicatorMain() {
  //textSize(w/4+(speed/4));
  if (state != BOOTUP) speedSimulationMain();
  speedFont = createFont("Reality Hyper Regular", w/3+speed-(menuOffset/4), true);
  textFont(speedFont);
  fill(speed*4, 0, 0);
  if (state == BOOTUP) fill(fade, fade, fade);
  textAlign(CENTER, CENTER);
  //if (menu) textSize(w/4);
  text(speed, width/2-(menuOffset/2), height/2-(h/11));
  //textFont(
  textSize(w/20);
  fill(0, 0, 0, 255-(speed*6));
  text("km  h", width/2-(menuOffset/2), height/2+(h/5)+(speed/2)-(menuOffset/15));
  stroke(0, 0, 0, 255-(speed*6));
  strokeWeight(w/120);
  line(width/2-(menuOffset/2)+(w/25), height/2+(h/5)+(speed/2)-(w/50)-(menuOffset/15), width/2-(menuOffset/2)+(w/50), height/2+(h/5)+(speed/2)+(w/40)-(menuOffset/15));
}

public void speedSimulationMain() { 
  if (ENGINE == ON) {
    if (RPM/3-idleRPM > speed) speed+=1;
    else speed -=1;
    speed = constrain(speed, 2, 70);
  } else speed = 0;
}

public void fuelGagueMain() {
  if (fuelLevel == 0) ENGINE = OFF;
  if (keyPressed) {
    if (key == 'e' || key == 'E') {
      fuelLevel-=1;
      fuelLevel = constrain(fuelLevel, 0, h-h4);
    }
    if (key == 'f' || key == 'F') {
      fuelLevel+=1;
      fuelLevel = constrain(fuelLevel, 0, h-h4);
    }
  }
  strokeWeight(5);
  //stroke(UIColor);
  float xLimit = fuelLevel;
  xLimit = constrain(xLimit, 0, h4*2);

  for (float revLineX = 0; revLineX <= xLimit; revLineX+=h4/8) {
    pushMatrix();
    translate(width/2-menuOffset+w2-h2+revLineX, height/2+h2);
    rotate(atan2(width/2-menuOffset-(width/2-menuOffset)+(height/2), height/2));
    stroke(285-revLineX, 0, 0);
    line(0, 0, -w*0.015f-(revLineX/8), 0);  
    popMatrix();
  }


  float yLimit = fuelLevel-h2;
  yLimit = constrain(yLimit, 0, h4);

  if (fuelLevel > h2) {
    for (float revLineY = 0; revLineY <= yLimit; revLineY+=h4/8) {
      pushMatrix();
      translate(width/2-menuOffset+w2, height/2+h2-revLineY);
      rotate(atan2(width/2-menuOffset-(width/2-menuOffset)+(height/2), height/2));
      stroke(0);
      line(0, 0, -w*0.045f+(revLineY/4), 0); 
      popMatrix();
    }
  }
  stroke(0);
  line(width/2-menuOffset+w2, height/2+h4, width/2-menuOffset+w2-15, height/2+h4-15);
  line(width/2-menuOffset+w2, height/2+h2, width/2-menuOffset+w2-45, height/2+h2-45);
  line(width/2-menuOffset+w2-h2, height/2+h2, width/2-menuOffset+w2-h2-15, height/2+h2-15);
  textSize(w/20);
  fill(0);
  text("F", width/2-menuOffset+w2-35, height/2+h4-35);
  text("E", width/2-menuOffset+w2-h2-35, height/2+h2-35);
  image(fuel_icon, width/2-menuOffset+w2-65, height/2+h2-65, fuel_icon.height-(fuel_icon.height-(h/14)), fuel_icon.height-(fuel_icon.height-(h/14)));
}

public void warningTriangle(float x, float y, float size, float sw) {
  warning_triangle = createShape();
  warning_triangle.beginShape();
  warning_triangle.strokeWeight(sw);
  warning_triangle.vertex(-(size/30), -(size/4));
  warning_triangle.vertex(-(size/4), size/10);
  warning_triangle.vertex(-(size/4.8f), size/6);
  warning_triangle.vertex(size/4.8f, size/6);
  warning_triangle.vertex(size/4, size/10);
  warning_triangle.vertex(size/30, -(size/4));
  warning_triangle.vertex(-(size/30), -(size/4));
  warning_triangle.endShape();
  shape(warning_triangle, x, y);
}

public void sideMenuMain() {
  if (menu) {        
    if (menuOffset < w2) {
      menuOffset+=0.2f;
      menuOffset+=menuOffset/2;
      menuOffset = constrain(menuOffset, 0, w2);
      sideMenu();
    }
  } else if (menuOffset > 0) {
    menuOffset-=0.1f;
    menuOffset-=menuOffset/5;
    menuOffset = constrain(menuOffset, 0, w2);
    sideMenu();
  }
}

public void sideMenu() {
  fill(55);
    stroke(0);
  strokeWeight(w/200);
  rect(width/2-menuOffset+w+2, height/2, w, h);      

  boolean pressed = false;

  if (menuSelection == MAIN_G) pressed = true;
  menuButton_square(width/2-menuOffset+w-w4-w8, height/3*2-(h/3.5f), w/5, w/5, pressed);
  image(dash_icon, width/2-menuOffset+w-w4-w8, height/3*2-(h/3.5f), dash_icon.height-(dash_icon.height-(h4)), dash_icon.height-(dash_icon.height-(h4)));
  pressed = false;

  if (menuSelection == TRACK) pressed = true;
  menuButton_square(width/2-menuOffset+w-w4+w8, height/3*2-(h/3.5f), w/5, w/5, pressed);
  image(race_icon, width/2-menuOffset+w-w4+w8, height/3*2-(h/3.5f), race_icon.height-(race_icon.height-(h4)), race_icon.height-(race_icon.height-(h4)));
  pressed = false;

  if (menuSelection == GPS) pressed = true;
  menuButton_square(width/2-menuOffset+w-w4-w8, height/3*2+h8, w/5, w/5, pressed);
  image(gps_icon, width/2-menuOffset+w-w4-w8, height/3*2+h8, gps_icon.height-(gps_icon.height-(h4)), gps_icon.height-(gps_icon.height-(h4)));
  pressed = false;

  if (menuSelection == SETTINGS) pressed = true;
  menuButton_square(width/2-menuOffset+w-w4+w8, height/3*2+h8, w/5, w/5, pressed);
  image(settings_icon, width/2-menuOffset+w-w4+w8, height/3*2+h8, settings_icon.height-(settings_icon.height-(h4)), settings_icon.height-(settings_icon.height-(h4)));
  pressed = false;

  dash_icon.loadPixels();
  for (int x = 0; x < dash_icon.width*dash_icon.height; x++) {
    if (dash_icon.pixels[x] != dash_icon.pixels[0]) {
      if (state == MAIN_G) dash_icon.pixels[x] = selectedColor;
      else dash_icon.pixels[x] = color(255);
    }
    dash_icon.updatePixels();
  }
  race_icon.loadPixels();
  for (int x = 0; x < race_icon.width*race_icon.height; x++) {
    if (race_icon.pixels[x] != race_icon.pixels[0]) {
      if (state == TRACK) race_icon.pixels[x] = color(0);
      else race_icon.pixels[x] = color(255);
    }
    race_icon.updatePixels();
  }
  gps_icon.loadPixels();
  for (int x = 0; x < gps_icon.width*gps_icon.height; x++) {
    if (gps_icon.pixels[x] != gps_icon.pixels[0]) {
      if (state == GPS) gps_icon.pixels[x] = selectedColor;
      else gps_icon.pixels[x] = color(255);
    }
    gps_icon.updatePixels();
  }
  settings_icon.loadPixels();
  for (int x = 0; x < settings_icon.width*settings_icon.height; x++) {
    if (settings_icon.pixels[x] == color(0)) {
      if (state == SETTINGS) settings_icon.pixels[x] = selectedColor;
      else settings_icon.pixels[x] = color(255);
    }
    settings_icon.updatePixels();
  }
}

public void menuButton_square (float x, float y, int w, int h, boolean pressed) {

  fill(45);
  strokeWeight(w/15);
  stroke(65);
  rect(x, y, w, h);
  if (pressed) {
    stroke(65);
    rect(x, y, w-2, h-2);
    stroke(45);
    rect(x, y, w-4, h-4);
    stroke(25);
    rect(x, y, w-4, h-6);
  }
  strokeWeight(w/25);     
  stroke(0);
  noFill();
  rect(x, y, w, h);
}

public void warnings() {
  if (fuelLevel < (h-h4)*0.15f) {
    if (fadeIn) fade+=8;
    else fade-=8;
    if (fade >= 255) fadeIn = false;
    if (fade <= 1) fadeIn = true;
    fade = constrain(fade, 1, 255);
    stroke(0, 0, 0, fade);
    fill(255, 255, 255, fade);
    //  rect(width/2, height/2, h2, h2);

    warningTriangle(width/2, height/2, 600, width/100);
    fill(0, 0, 0, fade);
    strokeWeight(h/20);
    line(width/2, height/2-(h/6), width/2, height/2+(h/40));
    noStroke();
    ellipse(width/2, height/2+(h/10), h/20, h/20);
  }
}

public void keyPressed() {
  if (key == ENTER) {
    if (state != BOOTUP && fuelLevel > 0) {
      ENGINE++;
      if (ENGINE > OFF) ENGINE = ON;
    }
  }
  
  if (key == BACKSPACE) {
    if (state != BOOTUP) {
      if (menu) menu = false;
      else menu = true;
    }
  }
  
  if (key == CODED) {
    if (keyCode == RIGHT) {
    menuSelection++;
    if (menuSelection > SETTINGS) menuSelection = MAIN_G;
    sideMenu();  
    }
    if (keyCode == LEFT) {
    menuSelection--;
    if (menuSelection < MAIN_G) menuSelection = SETTINGS;
    sideMenu();  
    }
  }
}
public void bootUp() {
  switch (boot) {
  case 0:
    fade+=5;
    if (fade >= 255) {
      boot++;
      break;
    }
    fill(0);
    rect(width/2, height/2, w, h);
    fill(255, 255, 255, fade);
    rect(width/2, height/2, w, h);    
    break;

  case 1:   
    fade-=1.5f;
    if (fade <= 0) {
      boot++;
      break;
    }
    if (h > 485) image(yamahaMotor_logo, width/2, height/2);
    else image(yamahaMotor_logo, width/2, height/2, h-82, h-82);
    fill(255, 255, 255, fade);
    rect(width/2, height/2, w, h);
    break;

  case 2:
    fade+=3;
    if (fade >= 200) {
      boot++;
      fade = 255;
      break;
    }
    if (h > 485) image(yamahaMotor_logo, width/2, height/2);
    else image(yamahaMotor_logo, width/2, height/2, h-82, h-82);
    fill(255, 255, 255, fade);
    rect(width/2, height/2, w, h);
    break;

  case 3:
    fade-=3;
    if (fade <= 0) {
      boot++;
      break;
    }
    speedIndicatorMain();
    revLines();      
    break;

  case 4:
    fill(255);
    stroke(0);
    rect(width/2, height/2, w, h);
    if (RPM <= 0 && accelerating == false) {
      state = MAIN_G;
      //break;
    }
    if (accelerating) {
      RPM+=baseRPM/15;
      if (RPM >= h-h4-h8+(revLineSpacing*xRPM)) accelerating = false;
    } else {
      RPM-=baseRPM/15;
    }

    if (h > 485) image(yamahaMotor_logo, width/2, height/2);
    else image(yamahaMotor_logo, width/2, height/2, h-82, h-82);
    fill(255, 255, 255, 200 );
    rect(width/2, height/2, w, h);

    revIndicator();
    speedIndicatorMain();
    revLines();
    break;
  }
}
public void revGagueMain() {
  revIndicator();
  revLines();
}

public void revLines() {
  strokeWeight(6-(menuOffset/400));
  int iteration = 0;
  float resize = map(menuOffset, 0, w2, 0, revLineSpacing/2);
  for (float revLineY = 0; revLineY <= h-h4-h8-(resize/yRPM); revLineY+=revLineSpacing-resize) {
    pushMatrix();
    translate(width/2-w2, height/2+h2-h4-h8-revLineY-(resize*2));
    fill(0);
    if (state == BOOTUP) fill(fade, fade, fade);
    textSize(w/20);
    text(iteration, w*0.055f+(revLineY/5), w*0.055f+(revLineY/5));
    iteration++;

    rotate(atan2(width/2-(width/2)+(height/2), height/2));
    stroke(UIcolor);
    if (state == BOOTUP) stroke(fade, fade, fade);
    line(0, 0, w*0.065f+(revLineY/4)-(menuOffset/25), 0); //+(revLineY/5)
    popMatrix();
  }
  
  for (float revLineX = (h-h4-h8)/yRPM-(resize); revLineX <= revLineSpacing*xRPM-(resize*xRPM); revLineX+=revLineSpacing-resize) {
    pushMatrix();
    translate(width/2-w2+revLineX, height/2-h2);
    if (iteration < 7) {     
      text(iteration, w*0.035f+(h-h4-h8)/4-(revLineX/10), w*0.035f+(h-h4-h8)/4-(revLineX/10));    
      iteration++;
    }
    rotate(atan2(width/2-menuOffset-(width/2-menuOffset)+(height/2), height/2));
    if (state == BOOTUP) stroke(fade, fade, fade);
    line(0, 0, w*0.065f+(h-h4-h8)/4-(revLineX/7)-(menuOffset/7), 0);  
    popMatrix();
  }
}


public void revIndicator() {
  if (ENGINE == ON || state == BOOTUP) {
    
    float resize = map(menuOffset, 0, w2, 0, revLineSpacing/2);
    float xLimit = RPM-(h-h4-h8)-(resize*(xRPM-1));
    
    float yLimit = RPM;
    yLimit = constrain(yLimit, 0, h-h4-h8);
    
    strokeWeight(6-(menuOffset/400));
    if (RPM > yLimit-(resize*2)) {
      for (float revLineX = 0; revLineX <= xLimit; revLineX+=revLineSpacing/10) {
        //float ll = sqrt(pow((w*0.03)+(h-h8)/4/2, 2));
        //float llr = sqrt(pow((w*0.025)+(h-h8)/4/2-(revLineX/7), 2));
        //stroke(0, 0, 255);
        //line(width/2-w2+ll, height/2-h2+ll, width/2-w2+revLineX+llr, height/2-h2+llr);
        pushMatrix();
        translate(width/2-w2+revLineX, height/2-h2);
        rotate(atan2(width/2-(width/2)+(height/2), height/2));
        stroke(revLineX-w4, 0, 0);
        line(0, 0, w*0.045f+(h-h4-h8)/4-(revLineX/7)-(menuOffset/7), 0);  
        popMatrix();
      }
    }    

    for (float revLineY = 0; revLineY <= yLimit-(resize*2); revLineY+=revLineSpacing/10) {
      pushMatrix();
      translate(width/2-w2, height/2+h2-h4-h8-revLineY-(resize*2));
      rotate(atan2(width/2-(width/2)+(height/2), height/2));
      stroke(0);
      line(0, 0, w*0.045f+(revLineY/4)-(menuOffset/20), 0); 
      popMatrix();
    }
  }
}

public void throttleSimulationMain() {
  if (keyPressed) {
    if (key == CODED) {
      if (keyCode == UP) {
        RPM+=baseRPM/22;
        RPM = constrain(RPM, baseRPM/5, baseRPM);
      }
    }
  } else if (keyPressed == false) {
    RPM-=baseRPM/18;
    RPM = constrain(RPM, baseRPM/5, baseRPM); 
  }
}
  public void settings() {  size(800, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
	}
}
