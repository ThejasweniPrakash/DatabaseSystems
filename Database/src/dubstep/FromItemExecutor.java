package dubstep;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;

public class FromItemExecutor implements FromItemVisitor {
	
	HashMap<String, CreateTable> createTableMap;
	ArrayList<Column> tableList;
	HashMap<String,String> alias;
	HashMap<String, Operator> operatorMap;
	public Operator source = null;
	
	public FromItemExecutor(HashMap<String, CreateTable> createTableMap) {
		this.createTableMap = createTableMap;
		tableList = new ArrayList<>();
		alias = new HashMap<>();
		//operatorMap = new HashMap<>();
		
		
	}

	@Override
	public void visit(Table table) {
		// TODO Auto-generated method stub
		CreateTable ct = createTableMap.get(table.getName().toLowerCase());
        if(table.getAlias() != null) {
            alias.put(table.getAlias().toLowerCase(), table.getName().toLowerCase());
        }
        List cols = ct.getColumnDefinitions();

        for(int i = 0; i < cols.size(); i++) {
            ColumnDefinition col = (ColumnDefinition)cols.get(i);
            tableList.add(new Column(table, col.getColumnName().toLowerCase()));
        }
        source = new ScanOperator(
                new File(table.getName().toLowerCase() + ".csv"), ct);
        operatorMap.put(table.getName().toLowerCase(), source);	
		
	}

	@Override
	public void visit(SubSelect arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SubJoin arg0) {
		// TODO Auto-generated method stub
		
	}

}
