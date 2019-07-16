import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.text.JTextComponent;
import javax.swing.*;
class WatchMethods{
	ArrayList<String> lst_methods = new ArrayList<String>();
	private String lib_name = "Unknow";
	//Costruttori
	WatchMethods(String file, Extractor ex){
		setFile(file, ex);
	}
	
	WatchMethods(String file, Extractor ex, String lib_name){
		this.lib_name = lib_name; 
		setFile(file, ex);
		sort();
	}
//----------------------------Private Methods------------------------------------------//
	private String isMethods(String str){
		String tmp = "";
		boolean type_found = false;
		for(int i = 0; i < str.length() && str.charAt(i) != '('; i++){
			//Ignora gli spazi
			if(str.charAt(i) != 32) tmp += str.charAt(i);
			//se è un commento
			if(tmp.equals("//")) return "NO_FOUND";
			//trova un tipo
			if(tmp.equals("void") || tmp.equals("char") || tmp.equals("int") || tmp.equals("float") && !type_found){
				type_found = true;
				tmp = "";
			}
		}
		//System.out.println(tmp);
		if(type_found) return tmp;
		else return "NO_FOUND";
	}
//----------------------------Public Methods-------------------------------------------//
	public String getLibName(){
		return lib_name;
	}
	
    public void sort(){
		for(int i = 0; i < lst_methods.size(); i++){
			for(int j = 0; j < lst_methods.size(); j++){
				if(lst_methods.get(j).compareTo(lst_methods.get(i)) > 0){
					 String tmp = lst_methods.get(j);
					 lst_methods.set(j,lst_methods.get(i));
					 lst_methods.set(i,tmp);
				}
			}
		}
	}

	public void showMethods(TextComponent tc){
		 sort();
		 Iterator <String> it = lst_methods.iterator();
		 String tmp = "";
		 while(it.hasNext()){
			tmp += it.next() + '\n';
		 }
		 tc.setText(tmp);
	}
	//Overload 
	public void showMethods(JTextComponent tc){
		 Iterator <String> it = lst_methods.iterator();
		 String tmp = "";
		 while(it.hasNext()){
			tmp += it.next() + "       " +'\n';
		 }
		 tc.setText(tmp);
	}

	public String[] getMethodList(){
		 String rtn[] = new String[lst_methods.size()];
		 Iterator <String> it = lst_methods.iterator();
		 int i = 0;
		 while(it.hasNext()){
			rtn[i] = it.next() + "      ";
			i++;
		 }
		 return rtn;
	}
	
	private void setFile(String namefile, Extractor ex){
		lst_methods.clear();
		try{
			File file = new File(namefile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				String nwline = isMethods(line);
				if(!nwline.equals("NO_FOUND")){
					lst_methods.add(nwline);
					ex.addKeyword(nwline,"RED");
				}
			}
		}catch(IOException exc){
			System.out.println("Err");
		}
	}
	
	
}