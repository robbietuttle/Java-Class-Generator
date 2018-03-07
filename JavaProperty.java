import java.util.Scanner;

public class JavaProperty {
	//=================================================Properties
	public String name;
	public String type;
	public String scope;
	public Boolean getter;
	public String getterScope;
	public Boolean setter;
	public String setterScope;

	//==================================================Constructor
	public JavaProperty(Scanner fin) {
		name = Utilities.nextLineData(fin);
		type = Utilities.nextLineData(fin);
		scope = Utilities.nextLineData(fin);
		getter = Utilities.nextLineBoolean(fin);
		getterScope = Utilities.nextLineData(fin);
		setter = Utilities.nextLineBoolean(fin);
		setterScope = Utilities.nextLineData(fin);
	}

	//===================================================Save Header, Getters, And Setters

	public String saveHeader() {
		return ("\t"+scope.toLowerCase()+" "+Utilities.camelCase(type)+ " "+ name.toLowerCase()+";");
	}
	
	public String saveGetters() {
		if(getter)
			return("\t"+getterScope.toLowerCase()+" "+Utilities.camelCase(type)+ " get"+Utilities.camelCase(name)+"(){\n\t\t"
					+"return "+name+";\n"
							+ "\t}");
		else
			return "";
	}
	
	public String saveSetters() {
		if(setter)
			return("\t"+setterScope.toLowerCase()+" "+Utilities.camelCase(type)+ " set"+Utilities.camelCase(name)+"(){\n\t\t"
					+"this."+name+" = "+name+";"
					+ "\n\t}");
		else
			return "";
	}
	
	//==================================================Getters
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public void setName(String name) {
		this.name = name;
	}
}