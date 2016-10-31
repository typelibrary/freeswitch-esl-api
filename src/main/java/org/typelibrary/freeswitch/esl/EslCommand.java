package org.typelibrary.freeswitch.esl;

public final class EslCommand {

    private final String command;
    private final EslMessage message;

    public EslCommand(String command, EslHeaders headers, String body) {
        this.message = new EslMessage(headers, body);
        if (command == null)
            throw new IllegalArgumentException("Command cannot be null");
        if (command.isEmpty())
            throw new IllegalArgumentException("Command cannot be empty");
        if (command.indexOf('\n') != -1)
            throw new IllegalArgumentException("Command cannot have linefeed character");
        this.command = command;
    }

    public EslCommand(String command, EslHeaders headers) {
        this(command, headers, null);
    }

    public EslCommand(String command) {
        this(command, null, null);
    }

    public String getCommand() {
        return command;
    }

    public EslHeaders getHeaders() {
        return message.getHeaders();
    }
    
    public String getBody() {
        return message.getBody();
    }
    
    public String toString() {
        return command + "\n" + message.toString();
    }

}
