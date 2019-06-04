import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Lang{
	//Variables
		private List<String> LangElement = new ArrayList<String>();
	//Constructor
	Lang(String namefile){
		setLang(namefile);
	}
	//methods
	
	public void setLang(String namefile){
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
	public String contentOf(String variable){ 
		String rtn = "NO_FOUND";
		if(LangElement.get(0) != "ERROR"){ 
		    Iterator <String> it = LangElement.iterator();
			while(it.hasNext()){
				String s = it.next();
				if(s.contains(variable)){
					rtn = "";
					int i = 0;
					boolean flag = false; //when is true insert char after equals simbol
					while(i<s.length()){
						if(flag)rtn += s.charAt(i);
						if(s.charAt(i) == '=') flag = true;
						i++;
					}
				}
			}
		}
		return rtn;
	}
 
	
	public void langFileList(){
		try{
			File folder = new File("lang");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				NumberConverter.addLang(listOfFiles[i].getName());
			}
		}catch(Exception exp){
			NumberConverter.addLang("ERROR");
		}
	}
	
}