package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class TextPlay extends JLabel {

    private static final ImageIcon BACKGROUND = new ImageIcon("white2.jpg");
    private Timer timer, visible;
    private String display = "";
    private int idx;

    public TextPlay() {
	super(BACKGROUND);
	setSize(100, 768);
	setIconTextGap(-1335);
	setText("");
	timer = new Timer(30, e -> displayText());
	visible = new Timer(500, e -> cleanup());
	registerFont();
	setFont(new Font("Pokemon GB", Font.ITALIC, 12));
	setVisible(true);
    }

    private void displayText() {
	if (idx == display.length()) {
	    timer.stop();
	    visible.start();
	}
	else {
	    setText(getText() + display.charAt(idx++));
	}
    }

    private final void registerFont() {
	try {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Pokemon GB.ttf")));
	}
	catch (IOException | FontFormatException e) {
	    e.printStackTrace();
	}
    }

    public Timer getTimer() {
	return timer;
    }

    public void setTimerTime(int milliseconds) {
	timer = new Timer(milliseconds, e -> displayText());
    }

    public int getIndex() {
	return idx;
    }

    public void setDisplay(String s) {
	display = s;
    }

    public String getDisplay() {
	return display;
    }

    private void test() {
	timer.start();
    }

    private void cleanup() {
	setVisible(false);
	visible.stop();
	setText("");
	display = "";
	idx = 0;
    }

    public static void main(String[] args) {
	TextPlay test = new TextPlay();
	JFrame f = new JFrame("TEST");
	f.setSize(1366, 768);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setLocationRelativeTo(null);
	f.add(test);
	f.setVisible(true);

	test.setDisplay("ACMACMACMACMACMACMACMACMACMACMACMACMACMACM");
	test.test();
    }
}