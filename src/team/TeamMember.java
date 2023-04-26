package team;

/**
 * The TeamMember class represents a team member in a sports team, specifically a soccer team. It
 * extends the BasePlayer class and provides additional properties and methods for a team member.
 */
public class TeamMember extends BasePlayer {
  private int jerseyNumber;
  private Position actualPosition;

  /**
   * Constructs a TeamMember object with the specified firstName, lastName, dateOfBirth,
   * preferredPosition, and skillLevel.
   *
   * @param firstName         the first name of the team member
   * @param lastName          the last name of the team member
   * @param dateOfBirth       the date of birth of the team member, as a string in the format
   *                          "yyyy-MM-dd"
   * @param preferredPosition the preferred position of the team member
   * @param skillLevel        the skill level of the team member
   */
  public TeamMember(String firstName, String lastName, String dateOfBirth,
      Position preferredPosition, SkillLevel skillLevel) {
    super(firstName, lastName, dateOfBirth, preferredPosition, skillLevel);
  }

  /**
   * Returns the jersey number of the team member.
   *
   * @return the jersey number
   */
  public int getJerseyNumber() {
    return jerseyNumber;
  }

  /**
   * Sets the jersey number of the team member.
   *
   * @param jerseyNumber the jersey number to set
   */
  public void setJerseyNumber(int jerseyNumber) {
    this.jerseyNumber = jerseyNumber;
  }

  /**
   * Gets the actual position of the {@link TeamMember} in the starting lineup.
   *
   * @return The actual {@link Position} of the {@link TeamMember} on the field.
   */
  public Position getActualPosition() {
    return actualPosition;
  }

  /**
   * Sets the actual position of the {@link TeamMember} in the starting lineup.
   *
   * @param actualPosition The actual {@link Position} to be assigned to the {@link TeamMember} on
   *                       the field.
   */
  public void setActualPosition(Position actualPosition) {
    this.actualPosition = actualPosition;
  }

  @Override public String toString() {
    Position positionToDisplay = actualPosition != null ? actualPosition : getPreferredPosition();
    return "Name: " + getFullName() + ", Jersey Number: " + getJerseyNumber() + ", Position: "
        + positionToDisplay;
  }
}
