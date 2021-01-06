# My Personal Project

# space Shooter
**Proposal** 

This game will is inspired by the infamous 90's astroid-shooter, but instead of shooting at astroids, you 
will be shooting at enemies spaceships. This game will be a 2 dimensional shooter arcade game which involves 
the player to clear increasing size waves of ships without dying to the opponent. This application is intended 
for those who want to kill time. I wanted to create a video game for my personal project to not only practice OOP 
techniques but to also practice game development.

**User Stories**

**Phase 1:**

as a user, I want to know my health 
As a user, I want to add a enemy to a Enemies
As a user, I want to know how many enemies are present
As a user, I want to be able to see how many alive
As a user, I want to be able to change difficulty of the game

**Phase 2:**

As a user, when I select the quit option from the application menu, I want the option to save my game to file
As a user, I want to be able to load my game


**Phase 3:**
As a user, I want to choose the direction of where my bullets are fired using my mouse
As a user, I want to see the number of bullets fired
As a user, I want to quit the application within the actual running game 

**Instructions for Grader**
Warning: 
* Please make sure your volume is not set to 100% as there is background music playing. Also there is a hardware bug
 which only occurs on certain computers configurations which displays a Java Runtime Enviroment exception in the
  terminal. I talked to Felix and he said to ignore this when it comes 
 to grading
1. To launch the game please run Main
2. You will be prompted with my interface. 
- The Player control button will inform you with the controls needed to play
- Create new game button will show you the initialization sub menu needed to start the game
- Load new game button will have the same initialization button 
    (there is a bug when loading the game where it will add the difficulty-chosen enemy pre-game-spawn to the saved 
    file enemy count, please ignore this)
- exit button will close the GUI
3. to generate the "add an X to a Y" criteria is to load a New Game using the Create New Game Button and look at 
"number of enemies alive" counter to see the amount of enemies added to the list. The other way to generate the second 
event is to update
     this list by pressing shift on your keyboard, (this is a kill-all command to update the listOfEnemies because there is a bug with 
     the enemies being killed individually therefore i disabled that functionality.)
     - to test the shooting functionality, drag your mouse to either 4 corners of your laptop's display 
     (outside of the GUI) and press space bar to shoot.
4. The visual component of this application was the animation of the sub-menu and the actual players/bullets in the game
 and audio visual component comes when you hover over the buttons and pressing the spacebar when in the actual game
 
 
**Phase 4: Task 2**
I have chosen to implement the type hierarchy. The effected classes are, Player(supertype), 
Enemy and MainPlayer ( both subtypes) located in the model package.
They both override the spawnLocation() and move() methods. This is important as we want different spawn locations for
both the enemy and main player. We also want the movement of the enemy to be automated while the movement of the main 
player to be controlled by the keyboard.


**Phase 4: Task 3** 
- Extracted new class called SaveGame from game which handles the initialization components of loading a saved game data
and saving an actual game
-Extracted a new class called GameKeyHandler which handles the keyboard inputs in a game
-also note that I left out the associations for the MainMenu method in the ui package as that's legacy code that isn't
needed to run the game.

