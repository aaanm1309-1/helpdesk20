package br.com.adrianomenezes.models.exceptions;

public class RefreshTokenExpired extends RuntimeException{

    public RefreshTokenExpired(String message) {
        super(message);
    }

}
