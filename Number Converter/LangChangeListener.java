import java.awt.event.*;
class LangChangeListener implements ItemListener{
	//Override
	public void itemStateChanged(ItemEvent ie){
		NumberConverter.resetLang("lang/"+ie.getItem());
	}
}