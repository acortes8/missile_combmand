# Missile Comb-mand
Java-based game using JavaFX. Player operates a turret that shoots honey-missiles, represented by a yellow line which explode if the fire-button is pressed again, to defend their bases, represented by beehive sprites, against wasp-missiles raining from the sky, represented by red lines. The player can aim the turret using arrow keys and shoot missiles using spacebar, pressing spacebar again while the missile is still flying, and on-screen, will cause the missile to explode in the air, taking out any enemy missiles caught in its blast radius. The objective is to destroy the incoming wasps before they reach the ground, as they target your beehives. Game-over condition is reached if all beehives are destroyed. Game features dynamic animations, collision detection, and a scoring system. No win-condition, objective is to survive as long as you can, and get the highest score you can in a play-through. Implements object-oriented programming principles for modular design and maintainability.  

Skills: Java · JavaFX · Object-Oriented Programming (OOP) · Event Handling · Animation · IntelliJ · Maven  

Game-play:  
1. When the game opens, a title card is displayed. Moving the turret or firing makes the title card disappear so you can start playing.
![image](https://github.com/acortes8/missile_combmand/assets/46253800/ca813e44-2d42-4225-b902-34dbdb5ab7b7)
![image](https://github.com/acortes8/missile_combmand/assets/46253800/8414f124-2cbc-4533-a436-6aa25862fef4)  
2. Use the arrow keys to move your turret either left or right. and press space bar to fire a missile. Enemy missiles start raining down from the sky right away. Your objective is to survive as long as you can until you lose. Missiles randomly target beehives, and new missiles spawn as you shoot them down.  
![image](https://github.com/acortes8/missile_combmand/assets/46253800/8b7008d9-6d3a-48ab-a55a-88dc5df13a36)
3. Press space bar again, while the missile is in-flight and on-screen, to make it explode in-air. The explosion will take out any enemy missiles. The score count at the bottom-right corner of the screen will update with every missile you destroy.  
![image](https://github.com/acortes8/missile_combmand/assets/46253800/88b16e1e-4295-427e-98e5-e94eb4319538)
4. Prevent enemy missiles from reaching your beehives. If they reach a beehive, the enemy missiles explode, destroying that beehive.
![image](https://github.com/acortes8/missile_combmand/assets/46253800/acb8b7d8-7b17-40b6-91f7-ea6229acc469)
5. New enemy missiles will target whatever remaining beehives exist, ignoring the location of beehives they've already destroyed.
![image](https://github.com/acortes8/missile_combmand/assets/46253800/97293934-eb59-4c1d-8c48-06c75b26716c)
6. You lose once all beehives are destroyed. This will cause a defeated card to display. 
![image](https://github.com/acortes8/missile_combmand/assets/46253800/57080b4b-7368-47b6-ae82-4c479b131437)  






How to run using IntelliJ:  
1. Open IntelliJ and traverse to projects home screen (save and close any current project you have open, if any).  
![image](https://github.com/acortes8/missile_combmand/assets/46253800/37acd5dd-42e8-4b67-8997-f6aa8f120b01)  
2. Select the "Open" Button.  
![image](https://github.com/acortes8/missile_combmand/assets/46253800/eb801328-8c29-48f1-9359-b627f2e725b8)  
3. Navigate to the downloaded, and unzipped, project folder, select it, and then select "OK".  
![image](https://github.com/acortes8/missile_combmand/assets/46253800/b58e8566-6c54-423c-a6b7-d07e4644a05b)  
4. Wait for project to open in IntelliJ. It should start to build automatically once opened. Progress bar is at the bottom-right of the screen. If project did not start to build automatically, press the build button in the upper-right. Once process is completed, press the run button, in the upper-right.  
![image](https://github.com/acortes8/missile_combmand/assets/46253800/28b976a1-8bb8-4006-8d80-8181e10fa5ae)  
5. Game should now run.  
![image](https://github.com/acortes8/missile_combmand/assets/46253800/88a6948d-37ba-4154-aa81-57c23fc37d15)  
