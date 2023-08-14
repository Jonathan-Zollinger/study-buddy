package com.github.studybuddy.utils;

import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

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
}
