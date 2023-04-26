package team;

import java.util.List;

/**
 * Represents a team in a sports organization. Provides methods to add a player to the team,
 * retrieve all players on the team, and obtain the starting lineup.
 */
public interface Team {

  /**
   * Adds a player to the team. The method may also update the starting lineup if necessary. The
   * specific rules for adding players and updating the lineup may vary depending on the
   * implementation.
   *
   * @param player The {@link TeamMember} to be added to the team.
   * @return A string message indicating the result of the operation (e.g., success, failure, or any
   *     other relevant information).
   */
  String addPlayer(TeamMember player);

  /**
   * Retrieves a list of all players on the team, sorted by their last names.
   *
   * @return A {@link List} of {@link TeamMember} objects representing all players on the team.
   */
  List<TeamMember> getAllPlayers();

  /**
   * Retrieves the starting lineup for the team. The specific rules for selecting the starting
   * lineup may vary depending on the implementation.
   *
   * @return A {@link List} of {@link TeamMember} objects representing the starting lineup.
   * @throws IllegalStateException if there are not enough players to form a starting lineup.
   */
  List<TeamMember> getStartingLineup();
}
