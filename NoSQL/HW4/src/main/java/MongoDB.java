import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mongodb.client.model.Aggregates.lookup;
import static java.util.Collections.singletonList;

public class MongoDB {
    private static MongoCollection<Document> shopCollection;
    private static MongoCollection<Document> productCollection;
    private static MongoDatabase database;

    public MongoDB(){
        init();
    }

    private static void init(){

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        database = mongoClient.getDatabase("local");

        shopCollection = database.getCollection("shop");
        shopCollection.drop();

        productCollection = database.getCollection("product");
        productCollection.drop();
    }

    public void addShop(String shopName){
        Document object = new Document()
                .append("Name", shopName)
                .append("Products", new ArrayList<String>());
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
        BasicDBObject oShop = new BasicDBObject().append("Name", shopName);
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

        BasicDBObject newDocShop = new BasicDBObject();
        newDocShop.append("$set", new BasicDBObject().append("Name", shopName)
                .append("Products", products));

        shopCollection.updateOne(oShop, newDocShop);
    }

    public void getStats(){
        Bson pipeline = lookup("product", "Products", "Name", "ProductAtShop");
        List<Document> shopJoined = shopCollection.aggregate(singletonList(pipeline)).into(new ArrayList<>());
//        MongoCollection<Document> productAtShop = getCollection(shopJoined);
        for (Document d : shopJoined) {
            System.out.println(d.get("Name"));
            List<Document> currentList = d.getList("ProductAtShop", Document.class);
            System.out.println("\tОбщее количество наименований товаров:" + currentList.size());
            List<Product> productList = new ArrayList<>();
            currentList.forEach(i -> {
                productList.add(new Product(i.getString("Name"), i.getInteger("Price")));
            });
            System.out.println("\tСредняя цена товаров: " + (double) productList.stream().mapToInt(Product::getPrice).sum() / productList.size());
            System.out.println("\tСамый дорогой товар: " + productList.stream().mapToInt(Product::getPrice).max().getAsInt());
            System.out.println("\tСамый дешевый товар: " + productList.stream().mapToInt(Product::getPrice).min().getAsInt());
            System.out.println("\tКоличество товаров дешевле 100 рублей: " + getCountWherePriseLess(productList, 100));
        }
    }

    private int getCountWherePriseLess(List<Product> products, int price){
        AtomicInteger count = new AtomicInteger();
        products.forEach(p -> {
            if (p.getPrice() < price) count.getAndIncrement();
        });
        return count.get();
    }

//    private MongoCollection<Document> getCollection(List<Document> documents){
//        MongoCollection<Document> productAtShop = database.getCollection("ProductAtShop");
//        productAtShop.drop();
//        for (Document d : documents){
//            List<Document> docProducts = d.getList("ProductAtShop", Document.class);
//            Document object = new Document()
//                    .append("Name", d.getString("Name"))
//                    .append("Products", getProductsList(docProducts));
//            productAtShop.insertOne(object);
//        }
//        return productAtShop;
//    }
//
//    private List<Product> getProductsList(List<Document> documents){
//        List<Product> result = new ArrayList<>();
//        documents.forEach(d -> {
//            result.add(new Product(d.getString("Name"), d.getInteger("Price")));
//        });
//        return result;
//    }
}
