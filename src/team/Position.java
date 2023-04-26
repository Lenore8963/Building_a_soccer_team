package team;

/**
 * This enum represents the positions that players can occupy on a soccer team. Each position has a
 * maximum number of players that can play in that position in a starting lineup.
 */
public enum Position {
  GOALIE(1), DEFENDER(2), MIDFIELDER(3), FORWARD(1);

  private final int maxPlayers;

  /**
   * Constructs a new Position with the specified maximum number of players allowed in the starting
   * lineup.
   *
   * @param maxPlayers the maximum number of players allowed in the starting lineup for this
   *                   position
   */
  Position(int maxPlayers) {

    this.maxPlayers = maxPlayers;
  }
}
