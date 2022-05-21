import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Aggregates.lookup;
import static java.util.Collections.singletonList;

public class MongoDB {
    private static MongoCollection<Document> shopCollection;
    private static MongoCollection<Document> productCollection;

    public MongoDB(){
        init();
    }

    private static void init(){

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("local");

        shopCollection = database.getCollection("shop");
        shopCollection.drop();

        productCollection = database.getCollection("product");
        productCollection.drop();
    }

    public void addShop(String shopName){
        Document object = new Document()
                .append("Name", shopName);
        shopCollection.insertOne(object);
    }

    public void addProduct(String productName, int productPrice){
        Document object = new Document()
                .append("Name", productName)
                .append("Price", productPrice);
        productCollection.insertOne(object);
    }

    public List<Shop> getShops(){
        List<Shop> shopList = new ArrayList<>();

        FindIterable<Document> shops = shopCollection.find();

        for (Document shop : shops){
            String name = shop.getString("Name");
            List<String> products = shop.getList("Products", String.class);
            shopList.add(new Shop(name, products));
        }

        return shopList;
    }

    public List<Product> getProducts(){
        List<Product> productList = new ArrayList<>();

        FindIterable<Document> products = productCollection.find();

        for (Document product : products){
            String name = product.getString("Name");
            int price = product.getInteger("Price");
            productList.add(new Product(name, price));
        }

        return productList;
    }

    public void exhibitTheGoods(String productName, String shopName){
        Document uShop = shopCollection.find(new Document("Name", shopName)).first();
        if (uShop == null){
            System.out.println("У нас нет магазина \'" + shopName + '\'');
            return;
        }

        Document dProduct = productCollection.find(new Document("Name", productName)).first();
        if (dProduct == null){
            System.out.println("У нас нет товара \'" + productName + '\'');
            return;
        }

        List<String> products = uShop.getList("Products", String.class);
        if (products == null) products = new ArrayList<>();
        products.add(productName);

//        products.forEach(System.out::println);
//        System.out.println('\n');
        Bson updateValue = new Document("Products", products);
        Bson updateOperation = new Document("$set", updateValue);
        shopCollection.updateOne(uShop, updateOperation);
//        System.out.println(new Shop(Objects.requireNonNull(shopCollection.find(new Document("Name", shopName)).first()).getString("Name"),
//                Objects.requireNonNull(shopCollection.find(new Document("Name", shopName)).first()).getList("Products", String.class)));
    }

    public void getStats(){
        Bson pipeline = lookup("product", "Name", "Products", "ProductAtShop");
        List<Document> shopJoined = shopCollection.aggregate(singletonList(pipeline)).into(new ArrayList<>());
        System.out.println(shopJoined.size());
        for (Document d : shopJoined) {
            System.out.println(d);
//            List<Product> current = d.getList("ProductAtShop", Product.class);
//            current.forEach(System.out::println);
        }
    }
}
