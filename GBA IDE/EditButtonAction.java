import java.awt.event.*;

class EditButtonAction implements ActionListener{
	CodeViewer cw;
	GameObject gm;
	
	EditButtonAction(CodeViewer cw, GameObject gm){
		this.cw = cw;
		this.gm = gm;
	}
	
	public void actionPerformed(ActionEvent e){
		int id = Integer.parseInt(e.getSource().toString());
		//System.out.println(id);
		cw.setText("");
		cw.append(gm.getEvent(id));
		cw.reColorAllText();
	}
}