import processing.core.PApplet;

public class App extends PApplet {
    int width = 800;
    int height = 800;
    float fieldWidth = 400;
    float fieldHeight = 700;
    float fieldX = width / 2 - fieldWidth / 2;
    float fieldY = (height - fieldHeight) / 2;

    float squareSize = 30;
    float squareX = 200;
    float squareY = 750 - squareSize;
    float direction = 5;
    float speed = 10;

    float square2Size = 30;
    float squareX2 = fieldX;
    float squareY2 = fieldY;
    float direction2 = 5;
    float speed2 = 10;

    float ballSize = 50;
    float ballX = 400;
    float ballY = 300;
    int ballSpeedX = 2;

    int playerOneScore = 0; 
    int playerTwoScore = 0; 
    boolean gameOver = false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(width, height); // Set the window size to 800x600
    }

    public void setup() {
        background(200); // Set the background color to light gray
    }

    public void draw() {

        background(200); // Clear the background each frame
        noFill(); // Disable fill

        // rect(width / 2 - rectWidth / 2, (height - rectHeight) / 2, rectWidth,
        // rectHeight);

        // background(200);
        rect(fieldX, fieldY, fieldWidth, fieldHeight);

        fill(255, 0, 0); // Set the fill color to red for the circle
        ellipse(ballX, ballY, ballSize, ballSize); // Draw a circle at (400, 300) with a diameter of 100

        fill(70, 119, 199);
        rect(squareX, squareY, squareSize, squareSize);
        // squareX += direction;
        if (squareX >= 620 - squareSize || squareX < 170) {
            direction = -direction;

        }
        rect(squareX2, squareY2, square2Size, square2Size);
        // squareX += direction;
        if (squareX >= 620 - squareSize || squareX < 170) {
            direction = -direction;

        }
        ballX += ballSpeedX;
        ballY += 1;
        if (ballX + ballSize / 2 > fieldX + fieldWidth) {
            ballSpeedX = -ballSpeedX;
        }

        if (ballX - ballSize / 2 < fieldX) {
            ballSpeedX = -ballSpeedX;

        }
        if (ballY < fieldY) {
            System.out.println("Player One Scores");
            gameOver = true;

        }
        if (ballY > fieldY + fieldHeight) {
            System.out.println("Player Two Scores");
            gameOver = true;
        } else {
            fill(0);
            textSize(50);
            textAlign(CENTER, CENTER);
            text("Game Over", width / 2, height / 2);
        }

    }

    public void keyPressed() {
        if (keyCode == LEFT && squareX >= 210) {
            squareX -= speed;
        } else if (keyCode == RIGHT && squareX <= 560) {
            squareX += speed;
        } else if (key == 'a' && squareX2 >= fieldX) {
            squareX2 -= speed;
        } else if (key == 'd' && squareY2 <= fieldY) {
            squareX2 += speed;
        }

    }
}
