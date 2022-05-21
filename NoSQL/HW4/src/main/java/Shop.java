import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shop {
    private String name;
    private List<String> products;

    public Shop(String name, List<String> products) {
        this.name = name;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name = '" + name + '\'' +
                "\tproducts = " + products +
                "}\n";
    }
}
