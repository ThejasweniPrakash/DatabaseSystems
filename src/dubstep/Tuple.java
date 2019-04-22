package dubstep;
////@Author - Anunay Rao
import java.util.HashMap;
import java.util.List;

import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.PrimitiveValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

public class Tuple {
	
	public static PrimitiveValue[] getTuple(String line,HashMap<String, CreateTable> createTableMap,String tableName){
		
			if(line == null)
				return null;
			String[] cols = line.split("\\|");
			PrimitiveValue tuple[] = new PrimitiveValue[cols.length];
			
			List<ColumnDefinition> columnDefinitionList = createTableMap.get(tableName).getColumnDefinitions();
	        for (int i = 0; i < cols.length; i++) {
	            if(cols[i].equals("NULL")) {
	                tuple[i] = new NullValue();
	                continue;
	            }
	            String colDataType = columnDefinitionList.get(i).getColDataType().getDataType();
	            switch (colDataType) {
	            	case "string":
	            	case "varchar":
	            	case "char":
	            	case "STRING":
	                case "VARCHAR":
	                case "CHAR":
	                    tuple[i] = new StringValue(cols[i]);
	                    break;
	                case "INT":
	                case "int":
	                    tuple[i] = new LongValue(cols[i]);
	                    break;
	                case "decimal":
	                case "DECIMAL":
	                    tuple[i] = new DoubleValue(cols[i]);
	                    break;
	                case "date":
	                case "DATE":
	                    tuple[i] = new DateValue(cols[i]);
	                    break;
	                case "double":
	                case "DOUBLE":
	                    tuple[i] = new DoubleValue(cols[i]);
	                    break;
	                default:
	                    tuple[i] = null;
	            }
	        }
	        return tuple;
	    }
		
	}


