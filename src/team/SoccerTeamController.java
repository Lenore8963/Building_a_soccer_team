package team;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SoccerTeamController class is responsible for managing the interactions between the
 * SoccerTeamView and the Team model. It handles user actions, updates the model, and refreshes the
 * view accordingly.
 */
public class SoccerTeamController {
  private SoccerTeamView view;
  private Team team;

  /**
   * Constructs a SoccerTeamController with the specified SoccerTeamView and Team model.
   *
   * @param view the SoccerTeamView instance to be managed by this controller
   * @param team the Team model instance to be managed by this controller
   */
  public SoccerTeamController(SoccerTeamView view, Team team) {
    this.view = view;
    this.team = team;

    this.view.addAddPlayerButtonListener(new AddPlayerListener());
    this.view.addCreateTeamButtonListener(new CreateTeamListener());
  }

  /**
   * The AddPlayerListener is an ActionListener that handles the "Add Player" button events. It
   * retrieves the player data from the view, validates the date of birth, updates the model, and
   * refreshes the view accordingly.
   */
  private class AddPlayerListener implements ActionListener {
    @Override public void actionPerformed(ActionEvent e) {
      TeamMember player = view.getPlayerFromForm();

      if (player.getAge() == -1) {
        view.showMessage("Please enter a valid date of birth in the format YYYY-MM-DD.");
        return; // Do not proceed if the date of birth is invalid
      }

      String message = team.addPlayer(player);
      view.showMessage(message);

      // Update the player list immediately after adding a player
      view.displayAllPlayers(team.getAllPlayers());

      view.clearForm();
    }
  }

  /**
   * The CreateTeamListener is an ActionListener that handles the "Create Team" button events. It
   * checks if the team has at least 10 players, and if so, it displays the team list and starting
   * lineup in the view. Otherwise, it shows an error message.
   */
  private class CreateTeamListener implements ActionListener {
    @Override public void actionPerformed(ActionEvent e) {
      if (team.getAllPlayers().size() < 10) {
        view.showMessage("You need at least 10 players to create a team.");
      } else {
        view.displayAllPlayers(team.getAllPlayers());
        view.displayStartingLineup(team.getStartingLineup());
        view.showMessage("Team created successfully.");
      }
    }
  }
}
