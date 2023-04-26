package team;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The SoccerTeamView class represents the user interface for managing a U10 soccer team. It allows
 * users to input player details, add players to the team, and create the team with a starting
 * lineup. It extends the JFrame class to create a graphical user interface.
 */
public class SoccerTeamView extends JFrame {
  private JPanel mainPanel;
  private JTextField firstNameField;
  private JTextField lastNameField;
  private JTextField dateOfBirthField;
  private JComboBox<Position> positionComboBox;
  private JComboBox<SkillLevel> skillLevelComboBox;
  private JButton addButton;
  private JButton createTeamButton;
  private JTextArea teamListTextArea;
  private JTextArea startingLineupTextArea;

  /**
   * Constructs a SoccerTeamView instance, initializing the user interface components and setting up
   * the layout.
   */
  public SoccerTeamView() {
    super();
    setTitle("U10 Soccer Team");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set the main panel layout
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    // Create the form panel
    JPanel formPanel = new JPanel(new GridLayout(0, 2));
    formPanel.add(new JLabel("First Name:"));
    firstNameField = new JTextField();
    formPanel.add(firstNameField);
    formPanel.add(new JLabel("Last Name:"));
    lastNameField = new JTextField();
    formPanel.add(lastNameField);
    formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
    dateOfBirthField = new JTextField();
    formPanel.add(dateOfBirthField);
    formPanel.add(new JLabel("Position:"));
    positionComboBox = new JComboBox<>();
    formPanel.add(positionComboBox);
    formPanel.add(new JLabel("Skill Level:"));
    skillLevelComboBox = new JComboBox<>();
    formPanel.add(skillLevelComboBox);

    // Create buttons panel
    JPanel buttonsPanel = new JPanel();
    addButton = new JButton("Add Player");
    buttonsPanel.add(addButton);
    createTeamButton = new JButton("Create Team");
    buttonsPanel.add(createTeamButton);

    // Add form panel and buttons panel to main panel
    mainPanel.add(formPanel);
    mainPanel.add(buttonsPanel);

    // Create and add the team list and starting lineup panels
    JPanel teamListPanel = new JPanel(new BorderLayout());
    teamListPanel.setBorder(BorderFactory.createTitledBorder("Team List"));
    teamListTextArea = new JTextArea(10, 20);
    teamListPanel.add(new JScrollPane(teamListTextArea), BorderLayout.CENTER);
    JPanel listsPanel = new JPanel(new GridLayout(1, 2));
    listsPanel.add(teamListPanel);
    JPanel startingLineupPanel = new JPanel(new BorderLayout());
    startingLineupPanel.setBorder(BorderFactory.createTitledBorder("Starting Lineup"));
    startingLineupTextArea = new JTextArea(10, 20);
    startingLineupPanel.add(new JScrollPane(startingLineupTextArea), BorderLayout.CENTER);
    listsPanel.add(startingLineupPanel);

    mainPanel.add(listsPanel);

    positionComboBox.setModel(new DefaultComboBoxModel<>(Position.values()));
    skillLevelComboBox.setModel(new DefaultComboBoxModel<>(SkillLevel.values()));

    setContentPane(mainPanel);
    pack();
    setLocationRelativeTo(null);
  }

  /**
   * Registers an ActionListener for the "Add Player" button.
   *
   * @param listener the ActionListener to be registered
   */
  public void addAddPlayerButtonListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }

  /**
   * Registers an ActionListener for the "Create Team" button.
   *
   * @param listener the ActionListener to be registered
   */
  public void addCreateTeamButtonListener(ActionListener listener) {
    createTeamButton.addActionListener(listener);
  }

  /**
   * Retrieves the player data from the form and returns a new TeamMember instance.
   *
   * @return a new TeamMember instance with the data from the form
   */
  public TeamMember getPlayerFromForm() {
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String dateOfBirth = dateOfBirthField.getText();
    Position preferredPosition = (Position) positionComboBox.getSelectedItem();
    SkillLevel skillLevel = (SkillLevel) skillLevelComboBox.getSelectedItem();

    return new TeamMember(firstName, lastName, dateOfBirth, preferredPosition, skillLevel);
  }

  /**
   * Clears the input fields in the form.
   */
  public void clearForm() {
    firstNameField.setText("");
    lastNameField.setText("");
    dateOfBirthField.setText("");
  }

  /**
   * Displays a list of players in the team list text area.
   *
   * @param players a list of TeamMember instances representing the players
   */
  public void displayAllPlayers(List<TeamMember> players) {
    teamListTextArea.setText("");
    for (TeamMember player : players) {
      teamListTextArea.append(player.toString() + "\n");
    }
  }

  /**
   * Displays a list of players in the starting lineup text area.
   *
   * @param lineup a list of TeamMember instances representing the starting lineup
   */
  public void displayStartingLineup(List<TeamMember> lineup) {
    startingLineupTextArea.setText("");
    for (TeamMember player : lineup) {
      startingLineupTextArea.append(player.toString() + "\n");
    }
  }

  /**
   * Displays a message in a dialog box.
   *
   * @param message the message to be displayed
   */
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}