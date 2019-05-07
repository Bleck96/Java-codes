import java.awt.*;
import java.awt.event.*;


public class BtnClass implements ActionListener{
	Button b;
	String act;
	Frame f;
	int point = 0;
	BtnClass(String lab,String action, Frame f, int x, int y, int width, int height, int point){
		act = action;
		b = new Button(lab);
		b.setBounds(x, y, width, height);
		b.addActionListener(this);
		this.f = f;
		if(lab.equals(""))b.setBackground(Color.RED);
		f.add(b);
		this.point = point;
	}
	public void clear_color(BtnClass btn[]) {
		for(int i = 0; i<100;i++) {
			if(btn[i].b.getBackground() == Color.BLACK) {
				btn[i].b.setBackground(Color.RED);
			}
		}
	}
	public void recolorize() {
		if(b.getBackground() == Color.RED)b.setBackground(Color.BLACK);
		else b.setBackground(Color.RED);
	}
	
	public void GameEvent(BtnClass btn[]) {
		if(point-1>= 0) btn[point-1].recolorize(); 
		if(point+1<= 99) btn[point+1].recolorize();
		if(point-10>=0) btn[point-10].recolorize();
		if(point+10<=99) btn[point+10].recolorize();
	}
	
	private String updateScore(BtnClass btn[]) {
		int score = 0;
		for(int i = 0; i<100;i++) {
			if(btn[i].b.getBackground() == Color.BLACK) score++;
		}
		if(score<100)return "Score: " + score;
		else return "YOU WIN!";
	}
	
	//Override
	public void actionPerformed(ActionEvent ae) {
			if(act.equals("close"))System.exit(0);
			if(act.equals("colorize")) { 
				recolorize();
				GameEvent(Window.arr);
				Window.l.setText(updateScore(Window.arr));			
			}
			if(act.equals("reset")) {
				clear_color(Window.arr);
			}
	}
	
}
