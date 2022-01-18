package com.example.springsecuritymodel.security.util;

import javax.print.DocFlavor;

public class RolesHierarchyBuilder {

    private StringBuilder stringBuilder = new StringBuilder();
    public RolesHierarchyBuilder append(String uplineRole,String downlinRole){
        stringBuilder.append(String.format("ROLE_%s > ROLE_%s\n",uplineRole,downlinRole));
        return this;
    }

    public String build(){
        return stringBuilder.toString();
    }
}
