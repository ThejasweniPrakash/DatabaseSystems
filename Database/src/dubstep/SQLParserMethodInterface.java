package dubstep;

import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Union;

public interface SQLParserMethodInterface {
	
	void createTable(CreateTable createTable);
	void unionStatement(Union union);
	//void plainStatement(PlainSelect plain);
}
