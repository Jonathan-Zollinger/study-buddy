package com.github.studybuddy;

import com.github.studybuddy.utils.DgraphUtils;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphProto;

import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        ResourceBundle properties = ResourceBundle.getBundle("config"); //put config.properties in resources

        DgraphClient dgraphClient = DgraphUtils.initiateClient(properties.getString("dGraphEndpoint"),
                properties.getString("bearerToken"));

        DgraphProto.Version version = dgraphClient.checkVersion();
        System.out.println(version);
    }
}