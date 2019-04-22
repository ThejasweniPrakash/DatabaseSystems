package dubstep;
//@//@Author - Apoorva Biseria
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.PrimitiveValue;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dubstep.ColumnInfo;
import dubstep.ConfigureVariables;

public class MainHandler  {

	//static HashMap<String, HashMap<String, ColumnInfo>> dbMap = new HashMap<String, HashMap<String,ColumnInfo>>();
	//static HashMap<String, CreateTable> createTableMap = new HashMap<>();
	//HashMap<String, BufferedReader> tablefile = new HashMap<String,BufferedReader>();
    
	public void createTable(CreateTable createTable) {
		
    	String tableName = createTable.getTable().getName().toLowerCase();
    	/*BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(ConfigureVariables.pathname + tableName + ".dat"));
			ConfigureVariables.tablefile.put(tableName,in);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		*/
    	
        List<ColumnDefinition> columnDefinitionList = createTable.getColumnDefinitions();
       //System.out.println(columnDefinitionList);
        ArrayList<String> col = new ArrayList<String>();
        HashMap<String, ColumnInfo> tableMap = new HashMap<>();
        for (int i = 0; i < columnDefinitionList.size(); i++) {
            String colName = columnDefinitionList.get(i).getColumnName().toLowerCase();
            ColDataType colDataType = columnDefinitionList.get(i).getColDataType();
            ColumnInfo columnInfo = new ColumnInfo(i, colDataType.getDataType());
            tableMap.put(tableName+"."+colName, columnInfo);
            //ConfigureVariables.tablecol.put(tableName,colName);
            col.add(colName);
            
        }
        
        /*
        System.out.println(" Table Map");
        for (Map.Entry<String, ColumnInfo> entry : tableMap.entrySet())
		{
			String s = entry.getKey();
			ColumnInfo c = entry.getValue();
			
			System.out.println(s +" : "+ c.colNo + "," + c.colDataType );
		}
		System.out.println("****************");
		System.out.flush();
        */
        ConfigureVariables.tablecol.put(tableName,col);
		ConfigureVariables.dbMap.put(tableName, tableMap);
		
        ConfigureVariables.createTableMap.put(tableName, createTable);
        //System.out.println(createTable.get("n"));
        //PrimitiveValue []t;
        //String line = "C|ANUNAY|RAO";
        //t = Tuple.getTuple(line, ConfigureVariables.createTableMap,ConfigureVariables.tableStrings);
		
        //System.out.println("Tuple:"+ t[1]);
		 
    }
    

    public static void  unionStatement(Union union) {
    	List<PlainSelect> plainSelectsList = union.getPlainSelects();
        for(PlainSelect ps : plainSelectsList) {
        	plainStatement(ps);
        	//System.out.println(ps);
        }
    	//System.out.println(plainSelectsList);
        //System.out.println("****Execute Plain Statement***");
		
	}
    
    /*
    public void plainSelect(SelectBody body, String basicPath) throws FileNotFoundException {
        PlainSelect plain = (PlainSelect) body;
        FromItem fromItem = plain.getFromItem();
        System.out.println(fromItem.toString());
        if (fromItem instanceof SubSelect || fromItem instanceof Select) {
            System.out.println("Able to detect Sub Select");
        }
        Table table = (Table) plain.getFromItem();

        String tableName = table.getName();
        String path = basicPath + tableName.toLowerCase() + ".csv";
        Scanner scanner = new Scanner(new File(path));
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            System.out.print(scanner.next() + "|");
            System.out.print(scanner.nextLine());
        }
        scanner.close();
    }*/

 
    


	public static void plainStatement(PlainSelect plain) {
		//System.out.println("Plain Select Statement");
		ConfigureVariables.testselect = plain.getSelectItems();
		//System.out.println("TEST SELECT" + ConfigureVariables.testselect);
		
		
		
		
		/*
		
		
		
		
		
		List<SelectItem> selectItem = plain.getSelectItems();
		if(selectItem.get(0) instanceof AllColumns) {
			//System.out.println("Hello");
			ConfigureVariables.allcol = true;
		}
		else {
		for (SelectItem i :selectItem){
			String colalias = "";
			//SelectItem jHandler =  i;
			Expression selectExpressionItems = ((SelectExpressionItem) i).getExpression();
			
			ConfigureVariables.colExp.add(selectExpressionItems);
			
			String colName = selectExpressionItems.toString();
			//Column column = (Column)jHandler;
			//System.out.println("hello"+column);
			ConfigureVariables.colNameStrings.add(colName);
			//System.out.println(selectExpressionItems);
			if(((SelectExpressionItem) i).getAlias()!=null) {
				colalias = ((SelectExpressionItem) i).getAlias();
			//System.out.println(colalias);
			//Configuration.aliases.put(nameTable, selectExpressionItems);
			}
			else {
				colalias = selectExpressionItems.toString();
			}
			ConfigureVariables.aliases.put(colName,colalias);
			//i.accept(selectItemVisitor);
			//System.out.println(Configuration.aliases);
		}
		for(String key:ConfigureVariables.aliases.keySet())
		{
			//System.out.println(key+":"+ConfigureVariables.aliases.get(key));
		}
		}
		
		
		
		
		
		*/
		
		
		
		
		
		
		
		
		FromItem fromitem = plain.getFromItem();
		
		if(fromitem instanceof SubSelect) {
			SubSelectHandler.excecute(plain);
			if(plain.getWhere()!=null)
			{  Expression expression =plain.getWhere();
				ConfigureVariables.whereArrayList.add(expression);
			}

		}
		else {
			Table tbTable = (Table)plain.getFromItem();
			String nameString = tbTable.getName();
			String tbalias = tbTable.getAlias();
			//System.out.println("ALIS"+tbalias);
			if(tbalias!=null) {
				
				ConfigureVariables.tablealias.put(nameString,tbalias);
				ConfigureVariables.aliastotable.put(tbalias,nameString);
				ConfigureVariables.talias.add(tbalias);
			}
			else {
				ConfigureVariables.tablealias.put(nameString,nameString);
				ConfigureVariables.aliastotable.put(nameString,nameString);
			}
			/*
			for(String k : ConfigureVariables.tablealias.keySet()) {
				System.out.println(k +":" + ConfigureVariables.tablealias.get(k));
				
			}
			*/
			ConfigureVariables.tableStrings.add(nameString);
			if(plain.getJoins()!=null)
			{
			List<Join> joins =  plain.getJoins();
			if (joins.contains("JOIN")) {
				ConfigureVariables.JOIN = 1;
			//System.out.println(joins.size());
				//Configuration.conditionsArrayList.add(true);
				
			for(Join i:joins)
			{
				System.out.println(i.getRightItem());
				String rightString =i.getRightItem().toString();
				ConfigureVariables.tableStrings.add(rightString);
				if(i.getOnExpression()!=null) {
					Expression onExpression = i.getOnExpression();
					ConfigureVariables.OnList.add(onExpression);
					
				}
				
			}}else {
				ConfigureVariables.joinsget = true;
			for(Join i:joins) {
				//System.out.println("IIII"+i);
				//Table nameTable =
				Table tb1 = (Table)i.getRightItem();
				String table = tb1.getName();
				String tb = ((Table)tb1).getAlias();
				//System.out.println("TB 1:" +tb1);
				if(tb!=null) {
					ConfigureVariables.talias.add(tb);
					ConfigureVariables.tablealias.put(table,tb);
					ConfigureVariables.aliastotable.put(tb,table);
					}
					//System.out.println("TBBBBB"+tb);
					//ConfigureVariables.tablealias.put(nameString,tbalias);
				
				else {
					ConfigureVariables.tablealias.put(table,table);
					ConfigureVariables.aliastotable.put(table,table);
				}
				ConfigureVariables.tableStrings.add(table);
			}
			/*
			System.out.println("TABLE ALIAS");
			for(String key : ConfigureVariables.tablealias.keySet()) {
				System.out.println(key +" :" + ConfigureVariables.tablealias.get(key));
			}
			System.out.println(ConfigureVariables.tableStrings);
			*/
			}
			
			}
			//else
			//System.out.println(joins);
			if(plain.getWhere()!=null) {
				Expression expression =plain.getWhere();
				//System.out.println(expression);
				ConfigureVariables.whereArrayList.add(expression);
				
				
			}
			}
			//System.out.println("Plain Select Statement");
		if(ConfigureVariables.joinsget) {
		TableScan.NestedScan(ConfigureVariables.tableStrings);	
		}
		else {
		TableScan.SimpleScan(ConfigureVariables.tablefile, ConfigureVariables.tableStrings);
		}
		
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////
		/*
		FromItemChecker fic = new FromItemChecker();
		fic.check(fromtable);
		Table table = (Table) plain.getFromItem();
		System.out.println("From Items:" + fromtable);
		System.out.println("Select:"+ selectItem);
		
		for (Map.Entry<String, HashMap<String, ColumnInfo>> entry : dbMap.entrySet())
		{
			String s = entry.getKey();
			HashMap<String, ColumnInfo> c = entry.getValue();
			for (Map.Entry<String, ColumnInfo> entry1 : c.entrySet())
			{
				String s1 = entry1.getKey();
				ColumnInfo c1 = entry1.getValue();
	
				System.out.println(s+":"+s1 +" : "+ c1.colNo +":"+c1.colDataType );
			}
			//System.out.println(s +" : "+ c );
		}
		System.out.println("****************");
		if(plain.getWhere()!=null) {
			System.out.println(plain.getWhere());
			Expression exp = plain.getWhere();
			
			//Tuple.getTuple(line, createTableMap, tableName)
			//Evaluator eval = new Evaluator(tuple,dbMap);
			
			
		}
		*/
		
	

