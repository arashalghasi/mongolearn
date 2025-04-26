package org.arash;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
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

        collection.insertOne(inspection);
        mongoClient.close();
    }
}