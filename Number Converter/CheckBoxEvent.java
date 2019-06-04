import java.awt.event.*;
class CheckBoxEvent implements ItemListener {
		//Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getItem() == "Dec"){
				NumberConverter.setRadioButton("Bin",false);
				NumberConverter.setRadioButton("Exd",false);
			}
			if(e.getItem() == "Bin"){
				NumberConverter.setRadioButton("Dec",false);
				NumberConverter.setRadioButton("Exd",false);
			}
			if(e.getItem() == "Exd"){
				NumberConverter.setRadioButton("Bin",false);
				NumberConverter.setRadioButton("Dec",false);
			}
		}
}
