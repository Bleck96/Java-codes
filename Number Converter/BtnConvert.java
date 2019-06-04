import java.awt.event.*;
class BtnConvert implements ActionListener{
	int txt_nb = 0;
	//Override
	public void actionPerformed(ActionEvent e) {
		if(NumberConverter.checkRadioButton("Dec")){
			try{
				txt_nb = Integer.parseInt(NumberConverter.getText("Dec"));
				NumberConverter.setText("Bin",Integer.toBinaryString(txt_nb));
				NumberConverter.setText("Exd",Integer.toHexString(txt_nb));
			}catch(Exception exc){
				NumberConverter.setText("Dec","0");
				NumberConverter.setText("Bin","0");
				NumberConverter.setText("Exd","0");
			}
		}
		if(NumberConverter.checkRadioButton("Bin")){
			try{
				txt_nb = Integer.parseInt(NumberConverter.getText("Bin"),2);
				NumberConverter.setText("Dec",Integer.toString(txt_nb));
				NumberConverter.setText("Exd",Integer.toHexString(txt_nb));
			}catch(Exception exc){
				NumberConverter.setText("Dec","0");
				NumberConverter.setText("Bin","0");
				NumberConverter.setText("Exd","0");
			}
		}
		if(NumberConverter.checkRadioButton("Exd")){
			try{
				txt_nb = Integer.parseInt(NumberConverter.getText("Exd"),16);
				NumberConverter.setText("Bin",Integer.toBinaryString(txt_nb));
				NumberConverter.setText("Dec",Integer.toString(txt_nb));
			}catch(Exception exc){
				NumberConverter.setText("Dec","0");
				NumberConverter.setText("Bin","0");
				NumberConverter.setText("Exd","0");
			}
		}
	}
}