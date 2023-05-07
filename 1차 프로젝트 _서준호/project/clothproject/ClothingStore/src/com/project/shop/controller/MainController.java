package com.project.shop.controller;

import java.util.Scanner;

import com.project.shop.view.MainView;

public class MainController {

    private MainView view = new MainView();
    public static MainController instance = new MainController();
    public Scanner sc;

    public void start() {
        view.main();
    }

    public void end() {
        System.exit(0);
    }
}
