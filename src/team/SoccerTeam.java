package team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a soccer team that implements the {@link Team} interface. The team consists of players
 * under 10 years old with a minimum of 10 players and a maximum of 20 players. This class also
 * manages the starting lineup of the team, which consists of 7 players.
 */
public class SoccerTeam implements Team {
  private static final int MINIMUM_AGE = 10;
  private static final int MINIMUM_PLAYERS = 10;
  private static final int MAXIMUM_PLAYERS = 20;
  private List<TeamMember> players;
  private List<TeamMember> startingLineup;
  private final Map<Position, Integer> positionCounts = new HashMap<Position, Integer>() {{
      put(Position.GOALIE, 1);
      put(Position.DEFENDER, 2);
      put(Position.MIDFIELDER, 3);
      put(Position.FORWARD, 1);
    }};

  /**
   * Constructs a new SoccerTeam with empty lists for players and starting lineup.
   */
  public SoccerTeam() {
    players = new ArrayList<>();
    startingLineup = new ArrayList<>();
  }

  @Override public String addPlayer(TeamMember player) {
    int age = player.getAge();

    if (age >= MINIMUM_AGE) {
      return "Player not added. The team is for children under 10 years old.";
    }
    if (players.size() < MINIMUM_PLAYERS) {
      players.add(player);
      assignJerseyNumber(player);

      if (players.size() == MINIMUM_PLAYERS) {
        selectStartingLineup();
      } else {
        return "You need at least 10 players to create a team. "
            + "Player added, but team not created yet.";
      }
    } else if (players.size() < MAXIMUM_PLAYERS) {
      players.add(player);
      assignJerseyNumber(player);
      selectStartingLineup();
    } else {
      TeamMember lowestSkillPlayer = players.stream().min(
          Comparator.comparing(TeamMember::getSkillLevel)
              .thenComparing(TeamMember::getPreferredPosition)
              .thenComparing(TeamMember::getLastName)).orElse(null);

      if (lowestSkillPlayer != null
          && player.getSkillLevel().compareTo(lowestSkillPlayer.getSkillLevel()) > 0) {
        players.remove(lowestSkillPlayer);
        players.add(player);
        assignJerseyNumber(player);
        selectStartingLineup();
      } else {
        return "The team already has 20 players with higher or equal skill level.";
      }
    }

    return "Player added successfully.";
  }

  /**
   * Assigns a random jersey number to the given player that is not already assigned to any other
   * player in the team.
   *
   * @param player The {@link TeamMember} to assign a jersey number.
   */
  private void assignJerseyNumber(TeamMember player) {
    List<Integer> availableNumbers = IntStream.rangeClosed(1, MAXIMUM_PLAYERS)
        .filter(num -> players.stream().noneMatch(p -> p.getJerseyNumber() == num)).boxed()
        .collect(Collectors.toList());

    Random random = new Random();
    int randomIndex = random.nextInt(availableNumbers.size());
    int jerseyNumber = availableNumbers.get(randomIndex);

    player.setJerseyNumber(jerseyNumber);
  }

  /**
   * Selects the starting lineup for the team based on the players' skill levels, preferred
   * positions, and last names. This method is called when the team has at least the minimum number
   * of required players and when a new player is added.
   */
  private void selectStartingLineup() {
    players.sort(Comparator.comparing(TeamMember::getSkillLevel).reversed()
        .thenComparing(TeamMember::getPreferredPosition).thenComparing(TeamMember::getLastName));

    startingLineup.clear();
    Map<Position, Integer> positionCounts = new HashMap<>(this.positionCounts);

    for (TeamMember player : players) {
      Position position = player.getPreferredPosition();
      if (positionCounts.get(position) > 0 && !startingLineup.contains(player)) {
        player.setActualPosition(position);
        startingLineup.add(player);
        positionCounts.put(position, positionCounts.get(position) - 1);
      }
    }

    for (Map.Entry<Position, Integer> entry : positionCounts.entrySet()) {
      if (entry.getValue() > 0) {
        int requiredCount = entry.getValue();
        Position position = entry.getKey();

        List<TeamMember> remainingPlayers = players.stream()
            .filter(player -> startingLineup.stream().noneMatch(p -> p.equals(player)))
            .collect(Collectors.toList());

        remainingPlayers.sort(Comparator.comparing(TeamMember::getSkillLevel).reversed());

        for (int i = 0; i < requiredCount && i < remainingPlayers.size(); i++) {
          TeamMember player = remainingPlayers.get(i);
          if (!startingLineup.contains(player)) {
            player.setActualPosition(position);
            startingLineup.add(player);
          }
        }
      }
    }
  }

  /**
   * Creates a deep copy of the given {@link TeamMember} instance.
   *
   * @param player The {@link TeamMember} instance to be copied.
   * @return A new {@link TeamMember} instance with the same properties as the input player.
   */
  private TeamMember deepCopy(TeamMember player) {
    TeamMember copiedPlayer = new TeamMember(player.getFirstName(), player.getLastName(),
        player.getDateOfBirth(), player.getPreferredPosition(), player.getSkillLevel());
    copiedPlayer.setJerseyNumber(player.getJerseyNumber());
    return copiedPlayer;
  }

  @Override public List<TeamMember> getAllPlayers() {
    return players.stream().sorted(Comparator.comparing(TeamMember::getLastName))
        .map(this::deepCopy).collect(Collectors.toList());
  }

  @Override public List<TeamMember> getStartingLineup() {
    if (players.size() < MINIMUM_PLAYERS) {
      throw new IllegalStateException(
          "A soccer team must have at least 10 players to form a starting lineup.");
    }
    return startingLineup.stream().sorted(
            Comparator.comparing(TeamMember::getActualPosition)
                .thenComparing(TeamMember::getLastName)).collect(Collectors.toList());
  }
}
