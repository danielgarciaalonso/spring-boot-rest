package formacion.cmc.springbootrest.exception;

public class ErrorResponse {

    private Integer httpCode;

    private String httpMessage;

    private String moreInformation;

    public ErrorResponse(Integer httpCode, String httpMessage, String moreInformation) {
        super();
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
        this.moreInformation = moreInformation;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public void setHttpMessage(String httpMessage) {
        this.httpMessage = httpMessage;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }
    
}
