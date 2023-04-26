package team;

import java.text.ParseException;
import java.util.List;

/**
 * The Driver class demonstrates the creation and management of a soccer team with players under 10
 * years old. It creates instances of TeamMember and adds them to the SoccerTeam. The class also
 * prints all players and the starting lineup of the team.
 */
public class Driver {

  /**
   * The main method serves as the entry point for the application. It initializes a SoccerTeam
   * object and can be used to demonstrate the functionality of the program or perform any required
   * tasks related to the SoccerTeam class.
   *
   * @param args An array of command-line arguments passed to the application.
   * @throws ParseException If there is a problem parsing date strings while creating team members.
   */
  public static void main(String[] args) {
    SoccerTeam team = new SoccerTeam();

    team.addPlayer(
        new TeamMember("John", "Doe", "2015-05-01", Position.DEFENDER, SkillLevel.LEVEL_3));
    team.addPlayer(
        new TeamMember("Jane", "Doe", "2014-06-10", Position.DEFENDER, SkillLevel.LEVEL_4));
    team.addPlayer(
        new TeamMember("Mark", "Smith", "2014-07-20", Position.DEFENDER, SkillLevel.LEVEL_4));
    team.addPlayer(
        new TeamMember("Mary", "Johnson", "2015-08-15", Position.MIDFIELDER, SkillLevel.LEVEL_3));
    team.addPlayer(
        new TeamMember("Paul", "Jones", "2015-09-10", Position.DEFENDER, SkillLevel.LEVEL_4));
    team.addPlayer(
        new TeamMember("Sophie", "Brown", "2015-10-05", Position.MIDFIELDER, SkillLevel.LEVEL_5));
    team.addPlayer(
        new TeamMember("James", "Garcia", "2015-11-25", Position.FORWARD, SkillLevel.LEVEL_4));
    team.addPlayer(
        new TeamMember("Laura", "Williams", "2014-12-15", Position.FORWARD, SkillLevel.LEVEL_3));
    team.addPlayer(
        new TeamMember("Peter", "Davis", "2014-01-15", Position.DEFENDER, SkillLevel.LEVEL_3));
    team.addPlayer(
        new TeamMember("Susan", "Miller", "2015-02-20", Position.DEFENDER, SkillLevel.LEVEL_3));
    team.addPlayer(
        new TeamMember("Mike", "Taylor", "2016-03-25", Position.MIDFIELDER, SkillLevel.LEVEL_1));
    team.addPlayer(
        new TeamMember("Nancy", "Anderson", "2016-04-10", Position.MIDFIELDER, SkillLevel.LEVEL_2));
    team.addPlayer(
        new TeamMember("Tom", "Lee", "2014-05-15", Position.DEFENDER, SkillLevel.LEVEL_2));
    team.addPlayer(
        new TeamMember("Angela", "Perez", "2014-06-05", Position.DEFENDER, SkillLevel.LEVEL_1));
    team.addPlayer(
        new TeamMember("Steve", "Lewis", "2014-07-20", Position.MIDFIELDER, SkillLevel.LEVEL_5));
    team.addPlayer(
        new TeamMember("Rita", "Young", "2015-08-25", Position.MIDFIELDER, SkillLevel.LEVEL_3));
    team.addPlayer(
        new TeamMember("Alan", "Hall", "2016-09-30", Position.DEFENDER, SkillLevel.LEVEL_3));
    team.addPlayer(
        new TeamMember("Diana", "Harris", "2015-10-15", Position.DEFENDER, SkillLevel.LEVEL_5));
    team.addPlayer(
        new TeamMember("Brian", "Clark", "2015-11-20", Position.FORWARD, SkillLevel.LEVEL_1));
    team.addPlayer(
        new TeamMember("Grace", "Walker", "2016-12-05", Position.FORWARD, SkillLevel.LEVEL_1));
    team.addPlayer(
        new TeamMember("Frank", "Rodriguez", "2016-01-10", Position.FORWARD, SkillLevel.LEVEL_2));
    team.addPlayer(
        new TeamMember("Linda", "Gonzalez", "2016-02-15", Position.DEFENDER, SkillLevel.LEVEL_2));

    System.out.println("All Players:");
    printTeamMembers(team.getAllPlayers());

    System.out.println("\nStarting Lineup:");
    printLineupPlayers(team.getStartingLineup());
  }

  /**
   * Prints the information of each TeamMember in the provided list.
   *
   * @param teamMembers a list of TeamMember objects to be printed
   */
  private static void printTeamMembers(List<TeamMember> teamMembers) {
    for (TeamMember player : teamMembers) {
      System.out.println(player);
    }
  }

  /**
   * Prints a formatted list of lineup players to the console.
   *
   * @param lineupPlayers the list of {@link TeamMember} objects representing the players in the
   *                      lineup.
   */
  private static void printLineupPlayers(List<TeamMember> lineupPlayers) {
    for (TeamMember player : lineupPlayers) {
      System.out.println(player);
    }
  }
}
