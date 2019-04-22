package dubstep;
////@Author - Anunay Rao
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.sf.jsqlparser.expression.PrimitiveValue;

public class TableScan {

	static PrimitiveValue []ot ;
	static PrimitiveValue []it ;
	static PrimitiveValue []t;
	public static void SimpleScan(HashMap<String, BufferedReader> tablefile, ArrayList<String> tableStrings) {
		int i;
		//System.out.println("Hello" + tableStrings);
		for( i = 0; i < tableStrings.size();i++ ) {
			
		BufferedReader br = null ;
		//= ConfigureVariables.tablefile.get(ConfigureVariables.tableStrings.get(i).toLowerCase());
		try {
			br = new BufferedReader(new FileReader(ConfigureVariables.pathname + ConfigureVariables.tableStrings.get(i) + ConfigureVariables.FILEEXTENSION));
			//ConfigureVariables.tablefile.put(tableName,in);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		//if(br == null)
		//	System.out.println("NULLLLL");
		//System.out.println(tableStrings.get(0));
		//BufferedReader r = tablefile.get(tableStrings.get(0));
		/*
		try {
			
			System.out.println(r.readLine());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		/*
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(ConfigureVariables.pathname + "T.dat"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		String line;
	    try {
			while ((line = br.readLine()) != null) {
			    // process line here.
				//System.out.println(line);
				//List<String> items = Arrays.asList(line.split("\\s*\\|\\s*"));
				//System.out.println(items.get(0)+"|"+items.get(1));
				//t = Tuple.getTuple(line, ConfigureVariables.createTableMap,tableStrings.get(i));
				t = Tuple.getTuple(line, ConfigureVariables.createTableMap,ConfigureVariables.tableStrings.get(i).toLowerCase());
				//System.out.println("Tuple:"+ t);
				SelectionOperator.Selection(t,ConfigureVariables.tableStrings.get(i).toLowerCase());
				}
			ConfigureVariables.reset();
				//br = new BufferedReader(new FileReader(ConfigureVariables.pathname + ConfigureVariables.tableStrings.get(i) + ".dat"));
				//ConfigureVariables.tablefile.put(ConfigureVariables.tableStrings.get(i),br);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception");
			e.printStackTrace();
		}
			
		}
	
	
		
	}
	
	
	@SuppressWarnings("null")
	public static void NestedScan(ArrayList<String> tableStrings) {
		
		
			
		BufferedReader or = null ;
		try {
			or = new BufferedReader(new FileReader(ConfigureVariables.pathname + ConfigureVariables.tableStrings.get(0) + ConfigureVariables.FILEEXTENSION));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		String oline;
	    try {
			while ((oline = or.readLine()) != null) {
			    //System.out.println(oline);
				ot = Tuple.getTuple(oline, ConfigureVariables.createTableMap,ConfigureVariables.tableStrings.get(0).toLowerCase());
				//System.out.println(ot[0]);				
				//SelectionOperator.Selection(ot,ConfigureVariables.tableStrings.get(0).toLowerCase());
			    BufferedReader ir = null ;
				try {
					ir = new BufferedReader(new FileReader(ConfigureVariables.pathname + ConfigureVariables.tableStrings.get(1) + ConfigureVariables.FILEEXTENSION));
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			    String iline;
				while((iline = ir.readLine()) != null) {
				//System.out.println(iline);	
				it = Tuple.getTuple(iline, ConfigureVariables.createTableMap,ConfigureVariables.tableStrings.get(1).toLowerCase());
				//System.out.println(it[0]);	
				SelectionOperator.NSelection(ot,it,tableStrings.get(0));	
				}
			
			}
			ConfigureVariables.reset();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception");
			e.printStackTrace();
		}
			
		
		
		
		
	}
}
