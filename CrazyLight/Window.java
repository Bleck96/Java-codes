import java.awt.*;
public class Window{
	static BtnClass arr[] = new BtnClass[100]; 	
	static Label l = new Label("Score:0");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       WindHome w = new WindHome(700,700);
       BtnClass Exit = new BtnClass("Exit","close",w,10,30,30,20,-1);
       BtnClass Res = new BtnClass("Reset","reset",w,40,30,60,20,-1);
       l.setBounds(100,30,110,20);
       w.add(l);
       //prato fiorito
       int rg = 0;
       for(int i = 0; i<100;i++) {
    	   if(i%10 == 0 && i != 0)rg+= 20;
    	   arr[i] = new BtnClass("","colorize",w,(0+(i%10)*20)+10,50+rg,20,20,i);
       }
       w.ResetSize(220, 260);
	}

}
