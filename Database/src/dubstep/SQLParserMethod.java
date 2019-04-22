package dubstep;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dubstep.ColumnInfo;
import dubstep.ConfigureVariables;
import java.util.Map.Entry;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.Union;
import net.sf.jsqlparser.expression.*;

public class SQLParserMethod implements SQLParserMethodInterface {

    HashMap<String, HashMap<String, ColumnInfo>> dbMap = new HashMap<String, HashMap<String, ColumnInfo>>();
    HashMap<String, CreateTable> createTableMap = new HashMap<>();
    Map<Integer, String> rowData = new HashMap<Integer, String>();
    public SQLParserMethod() {
    }

    public void createTable(CreateTable createTable) {
        String tableName = createTable.getTable().getName();
        List<ColumnDefinition> columnDefinitionList = createTable.getColumnDefinitions();
        System.out.println(columnDefinitionList);
        HashMap<String, ColumnInfo> tableMap = new HashMap<>();
        for (int i = 0; i < columnDefinitionList.size(); i++) {
            String colName = columnDefinitionList.get(i).getColumnName();
            ColDataType colDataType = columnDefinitionList.get(i).getColDataType();
            ColumnInfo columnInfo = new ColumnInfo(i, colDataType.getDataType());
            tableMap.put(tableName+ "." +colName, columnInfo);
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

        dbMap.put(tableName, tableMap);
        createTableMap.put(tableName, createTable);

    }


    @Override
    public void unionStatement(Union union) {
        List<PlainSelect> plainSelectsList = union.getPlainSelects();
        System.out.println(plainSelectsList);
        System.out.println("****Execute Plain Statement***");

    }


    public void plainSelect(SelectBody body, String basicPath) throws FileNotFoundException {
        PlainSelect plain = (PlainSelect) body;
        List selectItems = plain.getSelectItems();
        FromItem fromItem = plain.getFromItem();
        System.out.println(fromItem.toString());
        if (fromItem instanceof SubSelect || fromItem instanceof Select) {
            System.out.println("Able to detect Sub Select");
        }
        Table table = (Table) plain.getFromItem();

        String tableName = table.getName();
        String path = basicPath + tableName.toLowerCase() + ".dat";
        //Scanner scanner = new Scanner(new File(path));
        //scanner.useDelimiter("|");
        //while (scanner.hasNext()) {
            //System.out.print(scanner.next() + "|");
          //  System.out.print(scanner.nextLine());
        //}
        //scanner.close();



        Scanner fileScanner = new Scanner(new File(path));
        fileScanner.useDelimiter("|");
        //System.out.println("Reaching while");
        while (fileScanner.hasNext()) {
            //System.out.println("Reached while");
            String rowSplit = fileScanner.nextLine();
            String[] splitRow = rowSplit.split("\\|"); //escaping pipe
            //writing data to rowData . data is stored as <colNo, Data> <0,1234> is 0 is ID colNo and 1234 is the data is .dat file

            for (int i = 0; i < splitRow.length; i++) {
                rowData.put(i , splitRow[i]);
                //System.out.println("Var value:" + splitRow[i] );
            }
            HashMap<String, ColumnInfo> column;
            ColumnInfo colInfo ;
            int  columnId = -1; //because colId starts from 0
            int j=0;
            column = dbMap.get(tableName); //colId has tablename and <colname,colinfo>  and colinfo has colnum and datatype

            //size - 1 to avoid the pipe for last col data
            for (j = 0; j < selectItems.size() - 1; j++) {

                colInfo = (ColumnInfo) column.get(selectItems.get(j).toString()); //typecasting to ColumnInfo type. colInfo has ColumnInfo data
                columnId = colInfo.colNo; //gets colNo

                System.out.print(rowData.get(columnId) + "|"); //data is fetched through rowData
            }
            colInfo = (ColumnInfo) column.get(selectItems.get(j).toString());
            columnId = colInfo.colNo;
            System.out.println(rowData.get(columnId));


        }
        fileScanner.close();
   /* public void nestedSelect(SelectBody selectBody, String basicPath) {
        PlainSelect plain = (PlainSelect) selectBody;
        SubSelect plainSelect = (SubSelect) plain.getFromItem();
        SelectBody selectBody1 = plainSelect.getSelectBody();
        PlainSelect plainSelect1 = (PlainSelect) selectBody1;
        Table table = (Table) plainSelect1.getFromItem();
        System.out.println(table.getName());
    }*/

/*
       @Override
        public void plainStatement(PlainSelect plain){

		List selectitems = plain.getSelectItems();
		FromItem fromtable = plain.getFromItem();
		Table table = (Table) plain.getFromItem();
		System.out.println("From Items:" + fromtable);
		System.out.println("Select:"+ selectitems);
		
		for (Map.Entry<String, HashMap<String, ColumnInfo>> entry : dbMap.entrySet())
		{
			String s = entry.getKey();
			HashMap<String, ColumnInfo> c = entry.getValue();
			for (Map.Entry<String, ColumnInfo> entry1 : c.entrySet())
			{
				String s1 = entry1.getKey();
				ColumnInfo c1 = entry1.getValue();
	
				//System.out.println(s+":"+s1 +" : "+ c1.colNo +":"+c1.colDataType );
			}
			//System.out.println(s +" : "+ c );
		}
		//integrating
    public void plainStatement(PlainSelect plain) {
        List selectitems = plain.getSelectItems();
        FromItem fromtable = plain.getFromItem();
        Table table = (Table)plain.getFromItem();
        System.out.println("From Items:" + fromtable);
        System.out.println("Select:" + selectitems);
        Iterator var6 = this.dbMap.entrySet().iterator();


		//System.out.println("****************");
		
	}

            while(var10.hasNext()) {
                Entry<String, ColumnInfo> entry1 = (Entry)var10.next();
                String s1 = (String)entry1.getKey();
                ColumnInfo c1 = (ColumnInfo)entry1.getValue();
                System.out.println(s + ":" + s1 + " : " + c1.colNo + ":" + c1.colDataType);
            }
        }
        */

    }
}
