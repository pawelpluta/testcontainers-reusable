package com.lynxted.example.tc.reuse

import com.github.dockerjava.api.command.InspectContainerResponse
import org.testcontainers.containers.KafkaContainer

class KafkaContainerWrapper extends KafkaContainer {

    private static KafkaContainerWrapper CONTAINER

    private KafkaContainerWrapper(String confluentPlatformVersion) {
        super(confluentPlatformVersion)
    }

    static KafkaContainer getContainer() {
        if (CONTAINER == null) {
            CONTAINER = new KafkaContainerWrapper("5.3.0")
                    .withReuse(true)
                    .withNetwork(null)
                    .withLabel("com.example.my.application.name", "testcontainersReuseSample")
            CONTAINER.start()
        }
        CONTAINER
    }

    @Override
    protected void containerIsStarted(InspectContainerResponse containerInfo, boolean reused) {
        if (!reused) {
            createKafkaTopics()
        }
    }

    void createKafkaTopics() {
        System.out.println("Creating kafka topics...")
    }

    @Override
    void close() {
        super.close()
    }
}