package br.edu.ifba.provapweb.domain.exceptions;

@SuppressWarnings("serial")
public class ResourceBadRequestException extends RuntimeException {

    public ResourceBadRequestException(String message) {
        super(message);
    }
}

