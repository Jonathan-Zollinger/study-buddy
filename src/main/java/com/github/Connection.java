package com.github;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Connection {

    public static void main(String[] args) {
        String connectionString = String.format("mongodb+srv://%s:%s@studybuddy.s8bclpe.mongodb.net/" +
                        "?retryWrites=true&w=majority",
                System.getenv("user"), System.getenv("password"));
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
        }
    }
}