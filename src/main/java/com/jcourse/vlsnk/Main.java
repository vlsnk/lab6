package com.jcourse.vlsnk;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        GuestBookController controller = new GuestBookControllerImpl();
        while (true) {
            String input = in.nextLine();
            String[] a = input.split(" ", 2);
            if (a[0].equalsIgnoreCase(Command.ADD.toString())){
                if (a.length > 1) controller.addRecord(a[1]);
            } else if (a[0].equalsIgnoreCase(Command.LIST.toString())){
                List<Record> recordList = controller.getRecords();
                for (Record r : recordList) {
                    System.out.println(r);
                }
            } else {
                System.out.println("Command not found");
                break;
            }
        }


    }
}
