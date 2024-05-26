package com.feeham.concurrency;

public class G_SharedResourceHttp__Note {
    // Use JMeter or some tool to make thousands requests to test.
    // Hit both /inc and /dec api concurrently.
    // In the middle of the JMeter hits execution or after those keep hitting /val from postman
    // If G_SharedResourceHttp_DataSource is used in G_SharedResourceHttp_Controller, val response looks like: 1 | 6908 | 6906 | 2
    // But in case of G_SharedResourceHttp_DataSourceSync val response looks like: -32 | 7968 | 8000 | -32
    // First one is the value of 'val'. Then number of increments, then number of decrements, then increment - decrement
    // first value and last value not same!? Meaning that, the data race is happening here too! By using sync, it is resolved.
}
