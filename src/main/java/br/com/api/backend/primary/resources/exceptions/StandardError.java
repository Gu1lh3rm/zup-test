package br.com.api.backend.primary.resources.exceptions;

import java.io.Serializable;

/** Class to generate a better and customized Json
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
public class StandardError implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    /** Constructor for simplify Creation of Object StandardError
     * @param timeStamp Long exact time exception
     * @param status Integer JSON response status
     * @param error String simple error
     * @param message String detail error
     * @param path String name of endpoint
     * @return new StandardError() StandardError
     */
    public StandardError(Long timeStamp, Integer status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
