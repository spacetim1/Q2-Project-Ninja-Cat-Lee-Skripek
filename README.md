# Q2-Project-Ninja-Cat-Lee-Skripek
## By Timothy Lee & Andrew Skripek

## Inspiration
#### This project is based on the game Math Ninja, an old mobile game where you have to solve math problems and shoot the incoming enemies with a ninja star.
#### This project took the shooting at the oncoming enemies part.

![image](https://user-images.githubusercontent.com/87093151/148460993-1d5138a8-428d-4b5c-aafd-b89cedce49ff.png)

## Intro & Instructions:
##### You feel the breeze on your face and the fresh scent of pine and fern. You are caught up in the scent of the forest until you bump into a mass of fur. Your claws accidentally get tangled up in the matted fur and just as you free yourself from its grip you are face to face with a horde of angry bears!
##### As you ran for your life, the bears hot on your trail, you find a shining red dot in the earth. You pick it up and realize its magic!
#### The main goal of the game is to kill 20 bears (enemies) before they can reach you and kill you.
#### You are gifted with a magic, red bullet which will go to where you wish it to (where you click). It is powerful, one shot can kill a bear, but beware: it sometimes moves at erratic speeds so be wary of where you aim it.

## Contents of the game:

## Driver
### The Driver class is the class which contains all the objects from other classes. It is the so-called "play area" of the game.

## Bear
### The Bear class contains the object bear, who is the antagonist of the game. They are programmed to spawn somewhere on the right side of the field and head towards you in order to kill you. The bear image is taken from the internet.
###### When the red bullet comes into contact with a bear's hitbox, a collide method is called upon to kill the bear and reset its position to the right side of the screen, where is it randomly generated to appear again past an x (outside of the frame, to the very right) coordinate, to come after the cat once again. This method also has a noice indication that a bear was hit. When one of the bears passes the cat's x (all the way to the left), the game ends and you lose.

## Bullet
### The Bullet class contains the magical red bullet, which is used to kill the oncoming horde.
###### The trickiest part of the game was coding the bullet's shooting ability. It requires usage of math and geometry. Think of the frame area as a graph. The bullet's original position is the first point and whereever the mouse pointer is when it is clicked is the second point. Using the two points you can create a linear line. That linear line has a slope, that is as y changes, x changes along with it. So to move the bullet we calculate how much its y should move and how much its x should move using the position of the mouse pointer and the bullet. Then we make the bullet move by that increment. A minor problem pops up sometimes, however, the bullet is too slow in some areas due to the position of the mouse pointer compared to the bullet. To solve this, we multiply that slow, original increment by a minimum speed so that it will always stay above a certain level and not be impossible to win.

## Background
### The Background class consists of the image for the background for the game.

## Cat
### The Cat class contains the player, the cat, well essentially just the image of the cat as he basically is not interactable in the game, he is just there. The image is taken from scratch. 

## Music
### The Music class is a class that is required to play music in the game. It allows the method music.play() to play a sound effect.
