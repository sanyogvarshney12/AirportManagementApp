package com.airport.events;

import java.io.IOException;

public interface IDataConsumer {
    public void readFileAndSplit() throws IOException, InterruptedException;
}
