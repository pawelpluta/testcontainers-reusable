package com.lynxted.example.tc.reuse

import org.apache.commons.lang3.time.StopWatch;

trait ExecutionTimeMeasurementSupport {
    StopWatch watch = new StopWatch()

    void startTimeMeasure() {
        watch.reset()
        watch.start()
    }

    void stopTimeMeasure() {
        watch.stop()
        println(String.format("Execution time: %d ms", watch.getTime()))
    }

}
