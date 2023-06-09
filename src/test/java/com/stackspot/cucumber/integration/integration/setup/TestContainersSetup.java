package com.stackspot.cucumber.integration.integration.setup;

import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

public class TestContainersSetup {

    private static DockerComposeContainer testContainers;

    public static void initTestContainers(String composePath){
        testContainers = new DockerComposeContainer(new File(composePath)).withLocalCompose(true);
        testContainers.start();
    }
}
