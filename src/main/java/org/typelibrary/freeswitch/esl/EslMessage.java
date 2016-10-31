package org.typelibrary.freeswitch.esl;

public final class EslMessage {

    public static final EslMessage EMPTY = new EslMessage();

    private final EslHeaders headers;
    private final String body;

    private EslMessage() {
        this.headers = EslHeaders.EMPTY;
        this.body = "";
    }

    public EslMessage(EslHeaders headers) {
        this(headers, "");
    }

    public EslMessage(String body) {
        this(null, body);
    }

    public EslMessage(EslHeaders headers, String body) {
        this.body = (body != null) ? body : "";
        if (this.body.isEmpty()) {
            this.headers = (headers != null) ? headers : EslHeaders.EMPTY;
        } else {
            this.headers = (headers != null)
                    ? headers.overwrite(EslConstants.HEADER_CONTENT_LENGTH,
                            Integer.toString(this.body.getBytes(EslConstants.UTF8).length))
                    : new EslHeaders(EslConstants.HEADER_CONTENT_LENGTH,
                            Integer.toString(this.body.getBytes(EslConstants.UTF8).length));
        }
    }

    public EslHeaders getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String toString() {
        if (headers.getContentLength() > 0) {
            return headers.toString() + "\n" + body;
        } else {
            return headers.toString();
        }
    }

}
