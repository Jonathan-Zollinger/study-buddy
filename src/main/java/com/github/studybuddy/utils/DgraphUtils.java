package com.github.studybuddy.utils;

import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

public class DgraphUtils {

    ManagedChannel channel1 = ManagedChannelBuilder
            .forAddress("localhost", 9080)
            .usePlaintext().build();
    DgraphGrpc.DgraphStub stub1 = DgraphGrpc.newStub(channel1);

    ManagedChannel channel2 = ManagedChannelBuilder
            .forAddress("localhost", 9082)
            .usePlaintext().build();
    DgraphGrpc.DgraphStub stub2 = DgraphGrpc.newStub(channel2);

    ManagedChannel channel3 = ManagedChannelBuilder
            .forAddress("localhost", 9083)
            .usePlaintext().build();
    DgraphGrpc.DgraphStub stub3 = DgraphGrpc.newStub(channel3);

    DgraphClient dgraphClient = new DgraphClient(stub1, stub2, stub3);

    public static DgraphClient initiateClient(String endpoint, String bearerToken) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(endpoint).useTransportSecurity().build();

        // If you have an API key, you should attach it to all your requests.
        Metadata metadata = new Metadata();
        Metadata.Key<String> apiKeyHeader = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
        metadata.put(apiKeyHeader, "Bearer <YOUR_API_KEY>"); // Replace <YOUR_API_KEY> with your actual API key

        DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(channel);
        stub = MetadataUtils.attachHeaders(stub, metadata);

        return new DgraphClient(stub);
    }
}
