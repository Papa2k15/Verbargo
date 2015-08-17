package verbargo.gui;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import verbargo.game.verbargoOptions;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.SystemColor;

public class OptionGUI {

	private JFrame frmVerbargoOptions;
	private JPanel optionpnl;
	private JSlider diffslider;
	private verbargoOptions options;
	private JButton btnReturnToMain;
	private JRadioButton musiconbtn;
	private JRadioButton musicoffbtn;
	private JButton saveoptnbtn;
	private JFormattedTextField timeinsecondstxtf;
	private JRadioButton timeoffbtn;
	private JRadioButton timeonbtn;
	private ButtonGroup timebg;
	private ButtonGroup musicbg;
	private JLabel lblMinMax;
	private boolean isOpen;

	/**
	 * Create the application.
	 */
	public OptionGUI(verbargoOptions options) {
		this.options = options;
		this.timebg = new ButtonGroup();
		this.musicbg = new ButtonGroup();
		isOpen = true;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVerbargoOptions = new JFrame();
		frmVerbargoOptions.setTitle("Verbargo Options");
		frmVerbargoOptions.setAlwaysOnTop(true);
		frmVerbargoOptions.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Samsung\\workspace\\Verbargo 1.5\\VerbargoAppIcon.png"));
		frmVerbargoOptions.setBounds(100, 100, 450, 300);
		frmVerbargoOptions.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		optionpnl = new JPanel();
		optionpnl.setBackground(SystemColor.activeCaptionBorder);
		frmVerbargoOptions.getContentPane().add(optionpnl, BorderLayout.CENTER);
		optionpnl.setLayout(null);
		
		diffslider = new JSlider(0,2,1);
		diffslider.setBackground(SystemColor.activeCaptionBorder);
		diffslider.setBounds(10, 42, 200, 26);
		diffslider.setSnapToTicks(true);
		diffslider.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent arg0) {
				options.setDifficulty(diffslider.getValue());
				saveoptnbtn.setEnabled(true);
			}
		});
		optionpnl.add(diffslider);
		
		JLabel difflbl = new JLabel("Difficulty");
		difflbl.setHorizontalAlignment(SwingConstants.CENTER);
		difflbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
		difflbl.setBounds(51, 11, 116, 26);
		optionpnl.add(difflbl);
		
		JLabel easylb = new JLabel("EASY");
		easylb.setFont(new Font("Segoe UI", Font.BOLD, 13));
		easylb.setBounds(10, 74, 46, 14);
		optionpnl.add(easylb);
		
		JLabel hardlbl = new JLabel("HARD");
		hardlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		hardlbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
		hardlbl.setBounds(164, 75, 46, 14);
		optionpnl.add(hardlbl);
		
		timeonbtn = new JRadioButton("On");
		timeonbtn.setBackground(SystemColor.activeCaptionBorder);
		timeonbtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		timeonbtn.setBounds(10, 138, 46, 23);
		timeonbtn.setSelected(true);
		timeonbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeinsecondstxtf.setText("Timer seconds");
				timeinsecondstxtf.setEnabled(true);
				options.setTimedOption(true);		
				saveoptnbtn.setEnabled(true);
			}	
		});
		optionpnl.add(timeonbtn);
		
		timeoffbtn = new JRadioButton("Off");
		timeoffbtn.setBackground(SystemColor.activeCaptionBorder);
		timeoffbtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		timeoffbtn.setBounds(10, 177, 46, 23);
		timeoffbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				options.setTimedOption(false);		
				timeinsecondstxtf.setEnabled(false);
				saveoptnbtn.setEnabled(true);
			}	
		});
		optionpnl.add(timeoffbtn);
		
		timebg.add(timeonbtn);
		timebg.add(timeoffbtn);
		
		JLabel timelbl = new JLabel("Time");
		timelbl.setHorizontalAlignment(SwingConstants.CENTER);
		timelbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
		timelbl.setBounds(51, 105, 116, 26);
		optionpnl.add(timelbl);
		
		timeinsecondstxtf = new JFormattedTextField(NumberFormat.INTEGER_FIELD);
		timeinsecondstxtf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		timeinsecondstxtf.setBounds(67, 158, 116, 20);
		timeinsecondstxtf.setText("Timer seconds");
		optionpnl.add(timeinsecondstxtf);
		
		saveoptnbtn = new JButton("Save Options");
		saveoptnbtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		saveoptnbtn.setBounds(275, 154, 149, 46);
		saveoptnbtn.setEnabled(false);
		saveoptnbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				options.setTime((int)timeinsecondstxtf.getValue());
				timeinsecondstxtf.setText("");
				saveoptnbtn.setEnabled(false);
			}
			
		});
		optionpnl.add(saveoptnbtn);
		
		btnReturnToMain = new JButton("Return to Main");
		btnReturnToMain.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnReturnToMain.setBounds(275, 204, 149, 46);
		btnReturnToMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frmVerbargoOptions.dispose();
			}
			
		});
		optionpnl.add(btnReturnToMain);
		
		JLabel musiclbl = new JLabel("Music");
		musiclbl.setHorizontalAlignment(SwingConstants.CENTER);
		musiclbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
		musiclbl.setBounds(272, 11, 116, 26);
		optionpnl.add(musiclbl);
		
		musiconbtn = new JRadioButton("On");
		musiconbtn.setBackground(SystemColor.activeCaptionBorder);
		musiconbtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		musiconbtn.setBounds(282, 42, 46, 23);
		musiconbtn.setSelected(true);
		optionpnl.add(musiconbtn);
		
		musicoffbtn = new JRadioButton("Off");
		musicoffbtn.setBackground(SystemColor.activeCaptionBorder);
		musicoffbtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		musicoffbtn.setBounds(342, 42, 46, 23);
		optionpnl.add(musicoffbtn);
		
		musicbg.add(musiconbtn);
		musicbg.add(musicoffbtn);
		
		lblMinMax = new JLabel("MIN: 60       MAX: 90");
		lblMinMax.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblMinMax.setBounds(62, 181, 133, 14);
		optionpnl.add(lblMinMax);
		frmVerbargoOptions.setVisible(true);
		frmVerbargoOptions.setLocation(600,100);
	}
	
	public boolean isOpen(){
		return isOpen;
	}
	
	public void close(){
		this.frmVerbargoOptions.dispose();
	}
}
