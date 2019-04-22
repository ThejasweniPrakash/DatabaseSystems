package dubstep;
////@Author - Anunay Rao
import java.sql.SQLException;

import net.sf.jsqlparser.expression.BooleanValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.PrimitiveValue;

public class SelectionOperator {
	
	
	public static void Selection(PrimitiveValue []tuple, String tableName) {
		int count = 0;
		Evaluator eval = new Evaluator(tuple, ConfigureVariables.dbMap);
		if(!ConfigureVariables.whereArrayList.isEmpty()) {
	
			for(Expression condition : ConfigureVariables.whereArrayList) {
				try {
					PrimitiveValue result = eval.eval(condition);
					 BooleanValue boolResult = (BooleanValue)result;
					 if(boolResult.getValue()) {
						 count++;
						 
						 //for( PrimitiveValue v : tuple)
						 //System.out.print(v.toRawString()+'|');
						 //System.out.println();
						 
					 }
					 if(count == ConfigureVariables.whereArrayList.size()) {
						 ProjectionOperator.Projection(tableName,tuple);
					 }
					 
				} catch (SQLException e) {
					
					e.printStackTrace();
				}	
			
			}
			
			
		}
		
		else {
			ProjectionOperator.Projection(tableName, tuple);
		}
		
	}
	
	public static void NSelection(PrimitiveValue []otuple, PrimitiveValue []ituple, String tableName) {
		int count =0;
		JoinEvaluator eval = new JoinEvaluator(otuple, ituple, ConfigureVariables.dbMap);
		if(!ConfigureVariables.whereArrayList.isEmpty()) {
			for(Expression condition : ConfigureVariables.whereArrayList) {
				
				try {
				PrimitiveValue	result = eval.eval(condition);
				BooleanValue boolResult =  (BooleanValue)result;
				if(boolResult.getValue()) {
					count++;
				}
				if(count== ConfigureVariables.whereArrayList.size()) {
					ProjectionOperator.NProjection(tableName,otuple,ituple);
					//System.out.print(otuple[0]);
					//System.out.println(ituple[0]);
					
					
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
	}

}
