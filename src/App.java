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
    float squareSize = 30;
    float squareX = 400; // starting position
    float squareY = 750 - squareSize;
    float direction = 5;
    float speed = 15; // speed of movement of paddle

    // Top square
    float square2Size = 30;
    float squareX2 = 400;
    float squareY2 = fieldY;
    float speed2 = 10;

    // Ball Size and location and how it bounces
    float ballSize = 50;
    float ballX = 400;
    float ballY = 300;
    int ballSpeedX = 2;
    int ballSpeedY = 2;

    // Game over thing
    boolean gameOver = false;

    // Game state and Start Screen
    int stage = 0;

    public void resetGame() {
        ballX = width / 2;
        ballY = height / 2;
        squareX = 400; // Reset paddles to the original position
        squareX2 = 400;
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
    }

    public void draw() {

        if (stage == 1) {
            background(200); // Clear the background each frame
            noFill(); // Disable fill


            // background(200);
            rect(fieldX, fieldY, fieldWidth, fieldHeight);

            fill(255, 0, 0); // Set the fill color to red for the circle
            ellipse(ballX, ballY, ballSize, ballSize); // Draw a circle at (400, 300) with a diameter of 100

            fill(70, 119, 199); // Sets the fill color to blue for rectangle
            rect(squareX, squareY, squareSize, squareSize);
            
            rect(squareX2, squareY2, square2Size, square2Size); // Sets the fill color to blue for rectangle
           

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

            // checks if the ball has hit the bottom of the screen and if it does then the
            // game over is true
            if (ballY + ballSize / 2 > fieldY + fieldHeight) {
                gameOver = true;

            }
            // checks if the ball has hit the top of the screen and if it does then the game
            // over is true
            if (ballY - ballSize / 2 < fieldY) {
                gameOver = true;
            }
            // this code is where the Game Over is placed and it is placed in the middle of
            // the screen
            if (gameOver) {
                fill(0);
                textSize(50);
                textAlign(CENTER, CENTER);
                text("Game Over", width / 2, height / 2);
                noLoop(); // Stops the draw loop when the game is over
            }

            // Makes sure when the ball hits the bottom or top square then it collides and
            // bounces off of them
            playerCollision();

            // if(collision(ballX, 1, 5,squareX , 1)){

            // }else (collision(ballX, 15, 14, squareX2)){

            // }

            // screen one
        } else if (stage == 0) {
            background(255);
            fill(0);

            // title of the game in the middle
            textSize(40);
            textAlign(CENTER, CENTER);
            text("Welcome to Pong", width / 2, 100);

            // Instrucions for game
            textSize(20);
            textAlign(CENTER, CENTER);

            // instructions for the game
            text("Player 1: Use A and D to move", width / 2, 200);
            text("Player 2: Use Left and Right arrows to move", width / 2, 240);
            text(" Player 1 controls the top square, and Player 2 controls the bottom square.", width / 2,
                    280);
            text("First to score wins!", width / 2, 320);

            // How to Start
            text("Press Spacebar to start", width / 2, 400);

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
        } else if (key == 'd' && squareX2 + square2Size <= fieldX + fieldWidth) {
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
        if (ballX > squareX && ballX < squareX + squareSize && ballY + (square2Size / 2) > squareY) {
            ballSpeedY = -ballSpeedY;

        }

        if (ballX > squareX2 && ballX < squareX2 + square2Size&& ballY - (ballSize / 2) < squareY2 + square2Size ) {
            ballSpeedY = -ballSpeedY;

        }

    }

    // public boolean collision(float squareXPos, float squareYPos, float squareSize){
    //     if(){
    //         return true;
    //     }else{
    //         return false;
        }

        
    
