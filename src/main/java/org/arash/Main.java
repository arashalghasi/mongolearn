package org.arash;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println( "Connecting to mongodb via singleton" );
        MongoClient mongoClient = MongoClientCustom.getMongoClient();
        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
        MongoDatabase database = MongoClientCustom.createDatabase("sample-training");
        MongoCollection<Document> collection = MongoClientCustom.createCollection(database,"inspections");

        Document inspection = new Document("_id" , new ObjectId())
                .append("business_id", "10021-2015-ENFO")
                .append("result", "no violation");

        Document inspectionTwo = new Document("_id" , new ObjectId())
                .append("business_id", "10021-2015-ENFO")
                .append("result", "no violation");

        List<Document> docs = Arrays.asList(inspection,inspectionTwo);

        //collection.insertOne(inspection);
        // collection.insertMany(docs);
        collection.find(Filters.and(Filters.gte("this", 10),Filters.lte("this", 20))).forEach(e -> System.out.println(e.toJson()));
        // if we want to process each record we can use .iterator()
        try(MongoCursor<Document> cursor = collection.find(Filters.and(Filters.gte("this", 10),
                Filters.lte("this", 20))).iterator())
        {
            while (cursor.hasNext())
            {
                System.out.println(cursor.next().toJson());
            }
        }

        // if we want to find a single record like find by Id we can use the .first()
        collection.find(Filters.and(Filters.gte("this", 10),Filters.lte("this", 20))).first();
        mongoClient.close();
    }
}