package verbargo.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import verbargo.exception.EmptyTeamException;
import verbargo.exception.FullTeamException;
import verbargo.exception.InvalidFileFormatException;
import verbargo.exception.NoSuchPlayerException;
import verbargo.game.verbargoGameManger;
import verbargo.game.verbargoOptions;
import verbargo.limits.vLimits;
import verbargo.team.vTeam;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.Toolkit;

public class VerbargoGUI implements ActionListener, vLimits {

	JFrame frmVerbargoV;
	private verbargoGameManger vgm;
	private verbargoOptions options;
	private JFileChooser fileChooser;
	private File verbFile;
	private JComboBox<String> teamcombobox;
	private JTextField teamtextfield;
	private JTextField playertextfield;
	private JPanel MainVerbargopnl;
	private JLabel lblVerbargo;
	private JButton startbtn;
	private JButton addteambtn;
	private JButton deleteteambtn;
	private JButton lockteambtn;
	private JButton addplayerbtn;
	private JButton deleteplayerbtn;
	private JButton aboutbtn;
	private JButton quitbtn;
	private JButton optionsbtn;
	private JTextArea teamplayerstextarea;
	private JButton showplayersbtn;
	private Color[] color;
	private JLabel currentdatabase;
	private JLabel currentAuthor;
	private OptionGUI opt;
	@SuppressWarnings("unused")
	private VerbargoDiffChoose vCardGameStart;
	private Random rand = new Random();

	/**
	 * Create the application.
	 */
	public VerbargoGUI(String fileName) {
		initialize();
		color = new Color[5];
		color[0] = new Color(51, 255, 255);
		color[1] = new Color(204, 51, 255);
		color[2] = new Color(0, 255, 0);
		color[3] = new Color(255, 255, 0);
		color[4] = new Color(204, 102, 0);
		options = new verbargoOptions();
		if(fileName == null){
			try {
				vgm = new verbargoGameManger(findFile());
				currentdatabase.setText(vgm.getDatabaseName());
				currentAuthor.setText(vgm.getDatabaseAuthor());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frmVerbargoV.getContentPane(), e.getMessage());
				System.exit(0);
			} catch (InvalidFileFormatException e) {
				JOptionPane.showMessageDialog(frmVerbargoV.getContentPane(), e.getMessage());
				System.exit(0);
			}
		} else {
			try {
				vgm = new verbargoGameManger(fileName);
				currentdatabase.setText(vgm.getDatabaseName());
				currentAuthor.setText(vgm.getDatabaseAuthor());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frmVerbargoV.getContentPane(), e.getMessage());
				System.exit(0);
			} catch (InvalidFileFormatException e) {
				JOptionPane.showMessageDialog(frmVerbargoV.getContentPane(), e.getMessage());
				System.exit(0);
			}
		}
	}

	private String findFile() {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		int jfcoption = fileChooser.showOpenDialog(frmVerbargoV);
		if(jfcoption != JFileChooser.APPROVE_OPTION){
			System.exit(0);
		} else {
			verbFile = fileChooser.getSelectedFile();
		}
		String fileName = verbFile.getAbsolutePath();
		while(!fileName.endsWith("verb")){
			JOptionPane.showMessageDialog(frmVerbargoV, "Select an VERB file.");
			jfcoption = fileChooser.showOpenDialog(frmVerbargoV);
			if(jfcoption == JFileChooser.CANCEL_OPTION){
				System.exit(0);
			} else if (jfcoption == JFileChooser.APPROVE_OPTION) {
				verbFile = fileChooser.getSelectedFile();
				fileName = verbFile.getAbsolutePath();
			}
		}
		return fileName;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVerbargoV = new JFrame();
		frmVerbargoV.getContentPane().setBackground(new Color(199, 21, 133));
		frmVerbargoV.setTitle("Verbargo v. 1.5");
		frmVerbargoV.setResizable(false);
		frmVerbargoV.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Samsung\\workspace\\Verbargo 1.5\\VerbargoAppIcon.png"));
		frmVerbargoV.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 12));
		frmVerbargoV.setBounds(100, 100, 521, 288);
		frmVerbargoV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVerbargoV.getContentPane().setLayout(null);
		
		MainVerbargopnl = new JPanel();
		MainVerbargopnl.setBackground(new Color(255, 255, 255));
		MainVerbargopnl.setBounds(0, 0, 515, 261);
		frmVerbargoV.getContentPane().add(MainVerbargopnl);
		MainVerbargopnl.setLayout(null);
		
		lblVerbargo = new JLabel("Verbargo");
		lblVerbargo.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		lblVerbargo.setBounds(10, 0, 104, 43);
		MainVerbargopnl.add(lblVerbargo);
		
		aboutbtn = new JButton("About ");
		aboutbtn.setForeground(new Color(0, 0, 0));
		aboutbtn.setBackground(new Color(230, 230, 250));
		aboutbtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		aboutbtn.setBounds(124, 10, 89, 23);
		aboutbtn.addActionListener(this);
		MainVerbargopnl.add(aboutbtn);
		
		JLabel teamnamelbl = new JLabel("Team Name");
		teamnamelbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
		teamnamelbl.setBounds(25, 75, 89, 23);
		MainVerbargopnl.add(teamnamelbl);
		
		quitbtn = new JButton("Quit");
		quitbtn.setForeground(Color.BLACK);
		quitbtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		quitbtn.setBackground(new Color(230, 230, 250));
		quitbtn.setBounds(346, 10, 89, 23);
		quitbtn.addActionListener(this);
		MainVerbargopnl.add(quitbtn);
		
		JLabel playernamelbl = new JLabel("Player Name");
		playernamelbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
		playernamelbl.setBounds(193, 105, 89, 23);
		MainVerbargopnl.add(playernamelbl);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(141, 75, 8, 175);
		MainVerbargopnl.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(315, 75, 8, 175);
		MainVerbargopnl.add(separator_1);
		
		teamcombobox = new JComboBox<String>();
		teamcombobox.setEnabled(true);
		teamcombobox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		teamcombobox.setBounds(159, 77, 146, 20);
		teamcombobox.addActionListener(this);
		
		teamtextfield = new JTextField();
		teamtextfield.setDocument(new JTextFieldCharLimit(20));
		teamtextfield.setBackground(new Color(255, 255, 153));
		teamtextfield.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		teamtextfield.setBounds(10, 106, 121, 20);
		MainVerbargopnl.add(teamtextfield);
		teamtextfield.setColumns(10);
		
		startbtn = new JButton("START");
		startbtn.setForeground(Color.BLACK);
		startbtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		startbtn.setBackground(new Color(102, 102, 102));
		startbtn.setBounds(424, 227, 81, 23);
		startbtn.setEnabled(false);
		startbtn.addActionListener(this);
		MainVerbargopnl.add(startbtn);
		
		playertextfield = new JTextField();
		playertextfield.setBackground(new Color(204, 153, 255));
		playertextfield.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		playertextfield.setColumns(10);
		playertextfield.setBounds(159, 139, 146, 20);
		playertextfield.setEnabled(false);
		MainVerbargopnl.add(playertextfield);
		
		addteambtn = new JButton("Add Team");
		addteambtn.setForeground(Color.BLACK);
		addteambtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		addteambtn.setBackground(new Color(51, 255, 255));
		addteambtn.setBounds(10, 137, 121, 23);
		addteambtn.addActionListener(this);
		MainVerbargopnl.add(addteambtn);
		
		deleteteambtn = new JButton("Delete Team");
		deleteteambtn.setForeground(Color.BLACK);
		deleteteambtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		deleteteambtn.setBackground(new Color(204, 51, 255));
		deleteteambtn.setBounds(10, 171, 121, 23);
		deleteteambtn.addActionListener(this);
		MainVerbargopnl.add(deleteteambtn);
		
		lockteambtn = new JButton("Lock Teams");
		lockteambtn.setForeground(Color.BLACK);
		lockteambtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lockteambtn.setBackground(new Color(0, 255, 0));
		lockteambtn.setBounds(10, 205, 121, 23);
		lockteambtn.addActionListener(this);
		MainVerbargopnl.add(lockteambtn);
		
		addplayerbtn = new JButton("Add Player");
		addplayerbtn.setForeground(Color.BLACK);
		addplayerbtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		addplayerbtn.setBackground(new Color(255, 255, 0));
		addplayerbtn.setBounds(161, 170, 144, 23);
		addplayerbtn.setEnabled(false);
		addplayerbtn.addActionListener(this);
		MainVerbargopnl.add(addplayerbtn);
		
		deleteplayerbtn = new JButton("Delete Player");
		deleteplayerbtn.setForeground(Color.BLACK);
		deleteplayerbtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		deleteplayerbtn.setBackground(new Color(204, 102, 0));
		deleteplayerbtn.setBounds(159, 206, 144, 23);
		deleteplayerbtn.setEnabled(false);
		deleteplayerbtn.addActionListener(this);
		MainVerbargopnl.add(deleteplayerbtn);
		
		teamplayerstextarea = new JTextArea();
		teamplayerstextarea.setLineWrap(true);
		teamplayerstextarea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		teamplayerstextarea.setBounds(333, 57, 161, 159);
		MainVerbargopnl.add(teamplayerstextarea);
		
		optionsbtn = new JButton("Options");
		optionsbtn.setForeground(Color.BLACK);
		optionsbtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		optionsbtn.setBackground(new Color(230, 230, 250));
		optionsbtn.setBounds(234, 11, 89, 23);
		optionsbtn.addActionListener(this);
		MainVerbargopnl.add(optionsbtn);
		
		showplayersbtn = new JButton("Show vTeam");
		showplayersbtn.setEnabled(false);
		showplayersbtn.setBackground(new Color(255, 255, 255));
		showplayersbtn.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		showplayersbtn.setBounds(325, 227, 89, 23);
		showplayersbtn.addActionListener(this);
		MainVerbargopnl.add(showplayersbtn);
		
		JLabel databaselbl = new JLabel("Database:");
		databaselbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		databaselbl.setBounds(10, 45, 54, 14);
		MainVerbargopnl.add(databaselbl);
		
		currentdatabase = new JLabel("cDatabase");
		currentdatabase.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		currentdatabase.setBounds(65, 45, 94, 14);
		MainVerbargopnl.add(currentdatabase);
		
		JLabel authorlbl = new JLabel("Author:");
		authorlbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		authorlbl.setBounds(170, 44, 54, 14);
		MainVerbargopnl.add(authorlbl);
		
		currentAuthor = new JLabel("cAuthor");
		currentAuthor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		currentAuthor.setBounds(216, 45, 117, 14);
		MainVerbargopnl.add(currentAuthor);
		MainVerbargopnl.validate();
		MainVerbargopnl.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int setcolor = rand.nextInt(5);
		this.MainVerbargopnl.setBackground(color[setcolor]);
		if(e.getSource() == this.quitbtn){
			System.exit(0);
		} else if (e.getSource() == this.addteambtn){
			if(teamtextfield.getText() == null || teamtextfield.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(frmVerbargoV, "Invalid Team Name!"); 
			} else {
				for (int i = 0; i < vgm.vteammanager.size(); i++) {
					if(vgm.vteammanager.get(i).getTeamName().equalsIgnoreCase(teamtextfield.getText().trim())){
						JOptionPane.showMessageDialog(frmVerbargoV, "Team Already Exists");
						return;
					}
				}
				vTeam newTeam = new vTeam(teamtextfield.getText().trim());
				vgm.vteammanager.add(newTeam);
			}			
			teamtextfield.setText("");
		} else if (e.getSource() == this.deleteteambtn) {
			if(teamtextfield.getText() == null || teamtextfield.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(frmVerbargoV, "Invalid Team Name"); 
			} else {
				if(vgm.vteammanager.isEmpty()){
					JOptionPane.showMessageDialog(frmVerbargoV, "Empty Team List");
				} else {
					vTeam newTeam = new vTeam(teamtextfield.getText().trim());
					boolean found = false;
					for (int i = 0; i < vgm.vteammanager.size(); i++) {
						if(vgm.vteammanager.get(i).getTeamName().equals(newTeam.getTeamName())){
							vgm.vteammanager.remove(vgm.vteammanager.get(i));
							found = true;
						} 
					}
					if(found == false){
						JOptionPane.showMessageDialog(frmVerbargoV, "Team Doesn't Exist!"); 
					}
				}
			}
			this.MainVerbargopnl.revalidate();
			teamtextfield.setText("");
		} else if (e.getSource() == this.lockteambtn) {
			if(vgm.vteammanager.size() < 2){
				JOptionPane.showMessageDialog(frmVerbargoV, "Must have at least two teams to play");
				return;
			}
			if(this.addteambtn.isEnabled() || this.deleteteambtn.isEnabled()){
				teamcombobox = new JComboBox<String>(vgm.vteammanager.getTeamNames());
				teamcombobox.setEnabled(true);
				MainVerbargopnl.add(teamcombobox);
				teamcombobox.setEnabled(true);
				teamcombobox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				teamcombobox.setBounds(159, 77, 146, 20);
				teamcombobox.setVisible(true);
				showplayersbtn.setEnabled(true);
				this.teamtextfield.setEnabled(false);
				this.addteambtn.setEnabled(false);
				this.deleteteambtn.setEnabled(false);
				this.startbtn.setEnabled(true);
				playertextfield.setEnabled(true);
				addplayerbtn.setEnabled(true);
				deleteplayerbtn.setEnabled(true);
				this.lockteambtn.setText("Edit Teams");
				this.MainVerbargopnl.repaint();
				this.MainVerbargopnl.validate();
				this.MainVerbargopnl.updateUI();
			} else {
				MainVerbargopnl.remove(teamcombobox);
				this.startbtn.setEnabled(false);
				teamcombobox.setVisible(false);
				this.teamtextfield.setEnabled(true);
				this.addteambtn.setEnabled(true);
				this.deleteteambtn.setEnabled(true);
				playertextfield.setEnabled(false);
				addplayerbtn.setEnabled(false);
				deleteplayerbtn.setEnabled(false);
				this.lockteambtn.setText("Lock Teams");
				this.MainVerbargopnl.repaint();
				this.MainVerbargopnl.validate();
				this.MainVerbargopnl.updateUI();
			}
		} else if (e.getSource() == this.optionsbtn){
			opt = new OptionGUI(options);
		} else if (e.getSource() == this.addplayerbtn) {
			teamplayerstextarea.setText("");
			String teamName = null;
			if(playertextfield.getText().equalsIgnoreCase("") || playertextfield.getText() == null){
				JOptionPane.showMessageDialog(frmVerbargoV, "Invalid Name");
				return;
			} else {
				teamName = (String) teamcombobox.getSelectedItem();
				try {
					vgm.vteammanager.getTeam(teamName.trim()).addToBack(playertextfield.getText());
				} catch (FullTeamException e1) {
					JOptionPane.showMessageDialog(frmVerbargoV,e1.getMessage());
				}
			}
			teamplayerstextarea.append(vgm.vteammanager.getTeam(teamName.trim()).getPlayersOnTeam());
			playertextfield.setText("");
		} else if (e.getSource() == this.deleteplayerbtn) {
			teamplayerstextarea.setText("");
			String teamName = null;
			if(playertextfield.getText().equalsIgnoreCase("") || playertextfield.getText() == null){
				JOptionPane.showMessageDialog(frmVerbargoV, "Invalid Name");
				return;
			} else {
				teamName = (String) teamcombobox.getSelectedItem();
				try {
					vgm.vteammanager.getTeam(teamName.trim()).removePlayer((playertextfield.getText()));
				} catch (NoSuchPlayerException e1) {
					JOptionPane.showMessageDialog(frmVerbargoV, e1.getMessage());
				} catch (EmptyTeamException e1) {
					JOptionPane.showMessageDialog(frmVerbargoV, e1.getMessage());
				}
			}
			teamplayerstextarea.append(vgm.vteammanager.getTeam(teamName.trim()).getPlayersOnTeam());
			playertextfield.setText("");
		} else if (e.getSource() == this.showplayersbtn){
			teamplayerstextarea.setText("");
			if(vgm.vteammanager.size() > 1){
				teamplayerstextarea.append(vgm.vteammanager.getTeam(((String)teamcombobox.getSelectedItem()).trim()).getPlayersOnTeam());
			}
			this.MainVerbargopnl.validate();
			this.MainVerbargopnl.repaint();
		} else if (e.getSource() == this.startbtn){
			for(int i = 0; i < vgm.vteammanager.size(); i++){
				if(vgm.vteammanager.get(i).getSize() < MIN_TEAM_PLAYERS){
					JOptionPane.showMessageDialog(frmVerbargoV, "Each Team needs at least 2 players!");
					return;
				}
			}
			if(opt == null){
				//NEVER OPENED OPTION PANEL
			} else {
				opt.close();
			}
			vCardGameStart = new VerbargoDiffChoose(options.getDifficulty(), vgm);
			this.frmVerbargoV.dispose();
		} else if (e.getSource() == this.aboutbtn){
			//ABOUT PANEL
		}
	}

	public class JTextFieldCharLimit extends PlainDocument{
		
		private static final long serialVersionUID = -8551439773009905849L;
		
		private int limit;
		
		public JTextFieldCharLimit(int limitation){
			this.limit = limitation;
		}
		
		public void insertString(int offset, String str, AttributeSet set) throws BadLocationException{
			if (str == null){
				return;
			} else if(getLength() + str.length() <= limit){
				str = str.toUpperCase();
				super.insertString(offset, str, set);
			}
			
		}
	}
}
