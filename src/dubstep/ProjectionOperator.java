package dubstep;
import java.sql.SQLException;
////@Author - Anunay Rao
import java.util.ArrayList;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.PrimitiveValue;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

public class ProjectionOperator {

	public static void Projection(String tableName, PrimitiveValue [] t) {
		//System.out.println(ConfigureVariables.colNameStrings);
		//System.out.println("Ptanle"+tableName);
		//System.out.println(ConfigureVariables.tablealias.get(tableName));
		/*
		if(!ConfigureVariables.colNameStrings.get(0).contains(".")) {
			for(int i = 0; i<ConfigureVariables.colNameStrings.size(); i++) {
				ConfigureVariables.colNameStrings.set(i,tableName.toUpperCase() + "."+ConfigureVariables.colNameStrings.get(i));
				//System.out.println(ConfigureVariables.tablealias.get(tableName.toUpperCase()));
			}
			
		}
		*/
		
		//&& 
		
		//System.out.println("AFter"+ConfigureVariables.colNameStrings);
		//CREATE TABLE PLAYERS(ID string, FIRSTNAME string, LASTNAME string, FIRSTSEASON int, LASTSEASON int, WEIGHT int, BIRTHDATE date);
		//SELECT P1.FIRSTNAME FROM PLAYERS P1 WHERE P1.WEIGHT>200;
		ArrayList<String>  str1 = ConfigureVariables.tablecol.get(tableName);
		int i =0;
		for(SelectItem si : ConfigureVariables.testselect) {
			
			if(si instanceof AllColumns || (si.toString().equals(ConfigureVariables.tablealias.get(tableName.toUpperCase())+".*")) ){
				for ( int a =0; a<str1.size();a++) {
	        		//System.out.println(str.get(a));
	        		//System.out.println("S:"+s);
	        			if((a==str1.size()-1)&&(i == ConfigureVariables.testselect.size()-1)) {
	        				System.out.print(t[a].toRawString());
	        				
	        			}
	        			else {
	        			System.out.print(t[a].toRawString()+"|");
	        			
	        			}
	        		
	        	}
				i++;
			}
			else if(si instanceof SelectExpressionItem) {
				Evaluator eval = new Evaluator(t, ConfigureVariables.dbMap);
				try {
					PrimitiveValue result = eval.eval(((SelectExpressionItem) si).getExpression());
					if(i<ConfigureVariables.testselect.size()-1)
					System.out.print(result.toRawString()+"|");
					else
						System.out.print(result.toRawString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
			
			
			
		}
		
		System.out.println();
		
		/*
		
		
		
		if(!ConfigureVariables.allcol) {
			ArrayList<String>  str = ConfigureVariables.tablecol.get(tableName);
			for(int i = 0 ; i <ConfigureVariables.colExp.size(); i++) {
				Object o = ConfigureVariables.colExp.get(i);
				if(o instanceof Expression) {
					Evaluator eval = new Evaluator(t, ConfigureVariables.dbMap);
					try {
						PrimitiveValue result = eval.eval((Expression)o);
						if(i<ConfigureVariables.colExp.size()-1)
						System.out.print(result.toRawString()+"|");
						else
							System.out.print(result.toRawString());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			/////////////////////////////////////////////////
		/*	int k =1;
			//System.out.println(ConfigureVariables.colNameStrings);
		for(String s : ConfigureVariables.colNameStrings) {
			
        	ArrayList<String>  str = ConfigureVariables.tablecol.get(tableName);
        	//System.out.println("P1 aana : "+ConfigureVariables.tablealias.get(tableName.toUpperCase()));
        	
        	for ( int a =0; a<str.size();a++) {
        		//System.out.println(str.get(a));
        		
        		//System.out.println("S:"+s);
        		//Here -1
        		if(k==ConfigureVariables.colNameStrings.size()) {
        			if((ConfigureVariables.tablealias.get(tableName.toUpperCase())+"."+str.get(a).toUpperCase()).equals(s.toUpperCase())) {
            			
        				System.out.print(t[a].toRawString());
            			break;
        		}
        		}
        			else {
        		if((ConfigureVariables.tablealias.get(tableName.toUpperCase())+"."+str.get(a).toUpperCase()).equals(s.toUpperCase())) {
        			System.out.print(t[a].toRawString()+"|");
        			break;
        		}
        			}
        		
        	}
        	k++;
       
	}*/
		////////////////////////////////////////////////
		
		
		
		
		/*
		 
		 
		 
		 
		System.out.println();
	}
		else if(ConfigureVariables.allcol) {
			ArrayList<String>  str = ConfigureVariables.tablecol.get(tableName);
        	for ( int a =0; a<str.size();a++) {
        		//System.out.println(str.get(a));
        		//System.out.println("S:"+s);
        			if(a==str.size()-1) {
        				System.out.print(t[a].toRawString());
        				
        			}
        			else {
        			System.out.print(t[a].toRawString()+"|");
        			
        			}
        		
        	}
        	System.out.println();
        	
        	
		}
		*/
	}
	
	public static void NProjection(String tableName, PrimitiveValue []otuple, PrimitiveValue []ituple) {
		//System.out.println(tableName);
		//System.out.println(ConfigureVariables.tablecol.get(tableName.toLowerCase()).get(0));
		//System.out.println(ConfigureVariables.talias);
		ArrayList<String>  str1 = ConfigureVariables.tablecol.get(tableName.toLowerCase());
		int i =0;
		for(SelectItem si : ConfigureVariables.testselect) {
			
			if((si.toString().equals(ConfigureVariables.talias.get(0).toUpperCase()+".*"))   ){
				for ( int a =0; a<str1.size();a++) {
	        		//System.out.println(str.get(a));
	        		//System.out.println("S:"+s);
	        			if((a==str1.size()-1)&&(i == ConfigureVariables.testselect.size()-1)) {
	        				System.out.print(otuple[a].toRawString());
	        				
	        			}
	        			else {
	        			System.out.print(otuple[a].toRawString()+"|");
	        			
	        			}
	        		
	        	}
				i++;
			}
			else if((si.toString().equals(ConfigureVariables.talias.get(1).toUpperCase()+".*"))) {
				for ( int a =0; a<str1.size();a++) {
	        		//System.out.println(str.get(a));
	        		//System.out.println("S:"+s);
	        			if((a==str1.size()-1)&&(i == ConfigureVariables.testselect.size()-1)) {
	        				System.out.print(ituple[a].toRawString());
	        				
	        			}
	        			else {
	        			System.out.print(ituple[a].toRawString()+"|");
	        			
	        			}
	        		
	        	}
				i++;
			}
			else if(si instanceof SelectExpressionItem) {
				JoinEvaluator eval = new JoinEvaluator(otuple,ituple, ConfigureVariables.dbMap);
				try {
					PrimitiveValue result = eval.eval(((SelectExpressionItem) si).getExpression());
					if(i<ConfigureVariables.testselect.size()-1)
					System.out.print(result.toRawString()+"|");
					else
						System.out.print(result.toRawString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
			
			
			
		}
/*		
for(SelectItem si : ConfigureVariables.testselect) {
			
			if(si instanceof AllColumns || (si.toString().equals(ConfigureVariables.tablealias.get(tableName.toUpperCase())+".*")) ){
				for ( int a =0; a<str1.size();a++) {
	        		//System.out.println(str.get(a));
	        		//System.out.println("S:"+s);
	        			if((a==str1.size()-1)&&(i == ConfigureVariables.testselect.size()-1)) {
	        				System.out.print(ituple[a].toRawString());
	        				
	        			}
	        			else {
	        			System.out.print(ituple[a].toRawString()+"|");
	        			
	        			}
	        		
	        	}
				i++;
			}
			else if(si instanceof SelectExpressionItem) {
				JoinEvaluator eval = new JoinEvaluator(otuple,ituple, ConfigureVariables.dbMap);
				try {
					PrimitiveValue result = eval.eval(((SelectExpressionItem) si).getExpression());
					if(i<ConfigureVariables.testselect.size()-1)
					System.out.print(result.toRawString()+"|");
					else
						System.out.print(result.toRawString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
			
			
			
		}
		*/
		
		System.out.println();
		
	}
	
}
	
	

