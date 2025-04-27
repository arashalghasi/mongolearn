package org.arash;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoQueryHelper {


    public static void findAndPrintAll(MongoCollection<Document> collection, Bson filter) {
        collection.find(filter)
                .forEach(doc -> System.out.println(doc.toJson()));
    }



    public static MongoCursor<Document> findWithIterator(MongoCollection<Document> collection, Bson filter) {
        try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
            return cursor;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * Find the first document matching the filter.
     */
    public static Document findFirst(MongoCollection<Document> collection, Bson filter) {
        return collection.find(filter).first();
    }

    /**
     * Find all documents matching the filter and return them as a List.
     */
    public static List<Document> findAllAsList(MongoCollection<Document> collection, Bson filter) {
        return collection.find(filter).into(new ArrayList<>());
    }
}
