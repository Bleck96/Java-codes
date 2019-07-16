import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Extractor{
	//Variables
		private List<String> LangElement = new ArrayList<String>();
	//Constructor
	Extractor(){
		LangElement.add("ERROR");
	}
	Extractor(String namefile){
		setFile(namefile);
	}
	//methods
//--------------------------------------------Public Methods---------------------------//
    public void addKeyword(String str, String color){
		LangElement.add(str + "=" + color);
	}
	
	public void setFile(String namefile){
		LangElement.clear();
		try{
			File file = new File(namefile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				LangElement.add(line);
			}
		}catch(IOException exc){
			LangElement.add("ERROR");
		}
	}
	//Return content of variable in textfile.lang
	public String contentOf(String str){
		String rtn = "NO_FOUND";
		if(LangElement.get(0) != "ERROR"){ 
		    Iterator <String> it = LangElement.iterator();
			while(it.hasNext()){
				rtn = "";
				String s = it.next();
				String key = "";
				for(int i = 0; i < s.length() && s.charAt(i) != '='; i++) key += s.charAt(i);
				if(str.equals(key)){
					boolean flag = false;
					for(int i = 0; i < s.length() ; i++){
						if(flag)rtn += s.charAt(i);
						if(s.charAt(i) == '=') flag = true;
					}
					return rtn;
				}	
			}
		}
		return rtn;
	}
	//Overload
	public static String contentOf(String path,String variable){ 
		String rtn = "NO_FOUND";
		try{
			File file = new File(path);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				if(line.contains(variable)){
					rtn = "";
					int i = 0;
					boolean flag = false; //when is true insert char after equals simbol
					while(i<line.length()){
						if(flag)rtn += line.charAt(i);
						if(line.charAt(i) == '=') flag = true;
						i++;
					}
				}
			}
		}catch(Exception exc){
			
		}
		return rtn;
	}
 
	
	public static String[] langFileList( String foldr){
		try{
			File folder = new File(foldr);
			File[] listOfFiles = folder.listFiles();
			String rtn[];
			rtn = new String[listOfFiles.length];
			for (int i = 0; i < listOfFiles.length; i++) {
				rtn[i] = listOfFiles[i].getName();
			}
			return rtn;
		}catch(Exception exp){
			String rtn[];
			rtn = new String[1];
			rtn[0] = "ERROR";
			return rtn;
		}
	}
	
}