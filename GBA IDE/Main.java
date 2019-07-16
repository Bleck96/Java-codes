import javax.swing.*;
import java.awt.*;
import java.util.*;
class Main {
	public static void main(String args[]){
		//--------------------------Initialization--------------------------------
			//For_debbugging
			//	GameObject gb = new GameObject("prova");
			// My Class------------------------------------------------
				CodeViewer cw = new CodeViewer("settings/Config.cfg");
				WatchMethods wm = new WatchMethods("smgbalib.h", cw.getExtractor(),"smgbalib");
			// Text ----------------------------------------------
				JTextArea debug_console = new JTextArea(); 
				JTextArea meth_descr = new JTextArea(); 
			// JList------------------------------------------------- 
				JList lst_meth = new JList<String>(wm.getMethodList()); 
				JList lst_gm = new JList<GameObject>(); 
			// Frame --------------------------------------------------------
				JFrame fr = new JFrame("EditBoy (GBA IDE)");
			// ScrollPanel -----------------------------------------
				JScrollPane spEditor = new JScrollPane(cw,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				JScrollPane list_meth_scroll = new JScrollPane(lst_meth,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				JScrollPane list_gm_scroll = new JScrollPane(lst_gm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			// Panel -----------------------------------------
				Panel p = new Panel();
				JPanel p_right = new JPanel();
				JPanel p_left = new JPanel();
				p.setLayout( new BorderLayout());
				p_right.setLayout(new BoxLayout(p_right,BoxLayout.Y_AXIS));
				p_left.setLayout(new BoxLayout(p_left,BoxLayout.Y_AXIS));
			//Choiche-----------------------------------------------------
				Choice ch_lib = new Choice();	
				ch_lib.addItem("Library: " + wm.getLibName());
			
			//Buttons-------------------------------------------------------
				Button add_gm = new Button("add GameObject");
				Button add_rm = new Button("add Room");
		//-------------------------------------------------------------------------
		//West Panel add---------------------------------------------
			p_left.add(add_gm);
			p_left.add(list_gm_scroll);
		//East panel add---------------------------------------------
			p_right.add(ch_lib);
			p_right.add(lst_meth);
			p_right.add(meth_descr);
		//Container add---------------------------------------------
			p.add("Center" , spEditor);
			p.add("South" , debug_console);
			p.add("East" , p_right);
			p.add("West" , p_left);
		//CodeViewer set -----------------------------------------
			cw.append(cw.START_STRING);
			cw.reColorAllText();
		//TextArea Set---------------------------------------------
			debug_console.append("Debug Console : \n\n\n\n");
			debug_console.setEditable(false);
			debug_console.setBackground( new Color(20, 20, 40));
			debug_console.setForeground( new Color(233, 233, 234));
		//MethodList set----------------------------------------------
		    meth_descr.setEditable(false);
			lst_meth.setBackground(new Color(23,27,27));
			lst_meth.setForeground(Color.GREEN);
			meth_descr.setBackground(new Color(23,27,27));
			meth_descr.setForeground(new Color(240,240,240));
		//GameObject List set----------------------------------------------
			lst_gm.setBackground(new Color(23,27,27));
			lst_gm.setForeground(Color.GREEN);
	    //Frame set----------------------------------------------
			fr.add(p);
			fr.setLocation(0,0);
			fr.setSize(800,600);
			//fr.pack();
			fr.setVisible(true);
	}
}