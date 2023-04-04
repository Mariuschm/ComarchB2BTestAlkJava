package pl.alk.model;
import com.opencsv.bean.CsvBindByPosition;

public class ItemModel {
    @CsvBindByPosition(position = 0)
    private String categoryName;
    @CsvBindByPosition(position =1)
    private String itemCode;
    @CsvBindByPosition(position = 2)
    private float quantity;
}
