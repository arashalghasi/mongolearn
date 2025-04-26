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

public class MongoClientCustom {
    private static MongoClient mongoClient;

    public static synchronized MongoClient getMongoClient()
    {
        if (mongoClient == null)
        {
            System.out.println( "Creating a mongo client singleton" );
            ConnectionString connectionString = new ConnectionString("mongodb+srv://arash:arash@firstcluster.fjeectn.mongodb.net/" +
                    "?retryWrites=true&w=majority&appName=FirstCluster");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .serverApi(
                            ServerApi.builder()
                                    .version(ServerApiVersion.V1)
                                    .build()
                    )
                    .build();
            mongoClient = MongoClients.create(settings);
        }
        return mongoClient;
    }

    public static synchronized MongoDatabase createDatabase (String databaseName) {
        if (mongoClient == null)
        {
            getMongoClient();
        }
        return mongoClient.getDatabase(databaseName);
    }

    public static synchronized MongoCollection<Document> createCollection ( MongoDatabase database , String collectionName) {
        return database.getCollection(collectionName);
    }
}
