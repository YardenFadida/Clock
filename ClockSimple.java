import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//@YardenFadida
public class ClockSimple extends JFrame implements ActionListener {
	static Frame frame;
	static JLabel title;
	static JLabel time;
	static JLabel clock;
	static JButton btn;
	static LocalTime currentTime;
	static int minute=0,second=0,mSecond=0;
	static Timer timer;
	static Object [] colors ={Color.RED, Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.GREEN, Color.ORANGE, Color.PINK, Color.YELLOW};
	static Random rnd;
	
	public ClockSimple() {
		
	}

	public static void main(String[] args) {
		frame = new JFrame("Clock");
		frame.setSize(600, 300);
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
		UpdateTime();
		timer = new Timer();
		rnd= new Random();

	}
	
	private static void placeComponents(JPanel panel){
		ClockSimple CS = new ClockSimple();
		panel.setLayout(null);
		
		title = new JLabel("Clock & Timer Example project");
		title.setBounds(160,0,300,30);
		Font labelFont = title.getFont();
		title.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
		
		time = new JLabel("Current Time");
		time.setBounds(200,40,300,60);
		time.setFont(new Font(labelFont.getName(), Font.PLAIN, 50));
		
		clock = new JLabel("00:00:00");
		clock.setBounds(200,130,300,60);
		clock.setFont(new Font(labelFont.getName(), Font.PLAIN, 50));
		
		btn = new JButton("Start");
		btn.addActionListener(CS);
		btn.setBounds(150,240,300,25);
		
		panel.add(title);
		panel.add(btn);
		panel.add(time);
		panel.add(clock);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource().equals(btn)) {
			if(btn.getLabel().equals("Start")) {
				TimerMethod();
				btn.setText("Stop");
			}
			else {
				timer.cancel();
				timer=new Timer();
				btn.setText("Start");
			}
		}
		
	}
	
	private static void TimerMethod() {
		 TimerTask task = new TimerTask() {
	         public void run() {
	            if(mSecond == 59 && second == 59) {
	            	minute++;
	            	second=00;
	            	mSecond=00;
	            }
	            else if(mSecond==59) {
	            	second++;
	            	mSecond=00;
	            	clock.setForeground((Color) colors[rnd.nextInt(8)]);
	            }
	            else{
	            	mSecond++;
	            }
	            clock.setText(minute+":"+second+":"+mSecond);
	         }
	      };
		
	      timer.scheduleAtFixedRate(task, 0, 100);
	}

	private static void UpdateTime() {
	      Timer timer = new Timer();
	      
	      // Create a new TimerTask that will run every second
	      TimerTask task = new TimerTask() {
	         public void run() {
	            LocalTime currentTime = LocalTime.now();
	            String timeColonPattern = "HH:mm:ss";
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeColonPattern);
	  	      	String formattedTime = currentTime.format(formatter);
	            time.setText(formattedTime);
	         }
	      };

	      // Schedule the task to run every second, starting now
	      timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
}