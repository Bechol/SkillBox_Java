package JsonParser.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Line {

    private String number;
    private String name;
    private String color;
    private int stationsAmount;
}
