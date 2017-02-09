package com.intercall.ThreadingSamples;

interface ISyncronous {
    String getThreadNameFast();

    //This method is supposed to run async because of the oneway keyword and the provided callback
    //oneway void getThreadNameSlow(IAsynchronousCallback callback);
    String getThreadNameBlocking();
    String getThreadNameUnblock();
}
