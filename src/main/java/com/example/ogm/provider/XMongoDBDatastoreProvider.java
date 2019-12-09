package com.example.ogm.provider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.hibernate.ogm.datastore.mongodb.configuration.impl.MongoDBConfiguration;
import org.hibernate.ogm.datastore.mongodb.impl.MongoDBDatastoreProvider;

public class XMongoDBDatastoreProvider extends MongoDBDatastoreProvider {
    @Override
    protected MongoClient createMongoClient(MongoDBConfiguration config) {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://mongouser:mongouser123@mongo-test-instance-musz7.mongodb.net/test?retryWrites=true&w=majority");

        return new MongoClient(uri);
    }
}
