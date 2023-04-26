import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import team.Position;
import team.SkillLevel;
import team.SoccerTeam;
import team.Team;
import team.TeamMember;

/**
 * Unit tests for the {@link SoccerTeam} class, which implements the {@link Team} interface. Tests
 * cover various scenarios for adding players, retrieving player information, and ensuring the
 * correct behavior of the starting lineup.
 */
public class SoccerTeamTest {

  private Team soccerTeam;

  @Before
  public void setUp() {
    soccerTeam = new SoccerTeam();
  }

  /**
   * Tests the addition of a single player and the expected response when the team is not yet
   * created due to an insufficient number of players.
   */
  @Test
  public void testAddPlayer() {
    TeamMember player = new TeamMember("John", "Doe", "2013-05-01", Position.GOALIE,
        SkillLevel.LEVEL_3);
    assertEquals(
        "You need at least 10 players to create a team. Player added, but team not created yet.",
        soccerTeam.addPlayer(player));
  }

  /**
   * Tests the addition of more than 20 players with lower skill levels, ensuring that the 21st
   * player is not added.
   */
  @Test
  public void testAddMoreThan20PlayersWithLowLevel() {
    for (int i = 1; i <= 20; i++) {
      TeamMember player = new TeamMember("Player" + i, "LastName" + i, "2013-05-01",
          Position.GOALIE, SkillLevel.LEVEL_3);
      soccerTeam.addPlayer(player);
    }
    TeamMember player21 = new TeamMember("Player21", "LastName21", "2013-05-01", Position.GOALIE,
        SkillLevel.LEVEL_3);
    String message = soccerTeam.addPlayer(player21);
    assertEquals("The team already has 20 players with higher or equal skill level.", message);
  }

  /**
   * Tests the addition of more than 20 players, with the 21st player having a higher skill level,
   * ensuring the 21st player is added successfully.
   */
  @Test
  public void testAddMoreThan20PlayersWithHigherLevel() {
    for (int i = 1; i <= 20; i++) {
      TeamMember player = new TeamMember("Player" + i, "LastName" + i, "2013-05-01",
          Position.GOALIE, SkillLevel.LEVEL_3);
      soccerTeam.addPlayer(player);
    }
    TeamMember player21 = new TeamMember("Player21", "LastName21", "2013-05-01", Position.GOALIE,
        SkillLevel.LEVEL_5);
    String message = soccerTeam.addPlayer(player21);
    assertEquals("Player added successfully.", message);
  }

  /**
   * Tests the addition of a player older than 10 years, ensuring that the player is not added.
   */
  @Test
  public void testAddPlayerOlderThanTen() {
    TeamMember player = new TeamMember("John", "Doe", "2009-01-01", Position.GOALIE,
        SkillLevel.LEVEL_3);
    assertEquals("Player not added. The team is for children under 10 years old.",
        soccerTeam.addPlayer(player));
  }

  /**
   * Tests the retrieval of a player's first name.
   */
  @Test
  public void testGetFirstName() {
    TeamMember player = new TeamMember("John", "Doe", "2013-01-01", Position.GOALIE,
        SkillLevel.LEVEL_3);

    String firstName = player.getFirstName();

    assertEquals("John", firstName);
  }

  /**
   * Tests the retrieval of a player's last name.
   */
  @Test
  public void testGetLastName() {
    TeamMember player = new TeamMember("John", "Doe", "2013-01-01", Position.GOALIE,
        SkillLevel.LEVEL_3);

    String lastName = player.getLastName();

    assertEquals("Doe", lastName);
  }

  /**
   * Tests the retrieval of a player's date of birth.
   */
  @Test
  public void testGetDateOfBirth() {
    String dob = "2013-01-01";
    TeamMember player = new TeamMember("John", "Doe", dob, Position.GOALIE, SkillLevel.LEVEL_3);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateOfBirth = player.getDateOfBirth();

    assertEquals(dob, dateOfBirth);
  }

  /**
   * Tests the retrieval of the starting lineup when there are fewer than 10 players on the team,
   * expecting an IllegalStateException to be thrown.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetStartingLineupWithLessThan10Players() {
    for (int i = 1; i <= 5; i++) {
      TeamMember player = new TeamMember("Player" + i, "LastName" + i, "2013-05-01",
          Position.GOALIE, SkillLevel.LEVEL_3);
      soccerTeam.addPlayer(player);
    }
    soccerTeam.getStartingLineup();
  }

  /**
   * Test that verifies all players in the team are sorted alphabetically by last name.
   */
  @Test
  public void testAllPlayersSortedAlphabetically() {
    soccerTeam.addPlayer(
        new TeamMember("John", "Doe", "2014-05-01", Position.GOALIE, SkillLevel.LEVEL_3));
    soccerTeam.addPlayer(
        new TeamMember("Jane", "Doe", "2015-06-10", Position.DEFENDER, SkillLevel.LEVEL_4));
    soccerTeam.addPlayer(
        new TeamMember("Mark", "Smith", "2014-07-20", Position.DEFENDER, SkillLevel.LEVEL_4));
    soccerTeam.addPlayer(
        new TeamMember("Mary", "Johnson", "2014-08-15", Position.MIDFIELDER, SkillLevel.LEVEL_3));
    soccerTeam.addPlayer(
        new TeamMember("Paul", "Jones", "2014-09-10", Position.GOALIE, SkillLevel.LEVEL_4));
    soccerTeam.addPlayer(
        new TeamMember("Sophie", "Brown", "2014-10-05", Position.MIDFIELDER, SkillLevel.LEVEL_5));
    soccerTeam.addPlayer(
        new TeamMember("James", "Garcia", "2015-11-25", Position.FORWARD, SkillLevel.LEVEL_4));
    soccerTeam.addPlayer(
        new TeamMember("Laura", "Williams", "2015-12-15", Position.FORWARD, SkillLevel.LEVEL_3));
    soccerTeam.addPlayer(
        new TeamMember("Peter", "Davis", "2014-01-15", Position.DEFENDER, SkillLevel.LEVEL_3));
    soccerTeam.addPlayer(
        new TeamMember("Susan", "Miller", "2015-02-20", Position.DEFENDER, SkillLevel.LEVEL_3));

    List<TeamMember> allPlayers = soccerTeam.getAllPlayers();

    List<TeamMember> sortedPlayers = allPlayers.stream().sorted(
            Comparator.comparing(TeamMember::getLastName).thenComparing(TeamMember::getFirstName))
        .collect(Collectors.toList());

    assertEquals(sortedPlayers, allPlayers);
  }

  /**
   * Test that verifies the list of all players in the team is sorted alphabetically.
   */
  @Test
  public void testGetAllPlayersSortedAlphabetically() {
    soccerTeam.addPlayer(
        new TeamMember("Alice", "Brown", "2013-05-01", Position.MIDFIELDER, SkillLevel.LEVEL_2));
    soccerTeam.addPlayer(
        new TeamMember("Charlie", "Adams", "2014-01-15", Position.DEFENDER, SkillLevel.LEVEL_3));
    soccerTeam.addPlayer(
        new TeamMember("Eve", "Smith", "2012-08-20", Position.FORWARD, SkillLevel.LEVEL_1));

    for (int i = 0; i < 7; i++) {
      soccerTeam.addPlayer(
          new TeamMember("Player" + i, "Extra" + i, "2014-06-10", Position.MIDFIELDER,
              SkillLevel.LEVEL_1));
    }

    List<TeamMember> allPlayers = soccerTeam.getAllPlayers();

    List<String> expectedSortedLastNames = new ArrayList<String>();
    for (TeamMember player : allPlayers) {
      expectedSortedLastNames.add(player.getLastName());
    }
    expectedSortedLastNames.sort(String::compareTo);

    for (int i = 0; i < allPlayers.size(); i++) {
      assertEquals(expectedSortedLastNames.get(i), allPlayers.get(i).getLastName());
    }
  }

  /**
   * Test that verifies the team's starting lineup is sorted by position and alphabetically for
   * players with the same position.
   */
  @Test
  public void testStartingLineupSortedByPositionAndAlphabetically() {
    soccerTeam.addPlayer(
        new TeamMember("John", "Smith", "2015-06-15", Position.GOALIE, SkillLevel.LEVEL_3));
    soccerTeam.addPlayer(
        new TeamMember("Jane", "Doe", "2016-02-20", Position.MIDFIELDER, SkillLevel.LEVEL_4));
    soccerTeam.addPlayer(
        new TeamMember("Mark", "Johnson", "2017-11-30", Position.DEFENDER, SkillLevel.LEVEL_2));
    soccerTeam.addPlayer(
        new TeamMember("Sara", "Jackson", "2017-08-25", Position.FORWARD, SkillLevel.LEVEL_5));
    soccerTeam.addPlayer(
        new TeamMember("Lucy", "Brown", "2015-04-18", Position.DEFENDER, SkillLevel.LEVEL_1));
    soccerTeam.addPlayer(
        new TeamMember("Matt", "Garcia", "2016-12-05", Position.MIDFIELDER, SkillLevel.LEVEL_3));
    soccerTeam.addPlayer(
        new TeamMember("Alex", "Harris", "2016-09-10", Position.MIDFIELDER, SkillLevel.LEVEL_4));
    soccerTeam.addPlayer(
        new TeamMember("Lily", "Davis", "2017-07-12", Position.DEFENDER, SkillLevel.LEVEL_2));
    soccerTeam.addPlayer(
        new TeamMember("Ella", "Wilson", "2014-05-22", Position.MIDFIELDER, SkillLevel.LEVEL_5));
    soccerTeam.addPlayer(
        new TeamMember("Noah", "Taylor", "2014-03-08", Position.FORWARD, SkillLevel.LEVEL_1));
    soccerTeam.addPlayer(
        new TeamMember("Sophia", "Young", "2014-06-15", Position.DEFENDER, SkillLevel.LEVEL_2));
    soccerTeam.addPlayer(new TeamMember("Oliver", "Martinez", "2017-02-20", Position.MIDFIELDER,
        SkillLevel.LEVEL_3));
    soccerTeam.addPlayer(
        new TeamMember("Emily", "Miller", "2017-07-10", Position.MIDFIELDER, SkillLevel.LEVEL_4));
    soccerTeam.addPlayer(
        new TeamMember("Chloe", "Gonzalez", "2015-04-18", Position.DEFENDER, SkillLevel.LEVEL_1));
    soccerTeam.addPlayer(
        new TeamMember("Benjamin", "Lopez", "2015-11-30", Position.FORWARD, SkillLevel.LEVEL_2));
    List<TeamMember> startingLineup = soccerTeam.getStartingLineup();

    List<String> actualNames = new ArrayList<>();
    for (TeamMember player : startingLineup) {
      actualNames.add(player.getLastName());
    }

    List<String> expectedNames = Arrays.asList("Smith", // Goalie
        "Davis", // Defender
        "Johnson", // Defender
        "Doe", // Midfielder
        "Harris", // Midfielder
        "Wilson", // Midfielder
        "Jackson" // Forward
    );

    assertEquals(expectedNames, actualNames);
  }

  /**
   * Test that verifies the toString() method of the TeamMember class, checking if the output
   * matches the expected string representation.
   */
  @Test
  public void testToString() {
    TeamMember teamMember = new TeamMember("John", "Smith", "2013-06-15", Position.GOALIE,
        SkillLevel.LEVEL_3);
    teamMember.setJerseyNumber(1);

    String expectedStringRepresentation = "Name: John Smith, Jersey Number: 1, Position: GOALIE";
    String actualStringRepresentation = teamMember.toString();

    assertEquals("TeamMember toString() does not match expected output",
        expectedStringRepresentation, actualStringRepresentation);
  }

  /**
   * Tests the {@link TeamMember#getAge()} method with valid date-of-birth strings. Ensures that the
   * calculated ages are correct based on the current date.
   */
  @Test
  public void testGetAgeWithValidDate() {
    TeamMember player1 = new TeamMember("John", "Doe", "2010-05-01", Position.DEFENDER,
        SkillLevel.LEVEL_3);
    TeamMember player2 = new TeamMember("Jane", "Doe", "2012-08-15", Position.DEFENDER,
        SkillLevel.LEVEL_4);
    TeamMember player3 = new TeamMember("Mark", "Smith", "2015-02-20", Position.DEFENDER,
        SkillLevel.LEVEL_4);

    java.util.Calendar currentDate = java.util.Calendar.getInstance();
    int currentYear = currentDate.get(java.util.Calendar.YEAR);
    int currentMonth = currentDate.get(java.util.Calendar.MONTH);
    int currentDay = currentDate.get(java.util.Calendar.DAY_OF_MONTH);

    int expectedAgePlayer1 =
        currentYear - 2010 - (currentMonth < 5 || (currentMonth == 5 && currentDay < 1) ? 1 : 0);
    int expectedAgePlayer2 =
        currentYear - 2012 - (currentMonth < 8 || (currentMonth == 8 && currentDay < 15) ? 1 : 0);
    int expectedAgePlayer3 =
        currentYear - 2015 - (currentMonth < 2 || (currentMonth == 2 && currentDay < 20) ? 1 : 0);

    assertEquals(expectedAgePlayer1, player1.getAge());
    assertEquals(expectedAgePlayer2, player2.getAge());
    assertEquals(expectedAgePlayer3, player3.getAge());
  }

  /**
   * Tests the {@link TeamMember#getAge()} method with an invalid date-of-birth string. Ensures that
   * the method returns -1 when encountering a ParseException.
   */
  @Test
  public void testGetAgeParseException() {
    TeamMember player1 = new TeamMember("John", "Doe", "invalid-date-format", Position.DEFENDER,
        SkillLevel.LEVEL_3);

    int actualAgePlayer1 = player1.getAge();
    assertEquals(-1, actualAgePlayer1);
  }
}



