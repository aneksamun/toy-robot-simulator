# Toy Robot Simulator   

The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units. The robot is roaming around the surface with no obstructions and also being prevented from falling to destruction. 

## Command line commands

| Command       | Description                                                                             |
| ------------- | --------------------------------------------------------------------------------------- |
| PLACE X,Y,F   | Puts the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.  |
| MOVE          | Moves the toy robot one unit forward in the direction it is currently facing.           |
| LEFT          | Rotates the robot 90 degrees in the left.                                               |
| RIGHT         | Rotates the robot 90 degrees in the right.                                              |
| REPORT        | Announces the X,Y and facing direction of the robot.                                    |

The first valid command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application discards all commands in the sequence until a valid PLACE command has been executed.   
Any commands that would cause the robot to fall are ignored.

## Use case

```
Toy Robot Simulator

Command: PLACE 0,0,NORTH
Command: MOVE
Command: REPORT
0,1,NORTH
Command: PLACE 0,0,NORTH
Command: LEFT
Command: REPORT
0,0,WEST
Command: PLACE 1,2,EAST
Command: MOVE
Command: MOVE
Command: LEFT
Command: MOVE
Command: REPORT
3,3,NORTH
```
## Build and run instructions

- build 
```
./gradlew build
```
- package 
```
jar: ./gradlew jar
```
- run
```
java -jar /build/libs/toy-robot-simulator-1.0.jar
```
