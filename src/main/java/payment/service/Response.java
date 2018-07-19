package payment.service;

public class Response {

    private String status;
    private String code;

    public Response(String status, String code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
