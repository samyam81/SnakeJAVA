# Snake Game

This Java program implements a classic Snake game using Swing. The game features a snake that moves around the screen, eating apples to grow longer. The player controls the snake's direction using arrow keys. The game ends if the snake collides with itself or the screen boundaries.

## Features
- Snake grows longer when it eats an apple.
- Score displayed on the screen.
- Game over screen displayed when the snake collides with itself or the screen boundaries.

## Components
- **GameFrame:** Extends JFrame to create the game window. It adds a GamePanel to the frame and sets up various properties such as title, size, and location.
- **GamePanel:** Extends JPanel and implements the game logic. It handles painting the game elements, moving the snake, checking collisions, and processing user input using KeyAdapter.
- **SnakeGame:** Main class to start the game by creating an instance of GameFrame.

## How to Play
1. Use the arrow keys to control the direction of the snake.
2. Guide the snake to eat apples and grow longer.
3. Avoid collisions with the snake's body or the screen boundaries.
4. The game ends when the snake collides, and the final score is displayed.

Enjoy playing the classic Snake game!

