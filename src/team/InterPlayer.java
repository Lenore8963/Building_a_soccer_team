package team;

/**
 * This interface represents a player on a soccer team. It defines methods for getting player
 * information such as name, date of birth, preferred position, skill level, and age.
 */
public interface InterPlayer {
  /**
   * Returns the first name of the player.
   *
   * @return the first name of the player
   */
  String getFirstName();

  /**
   * Returns the last name of the player.
   *
   * @return the last name of the player
   */
  String getLastName();

  /**
   * Returns the date of birth of the player.
   *
   * @return the date of birth of the player
   */
  String getDateOfBirth();

  /**
   * Returns the preferred position of the player on the field.
   *
   * @return the preferred position of the player
   */
  Position getPreferredPosition();

  /**
   * Returns the skill level of the player.
   *
   * @return the skill level of the player
   */
  SkillLevel getSkillLevel();

  /**
   * Returns the full name of the player (first name followed by last name).
   *
   * @return the full name of the player
   */
  String getFullName();

  /**
   * Returns the age of the player.
   *
   * @return the age of the player
   */
  int getAge();
}
