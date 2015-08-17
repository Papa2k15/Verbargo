package verbargo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import verbargo.card.vCard;
import verbargo.game.verbargoGameManger;
import verbargo.limits.vLimits.DIFFICULTY_TRANS;
import verbargo.team.vPlayer;
import verbargo.team.vTeam;

public class VerbargoDiffChoose extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int difficulty;
	private Color[] color;
	private JPanel startpnl;
	private Random r = new Random();
	private JLabel currentplayerlbl;
	private JLabel currentteamlbl;
	private JButton quitbtn;
	private JButton getcardbtn;
	private JLabel difficultylbl;
	@SuppressWarnings("unused")
	private vCard currentCard;
	private verbargoGameManger gameManager;
	private vTeam currentTeamGoing;
	private vPlayer currentPlayerGoing;
	public  VerbargoCardGUI play;
	

	public VerbargoDiffChoose(int difficulty, verbargoGameManger vgm) {
		this.difficulty = difficulty;
		this.gameManager = vgm;
		getCurrentTeamForGame();
		getCurrentPlayerForGame(currentTeamGoing);
		int n = r.nextInt(5);
		color = new Color[5];
		color[0] = new Color(51, 255, 255);
		color[1] = new Color(204, 51, 255);
		color[2] = new Color(0, 255, 0);
		color[3] = new Color(255, 255, 0);
		color[4] = new Color(204, 102, 0);
		startpnl = new JPanel();
		getContentPane().add(startpnl, BorderLayout.CENTER);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocation(500,250);
		setSize(300,300);
		startpnl.setBackground(color[n]);
		startpnl.setLayout(null);
		
		getcardbtn = new JButton("Get Card");
		getcardbtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		getcardbtn.setBounds(10, 223, 124, 27);
		getcardbtn.addActionListener(this);
		startpnl.add(getcardbtn);
		
		quitbtn = new JButton("Quit");
		quitbtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		quitbtn.setBounds(150, 223, 124, 27);
		quitbtn.addActionListener(this);
		startpnl.add(quitbtn);
		
		currentteamlbl = new JLabel("Current Team");
		currentteamlbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentteamlbl.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		currentteamlbl.setBounds(10, 75, 168, 42);
		currentteamlbl.setText(currentTeamGoing.getTeamName());
		startpnl.add(currentteamlbl);
		
		currentplayerlbl = new JLabel("Current Player");
		currentplayerlbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentplayerlbl.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		currentplayerlbl.setBounds(106, 128, 168, 42);
		currentplayerlbl.setText(currentPlayerGoing.getName());
		startpnl.add(currentplayerlbl);
		
		difficultylbl = new JLabel("Difficulty");
		difficultylbl.setHorizontalAlignment(SwingConstants.CENTER);
		difficultylbl.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		difficultylbl.setBounds(53, 11, 168, 19);
		if(this.difficulty == DIFFICULTY_TRANS.EASY.ordinal()){
			difficultylbl.setText("Difficulty: EASY");
		} else if (this.difficulty == DIFFICULTY_TRANS.MEDIUM.ordinal()){
			difficultylbl.setText("Difficulty: MEDIUM");
		} else if (this.difficulty == DIFFICULTY_TRANS.HARD.ordinal()){
			difficultylbl.setText("Difficulty: HARD");
		}
		startpnl.add(difficultylbl);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == quitbtn){
			int quitchoice = JOptionPane.showConfirmDialog(this, "Are you sure?");
			if (quitchoice == JOptionPane.YES_OPTION){
				System.exit(0);
			} else {
				return;
			}
		} else if (arg0.getSource() == getcardbtn){
			play = new VerbargoCardGUI(gameManager, currentTeamGoing, currentPlayerGoing, currentTeamGoing.getScore(), this.difficulty);
			setVisible(false);
		}
	}

	public void getCurrentTeamForGame(){
		currentTeamGoing = gameManager.vteammanager.getCurrentTeam();
	}
	
	public void getCurrentPlayerForGame(vTeam cTeam){
		currentPlayerGoing = cTeam.getAt(cTeam.getCounter());
		cTeam.incCounter();
	}
	
}
