# Soccer Team Management System

## About/Overview
The Soccer Team Management System is a Java-based application designed to simplify the process of managing a soccer team, particularly for a U10 age group. The program allows users to input and manage team members, while also helping coaches create a starting lineup based on player positions and skill levels.

## List of Features

- Add players with their first name, last name, date of birth, preferred position, and skill level.
- Display the list of all players on the team.
- Create a starting lineup based on the players' preferred positions and skill levels.
- Display the starting lineup.
- Validate player information (e.g., date of birth format).
- Automatically update the player list and starting lineup upon adding new players (when applicable).
- User-friendly graphical interface for smooth interaction.

## How To Run
### Running the Jar File
1. Ensure that you have the Java Runtime Environment (JRE) installed on your computer.
2. Open a command prompt or terminal window and navigate to the directory containing the Jar file.
3. Run the following command: java -jar soccer-team-management-system.jar

### Arguments
No arguments are needed to run the jar file.

## How to Use the Program
1. Launch the program by running the Jar file.
2. Enter the player's first name, last name, date of birth (in the format YYYY-MM-DD), preferred position, and skill level in the provided fields.
3. Click the "Add Player" button to add the player to the team.
4. Continue adding players until you have at least 10 players.
5. Click the "Create Team" button to generate the starting lineup based on the players' preferred positions and skill levels.
6. View the team list and starting lineup in the respective text areas.

## Design/Model Changes
- Version 1.0: Initial design and implementation.
- Version 1.1: Removed automatic team creation when 10 players were added. Added a "Create Team" button for manual team creation.

## Assumptions
- Player names will not exceed the JTextField size limits.
- Users will input the date of birth in the correct format (YYYY-MM-DD).
- Users will input valid player information.

## Limitations
- The program currently only supports a single team at a time.
- The program does not have the functionality to edit or remove players.
- The program does not save team data upon closing the application.

## Citations
No external sources were used during the development of this project.