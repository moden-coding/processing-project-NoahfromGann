import processing.core.PApplet;

public class App extends PApplet {
    float squareSize = 30;
    float squareX = 200;
    float squareY = 750 - squareSize;
    float direction = 5;
    float speed = 10;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 800); // Set the window size to 800x600
    }

    public void setup() {
        background(200); // Set the background color to light gray
    }

    public void draw() {

        background(200); // Clear the background each frame
        noFill(); // Disable fill

        float rectWidth = 400;
        // rect(width / 2 - rectWidth / 2, 0, rectWidth, height);
        float rectHeight = 700;
        // rect(width / 2 - rectWidth / 2, (height - rectHeight) / 2, rectWidth, rectHeight);

        // background(200);
        rect(width / 2 - rectWidth / 2, (height - rectHeight) / 2, rectWidth, rectHeight);

        fill(255, 0, 0); // Set the fill color to red for the circle
        ellipse(400, 300, 50, 50); // Draw a circle at (400, 300) with a diameter of 100

        
        

        fill(255, 0, 0); 
        rect(squareX, squareY, squareSize, squareSize); 
        //squareX += direction;
        if(squareX >= 620 - squareSize || squareX < 170){
             direction = -direction;
         }

       

        
    }

    public void keyPressed(){
        if(keyCode == LEFT && squareX >= 210){
            squareX -= speed;
        }else if(keyCode == RIGHT && squareX <= 560){
            squareX += speed;
        }
    }

}
