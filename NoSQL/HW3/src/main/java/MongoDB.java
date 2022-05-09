import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.List;
import java.util.Objects;

public class MongoDB {
    private final List<Student> students;
    private static MongoCollection<Document> collection;

    public MongoDB(List<Student> students) {
        this.students = students;
        init();
        createDB();
    }

    private static void init(){
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");

        collection = database.getCollection("students");

        collection.drop();
    }

    private void createDB(){
        students.forEach(s ->{
            Document object = new Document()
                    .append("Name", s.getName())
                    .append("Age", s.getAge())
                    .append("Courses", s.getCourses());
            collection.insertOne(object);
        });
    }

    public long getCount(){
        return collection.countDocuments();
    }

    public long getCountWhereAge(){
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("Age", new BasicDBObject("$gt", 40));
        FindIterable<Document> cursor = collection.find(gtQuery);
        int count = 0;
        for(Document ignored : cursor){
            count++;
        }
        return count;
    }

    public String getNameYoungStudent(){
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("Age", new BasicDBObject("$lt", 100));
        FindIterable<Document> cursor = collection.find(gtQuery).sort(new BasicDBObject().append("Age", 1)).limit(1);

        return Objects.requireNonNull(cursor.first()).getString("Name");
    }

    public List<String> getCoursesOldestStudent(){
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("Age", new BasicDBObject("$lt", 100));
        FindIterable<Document> cursor = collection.find(gtQuery).sort(new BasicDBObject().append("Age", -1)).limit(1);

        return Objects.requireNonNull(cursor.first()).getList("Courses", String.class);
    }
}

