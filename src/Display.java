import javax.swing.JFrame;
public class Display {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		GripperSim s = new GripperSim();
		f.add(s);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 650);
	}

}
