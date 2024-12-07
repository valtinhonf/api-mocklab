package com.mocklab.api.shared.exceptions;

/**
 * Exception thats excute when the user isn't mock's owner
 * */
public class MockIsThisUser extends RuntimeException {

    public MockIsThisUser(String message){
        super(message);
    }

    public MockIsThisUser(){
        super();
    }


}
