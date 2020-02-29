package com.lynxted.example.tc.reuse

import spock.lang.Specification

class KafkaContainerIT extends Specification {

    def "kafka container should be started"() {
        expect:
            KafkaContainerWrapper.getContainer().getBootstrapServers()
    }
}
