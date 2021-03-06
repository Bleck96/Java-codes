//Code viewer v 1.0
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.*;
import java.awt.event.*;

class CodeViewer extends JTextPane implements KeyListener {
	int newline_space = 0;
	Extractor fx = new Extractor();
	Font font = new Font("monospaced", Font.PLAIN,  16);
	//Array of Operator
	char operator[] = {'(',')','[',']','{','}',32,10,9,'+','-','/','*','=','%','&','|',',','.','<','>','!','^'};
	//ColorSet
	Color def_col_back = new Color(23,27,27); 
	Color def_col_fore = new Color(255,255,255); 
	//Color col_brackets = new Color(Color.ORANGE);
	//Syle definition
	StyledDocument doc = getStyledDocument();
	Style style = addStyle("ColorStyle", null);
	
	//Constructor	
	CodeViewer(String config_file_path){
		super();
		setTabSize(4);
		fx.setFile(config_file_path);
		setBackground(def_col_back);
		setForeground(def_col_fore.darker());
		addKeyListener(this);
		setFont(font);
		
	}
	CodeViewer(String config_file_path, Color back_color, Color fore_color){
		super();
		setTabSize(4);
		fx.setFile(config_file_path);
		setBackground(back_color);
		setForeground(fore_color);
		addKeyListener(this);
	}
	
	
	
	//--------------------------private methods------------------------------//
	private boolean checkOperator(char ch){
		for(int i=0;i<operator.length;i++){
			if(ch == operator[i]) return false;
		}
		return true;
	}
	
	private Color stringToColor(String color){
			if(color.equals("RED")) return Color.RED;
			if(color.equals("BLUE")) return Color.BLUE;
			if(color.equals("YELLOW")) return Color.YELLOW;
			if(color.equals("CYAN")) return Color.CYAN;
			if(color.equals("GRAY")) return Color.GRAY;
			if(color.equals("GREEN")) return Color.GREEN;
			if(color.equals("PINK")) return Color.PINK;
			if(color.equals("MAGENTA")) return Color.MAGENTA;
			if(color.equals("ORANGE")) return Color.ORANGE;
			return def_col_fore.darker();
	}
	
	//color Keyword
	private void coloration(String color,String keyword){
			Color clr = stringToColor(color);
			StyleConstants.setForeground(style, clr);	
		try { 
			if(clr != def_col_fore){
				if(keyword.length() > 1){
					doc.remove((getCaretPosition()-1)-keyword.length(),keyword.length());
					doc.insertString(getCaretPosition()-1,keyword,style); 
				}else if(keyword.length() == 1){
					doc.remove(getCaretPosition()-2,1);
					doc.insertString(getCaretPosition()-1,keyword,style); 
				}
			}
		}
        catch (BadLocationException e){}	
	}
	
	//Overload // Color Keyword start to position
	private void coloration(String color,String keyword,int pos){
	        Color clr = stringToColor(color);
			StyleConstants.setForeground(style, clr);	
        /////	
		try { 
			if(clr != def_col_fore){
				doc.remove(pos-keyword.length(),keyword.length());
				doc.insertString(pos-keyword.length(),keyword,style); 
			}
		}
        catch (BadLocationException e){}	
	}
	//Overlad 2 // Color single char
	private void coloration(String color,char ch){
	        Color clr = stringToColor(color);
			StyleConstants.setForeground(style, clr);	
        /////	
		try { 
			if(clr != def_col_fore){
				doc.remove(doc.getLength()-1,1);
				doc.insertString(doc.getLength(),ch+"",style); 
			}
		}
        catch (BadLocationException e){}	
	}
	//Found a keyword in Text 
	private String keywordFound(){
		String rtn = "";
		String tmp = "";
		int carr_pos = getCaretPosition();
		//estract part of text for keyword search
		try { 
		if(carr_pos>50)
			tmp = doc.getText(carr_pos-50,50);
		else
			tmp = doc.getText(0,carr_pos);
		}catch(BadLocationException a){
			System.out.println("exception");
			return rtn;
		};
		//Start check
		int i = 0;
		if(tmp.length() >= 2)i = tmp.length()-2;	
		while(checkOperator(tmp.charAt(i)) && i > 0){
			rtn += tmp.charAt(i);
			i--;
		}
		if(i == 0)rtn += tmp.charAt(i);		
		return mirrorString(rtn);
	}
	
	//recove original keyword
	private String mirrorString(String str){
		String rtn = "";
		if(!str.equals("")){
			for(int i = str.length()-1; i >= 0;i--){
				rtn += str.charAt(i);
			}
		}
		return rtn;
	}
	
	//simple check for auto spacing
	private boolean checkSpacingTabs(){
		String tmp = "";
		try{ tmp =  doc.getText(getCaretPosition()-2,1); }
		catch(BadLocationException a){};
		System.out.println((int)tmp.charAt(0));
		if(tmp.charAt(0) == 9) return true;
		else return false;
	}
	
	//--------------------------public methods------------------------------//
	
	public void resetConfigFile(String path){
		fx.setFile(path);
		reColorAllText();
	}
	
    public void setTabSize(int size){
		FontMetrics fm = getFontMetrics(getFont());
        int tabWidth = fm.charWidth( '_' ) * size;
        TabStop[] tabs = new TabStop[10];	  
		  
        for (int j = 0; j < tabs.length; j++) 
			tabs[j] = new TabStop( (j+1) * tabWidth );
		  
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setTabSet(attributes, new TabSet(tabs));
        doc.setParagraphAttributes(0, doc.getLength(), attributes, false);
	}
	
	
	//Gets number of Scope open start to carret position
	public int getScopeOpen(){
		int i = getCaretPosition();
		int rtn = 0;
		String str = "";
		try{
			str = doc.getText(0,i);
		}catch(BadLocationException a){};
		for(i-=1; i>0; i--) {
			if(str.charAt(i) == '{') rtn += 1;
			if(str.charAt(i) == '}') rtn -= 1;
		}
		if(rtn <0)rtn = 0;
		return rtn;
	}
	
	//Recolor all text
	public void reColorAllText(){
		String text = "";		
		try{ text =  doc.getText(0,doc.getLength());}
		catch(BadLocationException a){};
		String tmp = "";
		for(int i = 0; i < text.length(); i++){
			if(!checkOperator(text.charAt(i))){
				System.out.println(fx.contentOf(text.charAt(i)+""));
				coloration(fx.contentOf(text.charAt(i)+""),text.charAt(i)+"",i+1);
			}
			
			if(checkOperator(text.charAt(i))){
				tmp += text.charAt(i);
			}else if(!tmp.equals("")){
				coloration(fx.contentOf(tmp),tmp,i);
				//coloration(fx.contentOf(text.charAt(i)+""),text.charAt(i)+"",i);
				tmp = "";
			}
		}		
	}
	
	public void keyReleased(KeyEvent e){
		char ch = e.getKeyChar();
		//System.out.println((int)ch);
		//if key is space or new line check keyword
		if(!checkOperator(ch)){
			String key = keywordFound(); //extract keyword
			coloration(fx.contentOf(key),key); //check color for keyword
			//System.out.println(fx.contentOf(ch+""));
			coloration(fx.contentOf(ch+""),ch); //check color for keyword
		}	
	    if(ch == '}'){
			if(checkSpacingTabs()){
				try{ doc.remove(getCaretPosition()-2,1); }
				catch(BadLocationException a){};
			}
		}
		//reset default coloration
		if(ch == 32 || ch == 10){
			StyleConstants.setForeground(style, def_col_fore);
			if(ch == 10){
				try{
					String last_char = doc.getText(getCaretPosition()-2,1);
					if(!last_char.equals("}")){
						for(int i=0; i<getScopeOpen(); i++){
							doc.insertString(getCaretPosition(),(char)9 + "",style);
						}
					}else{
						doc.insertString(getCaretPosition(),(char)9 + "",style);
					}
				}catch(BadLocationException a){}
			}
		}
	}
	
	//For intarface--------------------------- 
	public void keyPressed(KeyEvent e){
		char ch = e.getKeyChar();
		if(ch == 'T') reColorAllText();
	};
	public void keyTyped(KeyEvent e){};

}