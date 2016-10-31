package org.typelibrary.freeswitch.esl;

import java.nio.charset.Charset;

class EslConstants {

    static final Charset UTF8 = Charset.forName("UTF-8");
    
    static final String ARRAY_PREFIX = "ARRAY::";

    static final String EXECUTE_APP_NAME = "execute-app-name";

    static final String EXECUTE_APP_ARG = "execute-app-arg";

    static final String HEADER_BODY_NAME = "_body";

    static final String HEADER_JOB_UUID = "Job-UUID";

    static final String HEADER_CONTENT_TYPE = "Content-Type";

    static final String HEADER_CONTENT_LENGTH = "Content-Length";

    static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    
    static final String HEADER_REPLY_TEXT = "Reply-Text";

    static final String HEADER_SOCKET_MODE = "Socket-Mode";

    static final String HEADER_EVENT_NAME = "Event-Name";

    static final String HEADER_EVENT_SUBCLASS = "Event-Subclass";

    static final String HEADER_EVENT_PRIORITY = "Priority";
    
    static final String TYPE_AUTH_REQUEST = "auth/request";

    static final String TYPE_API_RESPONSE = "api/response";

    static final String TYPE_COMMAND_REPLY = "command/reply";

    static final String TYPE_TEXT_EVENT_PLAIN = "text/event-plain";

    static final String TYPE_TEXT_EVENT_XML = "text/event-xml";

    static final String TYPE_DISCONNECT_NOTICE = "text/disconnect-notice";

    static final String ERR_INVALID = "-ERR invalid";

    static final String RESPONSE_OK = "+OK";

}
