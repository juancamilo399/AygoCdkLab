package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

import java.util.Arrays;

public class AygoCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new AygoCdkStack(app, "AygoCdkStack", StackProps.builder()
                .env(Environment.builder()
                        .account("900101194060")
                        .region("us-east-1")
                        .build())
                .build());
        app.synth();
    }
}

