package com.projects.bookmyshow.dtos;

public class Response {

    private ResponseStatus responseStatus;
    private String errorMsg;

    public Response(ResponseStatus responseStatus, String errorMsg) {
        this.responseStatus = responseStatus;
        this.errorMsg = errorMsg;
    }

    public static Response getFailureResponse(String errorMsg) {
        return new Response(ResponseStatus.FAILURE, errorMsg);
    }

    public static Response getSuccessResponse() {
        return new Response(ResponseStatus.SUCCESS, null);
    }

}
