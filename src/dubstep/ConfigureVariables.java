package dubstep;
//@author - Anunay Rao

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.SelectItem;

public class ConfigureVariables {

	  public static String PROMPT = "$> ";
	 // public static String FILEPATH = "/Users/anunay/eclipse-workspace/Checkpoint0/src/dubstep/parsers/";
	 public static String FILEEXTENSION= ".csv";
	 public static String pathname = "data/";
	  
	//public static String FILEEXTENSION= ".dat";
	//public static String pathname = "/Users/anunay/eclipse-workspace/Checkpoint0/src/dubstep/parsers/";
	  
	  public static final String DELIMITER = "|";
	  public static List<String> colNameStrings = new ArrayList<String>();
	  public static HashMap <String,String>  aliases = new HashMap<String, String>();
	  public static ArrayList<Expression> whereArrayList =new ArrayList<Expression>();
	  public static ArrayList<String> tableStrings = new ArrayList<String>();
	  public static int JOIN = 0;
	  public static ArrayList<Expression> OnList = new ArrayList<Expression>();
	  static HashMap<String, HashMap<String, ColumnInfo>> dbMap = new HashMap<String, HashMap<String,ColumnInfo>>();
	  static HashMap<String, CreateTable> createTableMap = new HashMap<>();
	  static HashMap<String, BufferedReader> tablefile = new HashMap<String,BufferedReader>();
	  static HashMap<String, ArrayList<String>> tablecol = new HashMap<String,ArrayList<String>>();
	  static boolean allcol = false;
	  public static HashMap<String,String> tablealias = new HashMap<String, String>();
	  public static ArrayList<Object> colExp = new ArrayList<Object>(); 
	  public static List<SelectItem> testselect = new ArrayList<SelectItem>();
	public static boolean joinsget = false;
	 public static HashMap<String,String> aliastotable = new HashMap<String, String>();
	 public static ArrayList<String> talias = new ArrayList<String>();
/*

public static void main(String arg[]) {
	
	System.out.println(DELIMITER);

}
*/
@SuppressWarnings("unused")
public static void reset() {
	//ArrayList<Expression> whereArrayList, ArrayList<String> tableStrings,List<String> colNameStrings
		whereArrayList.clear();
		tableStrings.clear();
		colNameStrings.clear();
		allcol = false ;
		tablealias.clear();
		colExp.clear();
		testselect.clear();
		joinsget = false;
		aliastotable.clear();
		talias.clear();
	  //System.out.println(whereArrayList);
	  //System.out.println(tableStrings);
	  //System.out.println(colNameStrings);
}

}