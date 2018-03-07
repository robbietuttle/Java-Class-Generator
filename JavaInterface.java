import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class JavaInterface {
	//================================================== Properties
	public String name;
	ArrayList<String> methods = new ArrayList<String>();

	//================================================== Constructor
	public JavaInterface(Scanner fin) {
		name = Utilities.nextLineData(fin);
		while(true) {
			String s = fin.nextLine().replace("\t", "");
			if(s.contains("End Interface:")) break;
			if(s.contains("Method:"))
				methods.add(Utilities.parseLine(s)[1]);
		}
	}
	//================================================== Save
	public void save() {
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new File(name+".java"));

			pw.println("public interface "+name+" {");
			for(String m : methods) {
				pw.println("\t"+m);
			}
			pw.println("}");

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(pw != null) pw.close();
		}

	}
}

