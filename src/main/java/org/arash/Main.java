package org.arash;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Main {
    public static void main(String[] args) {

        System.out.println( "Connecting to mongodb" );
        ConnectionString connectionString = new ConnectionString("mongodb+srv://arash:arash@firstcluster.fjeectn.mongodb.net/?retryWrites=true&w=majority&appName=FirstCluster");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
    }
}