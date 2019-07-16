import java.util.*;
import java.io.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
class GameObject{
	//Event list constant
		public final static int EVENT_CREATE = 0;
		public final static int EVENT_FRAME = 1;
		public final static int EVENT_LEFT_ACTION = 2;
		public final static int EVENT_RIGHT_ACTION = 3;
		public final static int EVENT_UP_ACTION = 4;
		public final static int EVENT_DOWN_ACTION = 5;
		public final static int EVENT_A_ACTION = 6;
		public final static int EVENT_B_ACTION = 7;
		public final static int EVENT_L_ACTION = 8;
		public final static int EVENT_R_ACTION = 9;
		public final static int EVENT_START_ACTION = 10;
		public final static int EVENT_SELECT_ACTION = 11;
		public final static int VAR_LIST = 12;
	//Gameobject instance variables
		private int event_select = 0;
		private String name;
		private String sprite;
		private Thread graphics;
		//String for default code
		private String def_var_str;
		private String def_frame_str;
		//Event list
		private ArrayList<String> event = new ArrayList<String>();
	//-------Constructor----//
		GameObject(String name){
			this.name = name;
			//if(Files.exists("include/" + name + "_obj.h"))
				//FileOutputStream out = new FileOutputStream("include/" + name + "_obj.h");
			//String for default code
				def_var_str = "//This code is auto-generated\nint " + name + "_x = 0;\nint " + name + "_y = 0;\nchar " + name + "_name[15] = " + (char)34 + name + (char)34 +  ";\n" + "void create_" + name + "(){\n\n}";
			    def_frame_str = "void frame_" + name + "(){\n\n}";
			event.ensureCapacity(13);
			for(int i=0; i<13;i++){
				if(i == 0)event.add(i,def_var_str);
				else if (i == 1) event.add(i,def_frame_str);
				else event.add(i,"//Insert code here\n");
			}
			setEvent(VAR_LIST,def_var_str);
			setEvent(EVENT_FRAME,def_frame_str);
				
			//Set Initial interface
			graphics = new GameObjectInterface("settings/Config.cfg",name,this);
			launchGraphics();	
		}

	//Private Methods
		private String getEventFromIndex(int index){
			if (index == 0) return "Create Event";
			if (index == 1) return "Frame Event";
			if (index == 2) return "Left Button Event";
			if (index == 3) return "Right Button Event";
			if (index == 4) return "Up Button Event";
			if (index == 5) return "Down Button Event";
			if (index == 6) return "A Button Event";
			if (index == 7) return "B Button Event";
			if (index == 8) return "L Button Event";
			if (index == 9) return "R Button Event";
			if (index == 10)return "Start Button Event";
			if (index == 11)return "Select Button Event";
			return "error";
		}
	
	//Public methods
		//launch gameobject graphics part
		public void launchGraphics(){
			graphics.run();
		}
		
		public String getEvent(int event){
			if(event >= 0 && event <= 12)
				return this.event.get(event);
			else return "error";
		}
		
		public void setEvent(int event, String set){
			if(event >= 0 && event <= 12)
				this.event.set(event,set);
		}
		
}