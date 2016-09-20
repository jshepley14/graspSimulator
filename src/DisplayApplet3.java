import java.awt.*;
import javax.swing.*;

public class DisplayApplet3 extends JApplet {
	
	GripperSim3 s;
	public void init(){
		s = new GripperSim3();
		setLayout(new FlowLayout());
		add(s);
		
	}
}