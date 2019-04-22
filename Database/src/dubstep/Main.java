package dubstep;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

/*import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;*/

import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.Union;


public class Main {
	
	public static void main(String[] args) throws ParseException, IOException {

		System.out.print(ConfigureVariables.PROMPT);
		System.out.flush();
		
		Reader input = new InputStreamReader(System.in);
		CCJSqlParser parser = new CCJSqlParser(input);
		
		Statement statement ;
		SQLParserMethod sqlParserMethod = new SQLParserMethod();

		String basicPath = "C:\\Users\\theja\\Database\\Database\\src\\data\\";

		
		while((statement=parser.Statement()) != null) {
			
			if (statement instanceof CreateTable) {
                CreateTable createTable = (CreateTable)statement;
                sqlParserMethod.createTable(createTable);
                
        		input = new InputStreamReader(System.in);
        		
                
            }
			else if (statement instanceof Select) {
                
				SelectBody selectBody = ((Select)statement).getSelectBody();

                if(selectBody instanceof Union) {
                    Union union = (Union)selectBody;
                    sqlParserMethod.unionStatement(union);
                    
                    
                   // HashMap<String, Integer> columnIndex = new HashMap<>();
                   //HashSet<String> unionResult = new HashSet<String>();
                   // Column[] tempSchema = null;
                    
                    System.out.print(ConfigureVariables.PROMPT);
        			System.out.flush();
        			System.gc();	
		}
			
			else {
				
				System.out.println("Plain Select Statement");
				PlainSelect plain = (PlainSelect)selectBody;
				//sqlParserMethod.plainStatement(plain);
					sqlParserMethod.plainSelect(plain,basicPath);
				
			}
		}
		
		System.out.print(ConfigureVariables.PROMPT);
    	//System.out.flush();
    	//System.gc();
    	
    	input = new InputStreamReader(System.in);
    	parser = new CCJSqlParser(input);
    	
    	
    	/*
		System.out.println("Create Table Map");
		for (Map.Entry<String, CreateTable> entry : sqlParserMethod.createTableMap.entrySet())
		{
			String s = entry.getKey();
			CreateTable c = entry.getValue();
			System.out.println(s +" : "+ c );
		}
		System.out.println("****************");
		*/
		
					
				}
			}
						
}
