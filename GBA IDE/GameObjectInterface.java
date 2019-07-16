import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

class EditButton extends Button {
	int id = 0;
	EditButton(int id){
		super();
		this.id = id;
	}
	EditButton(int id, String label){
		super(label);
		this.id = id;
	}

	public String toString(){
		return String.valueOf(id);
	}
}

class GameObjectInterface extends Thread {
	String name = "";
    String cw_setting_file_path = "settings/Config.cfg";
	String default_text = "Select code part";
	GameObject gm;
	//Constructors
	GameObjectInterface(String cw_setting_file_path, String name, GameObject gm){
		this.cw_setting_file_path = cw_setting_file_path;
		this.name = name;
		this.gm = gm;
	}

	GameObjectInterface(String cw_setting_file_path,String default_text,String name,GameObject gm){
		this.cw_setting_file_path = cw_setting_file_path;
		this.default_text = default_text;
		this.name = name;
		this.gm = gm;
	}
	
	public void run(){
		
	//Initialization-------------
		JFrame fr = new JFrame(name);
		String[] event_name = {"CREATE EVENT","FRAME EVENT","LEFT ACTION","RIGHT ACTION", "UP ACTION", "DOWN ACTION","A ACTION","B ACTION","L ACTION","R ACTION","START ACTION","SELECT ACTION"};
		CodeViewer cw = new CodeViewer(cw_setting_file_path);
		EditButtonAction act_bt = new EditButtonAction(cw,gm);
		cw.append(default_text);
		cw.reColorAllText();
	//Label list-----------------
		ArrayList<Label> lst_label = new ArrayList<Label>();
		for(int i = 0; i <12; i++) 
			lst_label.add(new Label(event_name[i]));
	//Button list------------------
		ArrayList<EditButton> lst_button = new ArrayList<EditButton>();
		for(int i = 0; i <12; i++) {
			lst_button.add(new EditButton(i,"Edit"));
			lst_button.get(i).addActionListener(act_bt);
		}
	//panel
		JPanel p_setting = new JPanel();
		p_setting.setLayout(new GridLayout(0,2));
		
		JPanel p_main = new JPanel();
		p_main.setLayout(new BorderLayout());
	//Set Panel
		for(int i = 0; i <12; i++){
				p_setting.add(lst_label.get(i));
				p_setting.add(lst_button.get(i));
		}
	//Main panel set-----------------------
		p_main.add("West",p_setting);
		p_main.add("Center",cw);
	//Frame set-----------------------	
		fr.setSize(900,600);
		fr.setVisible(true);
		fr.setResizable(false);
		fr.add(p_main);
		//fr.pack();
	}
		
}