package com.airports.concurrency.event;

import java.io.IOException;

public interface IDataConsumer {
    public void readFileAndSplit() throws IOException, InterruptedException;
}
