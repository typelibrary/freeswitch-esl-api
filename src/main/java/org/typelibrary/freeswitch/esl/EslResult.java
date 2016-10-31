package org.typelibrary.freeswitch.esl;

public final class EslResult {

    private final boolean success;
    private final EslMessage message;

    public EslResult(boolean success, EslMessage message) {
        this.success = success;
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public EslMessage getMessage() {
        return message;
    }
    
}
