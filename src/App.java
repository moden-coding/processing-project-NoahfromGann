import processing.core.PApplet;

public class App extends PApplet {

    // Variables that set the heigh and width of my game
    int width = 800;
    int height = 800;

    // Variables that are the box on the game
    float fieldWidth = 400;
    float fieldHeight = 700;
    float fieldX = width / 2 - fieldWidth / 2;
    float fieldY = (height - fieldHeight) / 2;

    // Bottom square
    float squareSize = 100;
    float rectWidth = 60;
    float rectHeight = 30;
    float squareX = 400; // starting position
    float squareY = 750 - rectHeight;

    float rectX = 400; // starting position
    float rectY = 750 - rectHeight;

    float direction = 5;
    float speed = 30; // speed of movement of paddle

    // Top square
    float rectWidth2 = 100;
    float rectHeight2 = 30;
    float squareX2 = 400 - rectWidth2 / 2;
    float squareY2 = fieldY;
    float speed2 = 10;

    // Ball Size and location and how it bounces
    float ballSize = 50;
    float ballX = 400;
    float ballY = 300;
    int ballSpeedX = 2;
    int ballSpeedY = 2;

    // how many times the ball bounces in one round
    int bounceCount = 0;

    // Game over thing
    boolean gameOver = false;

    // Game state and Start Screen
    int stage = 0;

    public void resetGame() {
        ballX = width / 2;
        ballY = height / 2;
        squareX = 400; // Reset paddles to the original position
        squareX2 = 400 - rectWidth2 / 2;
        ballSpeedX = 2; // Reset ball speed
        ballSpeedY = 2;
        gameOver = false; // Reset game over state
        loop(); // Start the game loop again
    }

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(width, height); // Set the window size to 800x600
    }

    public void setup() {
        background(200); // Set the background color to light gray

        // Randomize ballX and ballY within the game field bounds
        ballX = random(fieldX + fieldY / 2, fieldX + fieldWidth - ballSize / 2);
        ballY = random(fieldY + ballSize / 2, fieldY + fieldHeight - ballSize / 2);

    }

    public void draw() {
        println();
        if (stage == 0) {
            startScreen();
        }
        if (stage == 1) {
            mainBackground();
            fill(70, 119, 199); // Sets the fill color to blue for rectangle
            rect(squareX, squareY, rectWidth, rectHeight);

            rect(squareX2, squareY2, rectWidth, rectHeight); // Sets the fill color to blue for rectangle
            ballMovement();

            // checks if the ball has hit the bottom of the screen and if it does then the
            // game over is true
            if (ballY + ballSize / 2 > fieldY + fieldHeight) {
                gameOver = true;
            }
            gameEnd();
            if (ballY - ballSize / 2 < fieldY) {
                gameOver = true;
            }
            // Makes sure when the ball hits the bottom or top square then it collides and
            // bounces off of them
            playerCollision();

        }
    }
    // Where my bounce counter is one the screen

    public void mainBackground() {
        background(200); // Clear the background each frame
        noFill(); // Disable fill

        // background(200);
        rect(fieldX, fieldY, fieldWidth, fieldHeight);

        fill(255, 0, 0); // Set the fill color to red for the circle
        ellipse(ballX, ballY, ballSize, ballSize); // Draw a circle at (400, 300) with a diameter of 100

        // Bounce count display
        fill(0);
        textSize(20);
        text("Bounces:" + bounceCount, 50, 50);

    }

    public void ballMovement() {
        // this determines the ball speed and how fast it goes and to make the ball move
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Makes ball bounce off walls
        if (ballX + ballSize / 2 > fieldX + fieldWidth) {
            ballSpeedX = -ballSpeedX;
        }

        // Makes ball bounce off walls
        if (ballX - ballSize / 2 < fieldX) {
            ballSpeedX = -ballSpeedX;

        }
    }

    // how to play
    public void keyPressed() {
        if (keyCode == LEFT && squareX >= fieldX) {
            squareX -= speed;
        } else if (keyCode == RIGHT && squareX + squareSize <= fieldX + fieldWidth) {
            squareX += speed;

        } else if (key == 'a' && squareX2 >= fieldX) {
            squareX2 -= speed2;
        } else if (key == 'd' && squareX2 + rectWidth2 <= fieldX + fieldWidth) {
            squareX2 += speed2;
        } else if (key == ' ') {
            stage = 1;
        }

        else if (key == 'r' && gameOver) {
            resetGame(); // Resets the game when 'R' is pressed

        }

    }

    // player collision
    public void playerCollision() {
        if (ballX > squareX && ballX < squareX + rectWidth && ballY + (ballSize / 2) > squareY) {
            ballSpeedY = -ballSpeedY;
            bounceCount++;
        }

        if (ballX > squareX2 && ballX < squareX2 + rectWidth2 && ballY - (ballSize / 2) < squareY2 + rectHeight2) {
            ballSpeedY = -ballSpeedY;
            bounceCount++;

        }

    }

    public void startScreen() {
        background(255);
        fill(0);

        // title of the game in the middle
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Welcome to Pong", width / 2, 100);

        // Instructions for the game
        textSize(20);
        textAlign(CENTER, CENTER);

        // Instructions for the game
        text("Player 1: Use A and D to move", width / 2, 200);
        text("Player 2: Use Left and Right arrows to move", width / 2, 240);
        text("Player 1 controls the top square, and Player 2 controls the bottom square.", width / 2, 280);
        text("First to score wins!", width / 2, 320);

        // How to Start
        text("Press Spacebar to start", width / 2, 400);
        text("Or to play one person you control both Player 1 and Player 2 with separate hands", width / 2, 600);

        // Bounce count display
        fill(0);
        textSize(20);
        text("Bounces:" + bounceCount, 50, 50);

    }

    public void gameEnd() {

        // this code is where the Game Over is placed and it is placed in the middle of
        // the screen
        if (gameOver) {
            fill(0);
            textSize(50);
            textAlign(CENTER, CENTER);
            text("Game Over", width / 2, height / 2);
            noLoop(); // Stops the draw loop when the game is over
        }
    }

}