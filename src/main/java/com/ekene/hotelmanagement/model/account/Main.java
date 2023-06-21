package com.ekene.hotelmanagement.model.account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        String name = "Dear, Ben";
        String name1 =  "Ben";
        int no = 9;

        log.info("Testing 111");
        log.debug("debug 111");
        log.error("error 111");
        String text = ("Your account has been debited ");
        String t2 = ("Do enjoy the services rendered and enjoy you stay!");

        String s = String.format(" %s \n %s %d \n \n \n %s", name, text, no, t2);


        String text1 = ("Dear " + name1 + "\nYour account has been debited "
                + no + ". \n\n\n\nDo enjoy the services rendered and enjoy you stay!");
        System.out.println(text1);
    }
}
