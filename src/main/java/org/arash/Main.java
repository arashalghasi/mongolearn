package org.arash;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        MongoClient mongoClient = MongoClientCustom.getMongoClient();
//        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
//        MongoDatabase database = MongoClientCustom.createDatabase("sample-training");
//        MongoCollection<Document> collection = MongoClientCustom.createCollection(database,"inspections");

        MongoDatabase databaseBankAccount = MongoClientCustom.createDatabase("bankAccounts");
        MongoCollection<Document> accountsCollection = MongoClientCustom.createCollection(databaseBankAccount,"accounts");

        Document acc1 = new Document("_id" , new ObjectId())
                .append("accountHolder", "arash ghasemi")
                .append("accountType", "saving")
                .append("balance", 3999)
                .append("accountStatus", "active");

//        Document inspection = new Document("_id" , new ObjectId())
//                .append("business_id", "10021-2015-ENFO")
//                .append("result", "no violation");
//
//        Document inspectionTwo = new Document("_id" , new ObjectId())
//                .append("business_id", "10021-2015-ENFO")
//                .append("result", "no violation");

//        List<Document> docs = Arrays.asList(inspection,inspectionTwo);

        accountsCollection.insertOne(acc1);
        ObjectId id = new ObjectId();
        id = Objects.requireNonNull(accountsCollection.find(Filters.eq("accountHolder", "arash ghasemi")).first()).getObjectId(0);
        System.out.println(Objects.requireNonNull(accountsCollection.find(Filters.eq("account_id", id)).first()).toJson());
        //collection.insertOne(inspection);
        // collection.insertMany(docs);
        // collection.find(Filters.and(Filters.gte("this", 10),Filters.lte("this", 20))).forEach(e -> System.out.println(e.toJson()));
        // if we want to process each record we can use .iterator()
//        try(MongoCursor<Document> cursor = collection.find(Filters.and(Filters.gte("this", 10),
//                Filters.lte("this", 20))).iterator())
//        {
//            while (cursor.hasNext())
//            {
//                System.out.println(cursor.next().toJson());
//            }
//        }

        Bson query = Filters.eq("account_id" , id);
        Bson update = Updates.combine(Updates.set("accountStatus","active"), Updates.inc("balance", 100));
        UpdateResult upResult = accountsCollection.updateOne(query, update);
        System.out.println(upResult);
        System.out.println(Objects.requireNonNull(accountsCollection.find(Filters.eq("account_id", id)).first()).toJson());


        // to delete a document in a collection
        accountsCollection.deleteMany(query);
        accountsCollection.deleteOne(query);

        // deleteMany without any query will delete all the documents


        TransactionBody  tnxBody  = new TransactionBody<String>() {
            @Override
            public String execute() {

                MongoCollection<Document> bankingCollection = mongoClient.getDatabase("bank").getCollection("accounts");

                return "fund transfer";
            }

        };

        // if we want to find a single record like find by Id we can use the .first()
        // collection.find(Filters.and(Filters.gte("this", 10),Filters.lte("this", 20))).first();
        mongoClient.close();
    }

    private static void matchStage(MongoCollection<Document> accounts) {
        Bson matchStage = Aggregates.match(Filters.eq("account_id","4762847"));
        accounts.aggregate(List.of(matchStage)).forEach(document -> System.out.println(document.toJson()));
    }
}