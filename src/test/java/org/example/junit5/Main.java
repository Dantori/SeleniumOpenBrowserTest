package org.example.junit5;

import com.codeborne.selenide.Selenide;

public class Main {
    public static void main(String[] args) {
        Selenide.open("https://openjdk.org/");
    }
}
