import java.awt.*;
import java.awt.event.*;
class NumberConverter {
	//Static Items
		///Langlist
	    private static Lang lenguage = new Lang("lang/italiano");
	//label creation
		private static Label lb_dec = new Label(lenguage.contentOf("decarea"));
		private static Label lb_bin = new Label(lenguage.contentOf("binaryarea"));
		private static Label lb_exd = new Label(lenguage.contentOf("exarea"));
		private static Label lb_lang = new Label(lenguage.contentOf("selectlang"));
		private static Label lb_frm = new Label(lenguage.contentOf("convertfrom"));
	//Checkbox-----------------------------------------------
		private static Checkbox check_dec = new Checkbox("Dec");
		private static Checkbox check_bin = new Checkbox("Bin");
		private static Checkbox check_exd = new Checkbox("Exd");
	//Text---------------------------------------------------
		private static TextField txt_dec = new TextField("0");
		private static TextField txt_bin  = new TextField("0");
		private static TextField txt_exd  = new TextField("0");
	//Choice-------------------------------------------------
		private static Choice lang_ch = new Choice();
	//Button
        private static Button convert = new Button(lenguage.contentOf("btnconvert"));
    //Frame
	    private static Frame fr = new Frame(lenguage.contentOf("title"));
		
	public static void main(String[] args){
		lenguage.langFileList();
		//SetEvent------------------------------------------
		CheckBoxEvent ch_event = new CheckBoxEvent();
		BtnConvert btn_event = new BtnConvert();
		LangChangeListener lg = new LangChangeListener();
		//SetFrame------------------------------------------
		fr.setLayout(new BorderLayout());
		fr.setSize(600,200);
		fr.setLocation(100,100);
		//Set the x button
		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
        //SetPanel------------------------------------------------
		Panel center_p = new Panel( new GridLayout(2,3,5,5));
		Panel top_p = new Panel(new FlowLayout(FlowLayout.LEFT));
		//input box creation
			//add itemlistener
			check_dec.addItemListener(ch_event);
			check_bin.addItemListener(ch_event);
			check_exd.addItemListener(ch_event);
			//Add Action listener
			convert.addActionListener(btn_event);
			lang_ch.addItemListener(lg);
		//
        //Panel add -> center panel
			center_p.add(lb_dec);
			center_p.add(lb_bin);
			center_p.add(lb_exd);
			//->Top panel ADD
				top_p.add(lb_lang);
		//
		//input box add
			center_p.add(txt_dec);
			center_p.add(txt_bin);
			center_p.add(txt_exd);
			//->Top panel add
				top_p.add(lang_ch);
				top_p.add(lb_frm);
				top_p.add(check_dec);
				top_p.add(check_bin);
				top_p.add(check_exd);
				top_p.add(convert);
		//
		fr.add("North", top_p);
		fr.add("Center", center_p);
		fr.setResizable(false);
		fr.setVisible(true);
		fr.pack();
	}
	
	public static boolean checkRadioButton(String label){
		if(label == "Dec") return check_dec.getState();
		if(label == "Bin") return check_bin.getState();
		if(label == "Exd") return check_exd.getState();
		return false;
	}
    public static void setRadioButton(String label, boolean set){
		if(label == "Dec") check_dec.setState(set);
		if(label == "Bin") check_bin.setState(set);
		if(label == "Exd") check_exd.setState(set);
	}	
	
	public static String getText(String label){
		if(label == "Dec") return txt_dec.getText();
		if(label == "Bin") return txt_bin.getText();
		if(label == "Exd") return txt_exd.getText();
		return "";
	}
	public static void setText(String label,String Text){
		if(label == "Dec") txt_dec.setText(Text);
		if(label == "Bin") txt_bin.setText(Text);
		if(label == "Exd") txt_exd.setText(Text);
	}
	
	public static void addLang(String lang){
		lang_ch.addItem(lang);
	}
	
	public static void resetLang(String file){
		lenguage.setLang(file);
		lb_dec.setText(lenguage.contentOf("decarea"));
		lb_bin.setText(lenguage.contentOf("binaryarea"));
		lb_exd.setText(lenguage.contentOf("exarea"));
		lb_lang.setText(lenguage.contentOf("selectlang"));
		lb_frm.setText(lenguage.contentOf("convertfrom"));
		convert.setLabel(lenguage.contentOf("btnconvert"));
		fr.pack();
	}
	
}