import java.awt.*;
import javax.swing.*;

public class DisplayApplet extends JApplet {
	
	GripperSim s;
	public void init(){
		s = new GripperSim();
		setLayout(new FlowLayout());
		add(s);
		
	}
}