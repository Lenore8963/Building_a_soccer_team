package team;

import javax.swing.SwingUtilities;

/**
 * This is the Main class for the U10 Soccer Team application. It initializes the Model, View, and
 * Controller components and sets up the application window.
 * <p>
 * The application allows users to add players to a U10 Soccer Team, display the team list, and
 * create a starting lineup based on the players' skill levels.
 */
public class Main {
  /**
   * The entry point of the U10 Soccer Team application.
   * <p>
   * The main method is responsible for initializing the SoccerTeamView, SoccerTeam (as Team), and
   * SoccerTeamController components. It also sets the application window visible.
   *
   * @param args The command-line arguments. This application does not use command-line arguments.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      SoccerTeamView view = new SoccerTeamView();
      view.setVisible(true);

      Team teamModel = new SoccerTeam(); // Create an instance of SoccerTeam
      SoccerTeamController controller = new SoccerTeamController(view,
          teamModel); // Pass the instance to the constructor
    });
  }
}