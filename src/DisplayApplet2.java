import java.awt.*;
import javax.swing.*;

public class DisplayApplet2 extends JApplet {
	
	GripperSim2 s;
	public void init(){
		s = new GripperSim2();
		setLayout(new FlowLayout());
		add(s);
		
	}
}