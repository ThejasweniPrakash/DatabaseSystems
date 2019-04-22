package dubstep;
////@Author - Anunay Rao,Apoorva Biseria
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;



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
		MainHandler sqlParserMethod = new MainHandler();
		
		while((statement=parser.Statement()) != null) {
			
			if (statement instanceof CreateTable) {
                CreateTable createTable = (CreateTable)statement;
                sqlParserMethod.createTable(createTable);
                
        		//input= new InputStreamReader(System.in);
        		
                
            }
			else if (statement instanceof Select) {
                
				SelectBody selectBody = ((Select)statement).getSelectBody();

                if(selectBody instanceof Union) {
                    
                    
                    Union union =(Union)selectBody;
            		//List<PlainSelect> plainSelects =union.getPlainSelects();
            		MainHandler.unionStatement(union);
                    //System.out.println(plainSelects.get(0));
            		//System.out.println(plainSelects.get(1));
            		/*
            		for(PlainSelect i:plainSelects) {
            			SQLParserMethod.plainStatement(i);
            		}
            		*/
            		//System.out.println("UNION");
                    //System.out.print(ConfigureVariables.PROMPT);
        			//System.out.flush();
        			//System.gc();	
            		
		}
			
			else {
				
				//System.out.println("Plain Select Statement");
				PlainSelect plain = (PlainSelect)selectBody;
				MainHandler.plainStatement(plain);
				
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
