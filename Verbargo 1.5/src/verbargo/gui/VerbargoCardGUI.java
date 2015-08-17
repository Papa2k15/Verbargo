package verbargo.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import verbargo.card.vCard;
import verbargo.exception.InvalidScoreException;
import verbargo.exception.UsedAllCardsException;
import verbargo.game.verbargoGameManger;
import verbargo.limits.vLimits;
import verbargo.team.vPlayer;
import verbargo.team.vTeam;
import java.awt.BorderLayout;
import java.util.concurrent.Semaphore;

import javax.swing.JProgressBar;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class VerbargoCardGUI extends JFrame implements ActionListener, vLimits {

	private static final long serialVersionUID = 1L;
	
	public verbargoGameManger vgm;
	public vTeam currentTeamGoing;
	public vCard currentCard;
	public vPlayer currentPlayerGoing;
	public int difficulty;

	private JProgressBar remainingtimepb;
	private JLabel currentscorelbl;
	private JButton passbtn;
	private JButton correctbtn;
	private JLabel currentteamlbl;
	private JLabel currentplayerlbl;
	private JLabel vwordlbl;
	private JLabel rvword1lbl;
	private JLabel rvword2lbl;
	private JLabel rvword3lbl;
	private JLabel rvword4lbl;
	private JLabel rvword5lbl;
	private JLabel rvword6lbl;
	private JPanel easygamepnl;
	private JButton startgamebtn;
	private JButton quitbtn;
	private Semaphore s = new Semaphore(1);

	public VerbargoCardGUI(verbargoGameManger vgm, vTeam cTeam, vPlayer cPlayer, int vTeamScore, int difficulty){
		this.vgm = vgm;
		getCurrentCardForGame();
		this.currentTeamGoing = cTeam;
		this.currentPlayerGoing = cPlayer;
		this.difficulty = difficulty;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(494,330);
		setLocation(400,200);
		
		easygamepnl = new JPanel();
		easygamepnl.setBackground(Color.WHITE);
		getContentPane().add(easygamepnl, BorderLayout.CENTER);
		easygamepnl.setLayout(null);
		
		vwordlbl = new JLabel("CURRENT vWORD");
		vwordlbl.setHorizontalAlignment(SwingConstants.CENTER);
		vwordlbl.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		vwordlbl.setBounds(10, 11, 177, 43);
		vwordlbl.setText(currentCard.getvWord());
		easygamepnl.add(vwordlbl);
		
		rvword1lbl = new JLabel("CURRENT rvWORD1");
		rvword1lbl.setHorizontalAlignment(SwingConstants.CENTER);
		rvword1lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rvword1lbl.setBounds(20, 52, 155, 30);
		rvword1lbl.setText(currentCard.getrvWords()[0]);
		easygamepnl.add(rvword1lbl);
		
		rvword2lbl = new JLabel("CURRENT rvWORD2");
		rvword2lbl.setHorizontalAlignment(SwingConstants.CENTER);
		rvword2lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rvword2lbl.setBounds(20, 82, 155, 30);
		rvword2lbl.setText(currentCard.getrvWords()[1]);
		easygamepnl.add(rvword2lbl);
		
		rvword3lbl = new JLabel("CURRENT rvWORD3");
		rvword3lbl.setHorizontalAlignment(SwingConstants.CENTER);
		rvword3lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rvword3lbl.setBounds(20, 112, 155, 30);
		rvword3lbl.setText(currentCard.getrvWords()[2]);
		easygamepnl.add(rvword3lbl);
		
		rvword4lbl = new JLabel("CURRENT rvWORD4");
		rvword4lbl.setHorizontalAlignment(SwingConstants.CENTER);
		rvword4lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rvword4lbl.setBounds(20, 141, 155, 30);
		rvword4lbl.setText(currentCard.getrvWords()[3]);
		easygamepnl.add(rvword4lbl);
		
		rvword5lbl = new JLabel("CURRENT rvWORD5");
		rvword5lbl.setHorizontalAlignment(SwingConstants.CENTER);
		rvword5lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rvword5lbl.setBounds(20, 171, 155, 30);
		rvword5lbl.setText(currentCard.getrvWords()[4]);
		easygamepnl.add(rvword5lbl);
		
		rvword6lbl = new JLabel("CURRENT rvWORD6");
		rvword6lbl.setHorizontalAlignment(SwingConstants.CENTER);
		rvword6lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rvword6lbl.setBounds(20, 200, 155, 30);
		rvword6lbl.setText(currentCard.getrvWords()[5]);
		easygamepnl.add(rvword6lbl);
		
		passbtn = new JButton("Pass");
		passbtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		passbtn.setBounds(112, 241, 92, 39);
		passbtn.addActionListener(this);
		easygamepnl.add(passbtn);
		
		correctbtn = new JButton("Correct");
		correctbtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		correctbtn.setBounds(214, 241, 92, 39);
		correctbtn.addActionListener(this);
		easygamepnl.add(correctbtn);
		
		currentteamlbl = new JLabel("CURRENT TEAM");
		currentteamlbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentteamlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		currentteamlbl.setBounds(252, 18, 155, 30);
		currentteamlbl.setText(currentTeamGoing.getTeamName());
		easygamepnl.add(currentteamlbl);
		
		currentplayerlbl = new JLabel("CURRENT PLAYER");
		currentplayerlbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentplayerlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		currentplayerlbl.setBounds(252, 62, 155, 30);
		currentplayerlbl.setText(currentPlayerGoing.getName());
		easygamepnl.add(currentplayerlbl);
		
		currentscorelbl = new JLabel("CURRENT SCORE");
		currentscorelbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentscorelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		currentscorelbl.setBounds(252, 112, 155, 30);
		currentscorelbl.setText(""+currentTeamGoing.getScore());
		easygamepnl.add(currentscorelbl);
		
		remainingtimepb = new JProgressBar();
		remainingtimepb.setMaximum(MAX_TURN_TIME);
		remainingtimepb.setBounds(211, 184, 240, 30);
		easygamepnl.add(remainingtimepb);
		
		startgamebtn = new JButton("Start");
		startgamebtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		startgamebtn.setBounds(10, 241, 92, 39);
		startgamebtn.addActionListener(this);
		easygamepnl.add(startgamebtn);
		
		quitbtn = new JButton("Quit");
		quitbtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		quitbtn.setBounds(376, 241, 92, 39);
		quitbtn.addActionListener(this);
		easygamepnl.add(quitbtn);
		
		rvword1lbl.setVisible(false);
		rvword2lbl.setVisible(false);
		rvword3lbl.setVisible(false);
		rvword4lbl.setVisible(false);
		rvword5lbl.setVisible(false);
		rvword6lbl.setVisible(false);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == startgamebtn){
			startgamebtn.setEnabled(false);
			s.acquireUninterruptibly();
			timeRemaining();
			if(difficulty == DIFFICULTY_TRANS.EASY.ordinal()){
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
			} else if (difficulty == DIFFICULTY_TRANS.MEDIUM.ordinal()){
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
				rvword3lbl.setVisible(true);
				rvword4lbl.setVisible(true);
			} else {
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
				rvword3lbl.setVisible(true);
				rvword4lbl.setVisible(true);
				rvword5lbl.setVisible(true);
				rvword6lbl.setVisible(true);
			}
		} else if (arg0.getSource() == quitbtn){
			int choice = JOptionPane.showConfirmDialog(this, "Are you sure?");
			if(choice == JOptionPane.YES_OPTION){
				System.exit(0);
			} else {
				return;
			}
		} else if (arg0.getSource() == passbtn){
			updateLabels();
			try {
				currentTeamGoing.decrementScore();
			} catch (InvalidScoreException e) {
				JOptionPane.showMessageDialog(this, "Current team has 0");
			}
			currentscorelbl.setText(""+currentTeamGoing.getScore());
			if(difficulty == DIFFICULTY_TRANS.EASY.ordinal()){
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
			} else if (difficulty == DIFFICULTY_TRANS.MEDIUM.ordinal()){
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
				rvword3lbl.setVisible(true);
				rvword4lbl.setVisible(true);
			} else {
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
				rvword3lbl.setVisible(true);
				rvword4lbl.setVisible(true);
				rvword5lbl.setVisible(true);
				rvword6lbl.setVisible(true);
			}
			easygamepnl.repaint();
			easygamepnl.revalidate();
		} else if (arg0.getSource() == correctbtn){
			try {
				currentTeamGoing.incrementScore();
				currentscorelbl.setText(""+currentTeamGoing.getScore());
			} catch (InvalidScoreException e) {
				JOptionPane.showMessageDialog(this, "MAX SCORE REACHED!");
			}
			updateLabels();
			if(difficulty == DIFFICULTY_TRANS.EASY.ordinal()){
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
			} else if (difficulty == DIFFICULTY_TRANS.MEDIUM.ordinal()){
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
				rvword3lbl.setVisible(true);
				rvword4lbl.setVisible(true);
			} else {
				rvword1lbl.setVisible(true);
				rvword2lbl.setVisible(true);
				rvword3lbl.setVisible(true);
				rvword4lbl.setVisible(true);
				rvword5lbl.setVisible(true);
				rvword6lbl.setVisible(true);
			}
			easygamepnl.repaint();
			easygamepnl.revalidate();
		}
	}
	
	public void updateLabels(){
		getCurrentCardForGame();
		vwordlbl.setText(currentCard.getvWord());
		rvword1lbl.setText(currentCard.getrvWords()[0]);
		rvword2lbl.setText(currentCard.getrvWords()[1]);
		rvword3lbl.setText(currentCard.getrvWords()[2]);
		rvword4lbl.setText(currentCard.getrvWords()[3]);
		rvword5lbl.setText(currentCard.getrvWords()[4]);
		rvword6lbl.setText(currentCard.getrvWords()[5]);
		currentteamlbl.setText(currentTeamGoing.getTeamName());
		currentplayerlbl.setText(currentPlayerGoing.getName());
		currentscorelbl.setText(""+currentTeamGoing.getScore());	
	}
	
	public synchronized void timeRemaining(){
		Thread t = new Thread(new Runnable() {
			public void run() {
				long i = System.currentTimeMillis();
				long f = System.currentTimeMillis();
				long dif = f-i;
				while(true){
					remainingtimepb.setValue((int)dif);
					dif = f-i;
					f = System.currentTimeMillis();
					if((int) dif > remainingtimepb.getMaximum()){
						s.release();
						getCurrentTeamForGame();
						getCurrentPlayerForGame(currentTeamGoing);
						currentteamlbl.setText(currentTeamGoing.getTeamName());
						currentplayerlbl.setText(currentPlayerGoing.getName());
						currentscorelbl.setText(""+currentTeamGoing.getScore());	
						startgamebtn.setEnabled(true);
						JOptionPane.showMessageDialog(easygamepnl, "TIMES UP!");
						remainingtimepb.setValue(0);
						
						return;
					}
				}
			}
		});
		t.start();
		return;
	}
	
	public void getCurrentCardForGame(){
		try {
			currentCard = this.vgm.vcarddatabase.getCurrentvCard();
		} catch (UsedAllCardsException e) {
			JOptionPane.showMessageDialog(this,"GAME OVER!!!");
			int max = 0;
			for (int i = 0; i < this.vgm.vteammanager.size(); i++) {
				max = Math.max(max,this.vgm.vteammanager.get(i).getScore());
			}
			for (int i = 0; i < this.vgm.vteammanager.size(); i++) {
				if(max == this.vgm.vteammanager.get(i).getScore()){
					JOptionPane.showMessageDialog(this,"Team " + this.vgm.vteammanager.get(i).getTeamName() + " Wins!!");
				}
			}
			System.exit(0);
		}
	}
	
	public void getCurrentTeamForGame(){
		currentTeamGoing = vgm.vteammanager.getCurrentTeam();
	}
	
	public void getCurrentPlayerForGame(vTeam cTeam){
		currentPlayerGoing = cTeam.getAt(cTeam.getCounter());
		cTeam.incCounter();
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
