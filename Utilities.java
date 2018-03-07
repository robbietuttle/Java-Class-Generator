
	import java.util.Scanner;

	public abstract class Utilities {

		//The following methods work with the format given in
		//the project file
		
		public static String[] parseLine(String line) {
			return line.replace("\t","").trim().split(":");
		}
		
		public static String nextLineData(Scanner fin)  {
			try {
				return parseLine(fin.nextLine())[1].trim();
			} catch(Exception e) {
				return "";
			}
		}
		
		public static  boolean nextLineBoolean(Scanner fin) {
			return nextLineData(fin).equalsIgnoreCase("true");
		}
		
		// A method that can be used to upper the first letter
		// of a string
		public static String camelCase(String value) {
			if(value.length() == 0) return "";
			return (value.charAt(0)+"").toUpperCase() + 
					value.substring(1);
		}
	}


