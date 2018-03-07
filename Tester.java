import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class Tester {
	private static ArrayList<JavaClass> classes = new ArrayList<JavaClass>();
	private static ArrayList<JavaInterface> interfaces = new ArrayList<JavaInterface>();

	public static void main(String[] args) {
		Scanner fin = null;
		try {
			fin = new Scanner(new File("Script.txt"));
			while(fin.hasNextLine()) {
				String s = fin.nextLine();
				if(s.contains("Class:")) {
					classes.add(new JavaClass(fin));
				}else if(s.contains("Interface:")) {
					interfaces.add(new JavaInterface(fin));
				}
			}
		}catch(Exception e) {
			//this is printing no line found and not allowing save interface
			System.out.println(e.getMessage());
		}finally {
			if(fin != null) fin.close();
		}
		for(JavaClass jc : classes)
			jc.save();
		
		for(JavaInterface i : interfaces) {
			i.save();
		}
		
	}
}
