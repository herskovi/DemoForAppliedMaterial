package com.amat.utils;

import com.amat.model.ContactPerson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collection;

public class SMSAndVoiceUtils
{

    public static Collection<ContactPerson> readPhoneList() {

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ContactPerson>>(){}.getType();

        Collection<ContactPerson> contactPersonList = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/phone_list.json"));
            contactPersonList = gson.fromJson(bufferedReader, collectionType);

        } catch (FileNotFoundException e) {

        }
        return contactPersonList;

    }
}
