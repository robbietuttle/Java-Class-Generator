import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaClass {
	//==================================================Properties
	public static String name = null;
	public Boolean isAbstract = false;
	public String extend = null;
	public String implement = null;
	public Boolean clone = false;
	public Boolean emptyCon = false;
	public Boolean workHorse = false;
	public Boolean copyCon = false;

	public ArrayList<JavaProperty> properties = new ArrayList<JavaProperty>();



	//==================================================Constructor
	public JavaClass(Scanner fin) {
		name = Utilities.nextLineData(fin);
		isAbstract = Utilities.nextLineBoolean(fin);
		extend = Utilities.nextLineData(fin);
		implement = Utilities.nextLineData(fin);
		clone = Utilities.nextLineBoolean(fin);
		emptyCon = Utilities.nextLineBoolean(fin);
		workHorse = Utilities.nextLineBoolean(fin);
		copyCon = Utilities.nextLineBoolean(fin);

		while(true) {
			String s = fin.nextLine().replace("\t", "");
			if(s.contains("End Class:")) break;
			if(s.contains("Property:")) 
				properties.add(new JavaProperty(fin));
		}
	}



	public void save() {
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new File(name+".java"));
			//Prints Class Header
			pw.println("public" + (isAbstract?" abstract": "") 
					+ " class " + name + 
					(extend.length()>0? " extends " + extend :"" )+
					(implement.length()>0? " implements " + implement :"" )+
					"{");

			//Prints Properties in Class
			pw.println("//========================================================= Properties");
			for(JavaProperty p : properties) {
				pw.println(p.saveHeader());
			}
			pw.println();

			//Prints Constructors
			pw.println("//========================================================= Constructors");
			if(emptyCon) {
				pw.println("\tpublic "+name+"(){\n\t}");
			}


			if(workHorse) {
				String sendWorkHorse = "";
				for(JavaProperty p : properties) {
					sendWorkHorse=sendWorkHorse+","+p.getType() +" " +p.getName();
				}

				pw.println("\tpublic "+name+"("+sendWorkHorse.substring(1)
				+"){");
				for(JavaProperty p : properties) {
					pw.println("\t\t" +"this."+p.getName() +" = "+p.getName()+";");
				}
				pw.println("\t}");
			}

			if(copyCon) {
				pw.println("\tpublic "+name+"("+name+" x){");
				for(JavaProperty p : properties) {
					pw.println("\t\t this."+p.getName()+" = x."+p.getName()+";");
				}
				pw.println("\t}");
			}
			pw.println();

			//Prints Methods (if any?)
			if(clone) {

				pw.println("//========================================================= Methods");
				if(copyCon) {
					pw.println("\tpublic "+name+ " clone(){");
					pw.println("\t\treturn new "+name+"(this);\n\t}");
				}else if(workHorse) {
					String sendWorkHorse = "";
					for(JavaProperty p : properties) {
						sendWorkHorse=sendWorkHorse+","+p.getType() +" " +p.getName();
					}
					pw.println("\tpublic "+name+ " clone(){");
					pw.println("\t\t"+name+" c = new "+name+"("+(sendWorkHorse.substring(1))+");");
					pw.println("\t\treturn c;");
					pw.println("\t}");
				}else {
					pw.println("\tpublic "+name+ " clone(){");
					pw.println("\t\t"+name+" c = new "+name+"();");
					for(JavaProperty p : properties) {
						pw.println("\t\tc."+p.name+" = "+p.name+";");
					}
					pw.println("\t\treturn c;");
					pw.println("\t}");
				}
				pw.println();
			}

			//print getters and setters
			pw.println("//========================================================= Getters and Setters");
							for(JavaProperty p : properties) {
								pw.println(p.saveGetters());
							}
							for(JavaProperty p : properties) {
								pw.println(p.saveSetters());
							}

							//prints final curly brace
							pw.println("}");

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(pw != null) pw.close();
		}

	}
}
