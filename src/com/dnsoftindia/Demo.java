package com.dnsoftindia;

/**
 * Created by Ganesha on 11/10/2016.
 */
public class Demo {

    private String idCode = "100";
    private String getPrivate() {
        return "How did you get this";
    }

    private String getOtherPrivate(int thisInt, String thatString) {
        return "How did you get here: "+thisInt+" and "+thatString;
    }

    public Demo(int i, String s) {
        System.out.println("You sent: "+i+" "+s);
    }

    public Demo(float newFloat) {
        System.out.println("You sent: "+newFloat);
    }

}
