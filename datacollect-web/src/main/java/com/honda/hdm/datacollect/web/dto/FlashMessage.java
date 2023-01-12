package com.honda.hdm.datacollect.web.dto;

public class FlashMessage {
    
    public static enum FlashMessageType{
        INFO, WARN, ERROR
    }
    
    public String text;
    public FlashMessageType messageType;
    
    public FlashMessage(){}
    
    public FlashMessage(String message, FlashMessageType messageType){
        this.text = message;
        this.messageType = messageType;
    }    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FlashMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(FlashMessageType messageType) {
        this.messageType = messageType;
    }    
    
}
