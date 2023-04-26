package team;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The BasePlayer class is an abstract class that represents a player in a team. It implements the
 * IPlayer interface and provides common properties and methods for classes that extend it.
 */
public class BasePlayer implements InterPlayer {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  protected String firstName;
  protected String lastName;
  protected String dateOfBirth;
  protected Position preferredPosition;
  protected SkillLevel skillLevel;

  /**
   * Constructs a BasePlayer object with the specified firstName, lastName, dateOfBirth,
   * preferredPosition, and skillLevel.
   *
   * @param firstName         the first name of the player
   * @param lastName          the last name of the player
   * @param dateOfBirth       the date of birth of the player
   * @param preferredPosition the preferred position of the player
   * @param skillLevel        the skill level of the player
   */
  public BasePlayer(String firstName, String lastName, String dateOfBirth,
      Position preferredPosition, SkillLevel skillLevel) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.preferredPosition = preferredPosition;
    this.skillLevel = skillLevel;
  }

  @Override
  public String getFirstName() {

    return firstName;
  }

  @Override
  public String getLastName() {

    return lastName;
  }

  @Override
  public String getDateOfBirth() {

    return dateOfBirth;
  }

  @Override
  public Position getPreferredPosition() {

    return preferredPosition;
  }

  @Override
  public SkillLevel getSkillLevel() {

    return skillLevel;
  }

  @Override
  public String getFullName() {

    return firstName + " " + lastName;
  }

  @Override
  public int getAge() {
    Calendar dob = Calendar.getInstance();
    try {
      dob.setTime(dateFormat.parse(dateOfBirth)); // Parse the date here
    } catch (ParseException e) {
      return -1; // Return an error value or throw an exception
    }
    Calendar today = Calendar.getInstance();

    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

    if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH) || (
        today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
            && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH))) {
      age--;
    }

    return age;
  }
}
