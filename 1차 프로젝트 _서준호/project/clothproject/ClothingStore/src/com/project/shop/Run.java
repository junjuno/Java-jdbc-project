package com.project.shop;

import java.util.Scanner;

import com.project.shop.controller.MainController;

public class Run {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MainController.instance.sc = sc;
        MainController.instance.start();
    }
}
