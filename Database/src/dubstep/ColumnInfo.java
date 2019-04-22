package dubstep;


public class ColumnInfo {
	int colNo;
    String colDataType;



    public ColumnInfo(int colNo, String colDataType) {
        this.colNo = colNo;
        this.colDataType = colDataType;
    }

    public int getColNo() {
        return colNo;
    }

    public void setColNo(int colNo) {
        this.colNo = colNo;
    }
}
