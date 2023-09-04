package com.github.studybuddy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dgraph.DgraphClient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws IOException {
        ResourceBundle properties = ResourceBundle.getBundle("config"); //put config.properties in resources

        DgraphClient dgraphClient = getDgraphClient(properties.getString("dGraphEndpoint"),
                properties.getString("bearerToken"));
        System.out.println(importStandardWorks());
    }

    static DgraphClient getDgraphClient(String endpoint, String bearerToken) throws MalformedURLException {
        return new DgraphClient(DgraphClient.clientStubFromSlashEndpoint(endpoint, bearerToken));
    }

    static List<Volume> importStandardWorks() throws IOException {
        return new ObjectMapper().readValue(new File("src/main/resources/lds-scriptures-2020.12.08/json/kjv-scriptures-json.txt".replace("/", File.separator)), new TypeReference<List<Volume>>(){});
    }

    @Data
    @NoArgsConstructor
        private class Volume {
        private String id;
        private String title;
        private List<Book> books;
    }

    @Data
    @NoArgsConstructor
        private class Book {
        private String id;
        private String title;
        private String shortTitle;
        private Volume volume;
        private List<Chapter> chapters;
    }

    @Data
    @NoArgsConstructor
        private class Chapter {
        private String id;
        private int number;
        private Book book;
        private List<Verse> verses;
    }

    @Data
    @NoArgsConstructor
        private class Verse {
        private String id;
        private int number;
        private String title;
        private String shortTitle;
        private String text;
        private Chapter chapter;
    }

}