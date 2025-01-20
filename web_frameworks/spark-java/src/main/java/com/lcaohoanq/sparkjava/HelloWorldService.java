package com.lcaohoanq.sparkjava;

import static spark.Spark.*;

public class HelloWorldService {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World!");

        get("/hello/:name", (req, res) -> {
            return "Hello: " + req.params(":name");
        });
    }
}
