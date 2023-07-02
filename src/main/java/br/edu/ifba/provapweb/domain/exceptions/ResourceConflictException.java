package br.edu.ifba.provapweb.domain.exceptions;

@SuppressWarnings("serial")
public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message) {
        super(message);
    }
}
