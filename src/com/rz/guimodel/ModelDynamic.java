/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guimodel;

import java.util.HashMap;

/**
 *
 * @author Rz Rasel
 */
public class ModelDynamic {

    private String title;
    private String description;
    private HashMap<String, Object> mapItems = new HashMap<String, Object>();

    public ModelDynamic() {
        //
    }

    public ModelDynamic(String argTitle, String argDescription) {
        this.title = argTitle;
        this.description = argDescription;
        //mapItems.put(this.title, this.description);
    }

    public ModelDynamic(String argTitle, String argDescription, HashMap<String, Object> argMapItems) {
        this.title = argTitle;
        this.description = argDescription;
        this.mapItems = argMapItems;
    }

    public ModelDynamic(HashMap<String, Object> argMapItems) {
        this.mapItems = argMapItems;
    }

    public void setTitle(String argTitle) {
        this.title = argTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String argDescription) {
        this.description = argDescription;
    }

    public HashMap<String, Object> getMapItems() {
        return this.mapItems;
    }

    public void setMapItems(HashMap<String, Object> argMapItems) {
        this.mapItems = argMapItems;
    }

    private HashMap<String, String> setDraftsMessageItems(String argId, String argSubject, String argBody, String argSender, String argReceiverType, String argReceiver) {
        HashMap<String, String> retDraftsMsgItems = new HashMap<String, String>();
        //retDraftsMsgItems.get(0).get("subject");
        retDraftsMsgItems.put("tdm_id", argId);
        retDraftsMsgItems.put("tdm_subject", argSubject);
        retDraftsMsgItems.put("tdm_body", argBody);
        retDraftsMsgItems.put("tdm_sender", argSender);
        retDraftsMsgItems.put("tdm_receiver_type", argReceiverType);
        retDraftsMsgItems.put("tdm_receiver", argReceiver);
        return retDraftsMsgItems;
        /*
        for (String key : retDraftsMsgItems.keySet()) {
            System.out.println(key);
        }
        System.out.println(team1.keySet().toArray()[0]);
        Iterator<Map.Entry<String,String>> itr=  _map.entrySet().iterator();
        //please check
        while(itr.hasNext())
        {
            System.out.println("key of : "+itr.next().getKey()+" value of      Map"+itr.next().getValue());
        }
        for (Map.Entry<Integer, String> entry : hm.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
        }
         */
    }
}
