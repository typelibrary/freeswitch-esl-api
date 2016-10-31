package org.typelibrary.freeswitch.esl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EslHeaders {

    public static final EslHeaders EMPTY = new EslHeaders();
    
    private final List<EslHeaderEntry> headers;

    private EslHeaders() {
        this.headers = Collections.emptyList();
    }
    
    public EslHeaders(String name, String value) {
        this.headers = Arrays.asList(new EslHeaderEntry(name, value));
    }

    public EslHeaders(EslHeaderEntry header) {
        this.headers = Arrays.asList(header);
    }

    public EslHeaders(List<EslHeaderEntry> headers) {
        this.headers = filterHeaders(headers);
    }

    EslHeaders(List<EslHeaderEntry> headers, boolean filter) {
        if (filter) {
            this.headers = filterHeaders(headers);
        } else {
            this.headers = Collections.unmodifiableList(headers);
        }
    }

    public String getValue(String name) {
        final String normalizedName = name.toLowerCase(Locale.ROOT);
        final int normalizedHashCode = normalizedName.hashCode();
        Optional<EslHeaderEntry> header = headers.stream().filter(h -> h.getNormalizedHashCode() == normalizedHashCode)
                .filter(h -> h.getNormalizedName().equals(normalizedName)).findFirst();
        if (header.isPresent()) {
            return header.get().getValue();
        } else {
            return null;
        }
    }

    public List<String> getArray(String name) {
        final String normalizedName = name.toLowerCase(Locale.ROOT);
        final int normalizedHashCode = normalizedName.hashCode();
        Optional<EslHeaderEntry> header = headers.stream().filter(h -> h.getNormalizedHashCode() == normalizedHashCode)
                .filter(h -> h.getNormalizedName().equals(normalizedName)).findFirst();
        if (header.isPresent()) {
            return header.get().getArray();
        } else {
            return null;
        }
    }

    public List<String> getNames() {
        return headers.stream().map(h -> h.getName()).collect(Collectors.toList());
    }
    
    public boolean contains(String name) {
        final String normalizedName = name.toLowerCase(Locale.ROOT);
        final int normalizedHashCode = normalizedName.hashCode();
        return headers.stream().anyMatch(h -> h.getNormalizedHashCode() == normalizedHashCode && h.getNormalizedName().equals(normalizedName));
    }
    
    public String getContentType() {
        return getValue(EslConstants.HEADER_CONTENT_TYPE);
    }
    
    public int getContentLength() {
        String length = getValue(EslConstants.HEADER_CONTENT_LENGTH);
        if (length != null) {
            try {
                return Integer.parseInt(length);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }
    
    public boolean isOkCommandReply() {
        if (!EslConstants.TYPE_COMMAND_REPLY.equals(getValue(EslConstants.HEADER_CONTENT_TYPE))) {
            return false;
        }
        String reply = getValue(EslConstants.HEADER_REPLY_TEXT);
        return (reply != null && reply.startsWith(EslConstants.RESPONSE_OK));
    }
    
    public int size() {
        return headers.size();
    }
    
    EslHeaders overwrite(String name, String value) {
        String normalizedName = name.toLowerCase(Locale.ROOT);
        List<EslHeaderEntry> newHeaders = new LinkedList<>();
        boolean found = false;
        for (EslHeaderEntry header : headers) {
            if (!found && normalizedName.equals(header.getNormalizedName())) {
                newHeaders.add(new EslHeaderEntry(name, value));
            } else {
                newHeaders.add(header);
            }
        }
        if (!found) {
            newHeaders.add(new EslHeaderEntry(name, value));
        }
        return new EslHeaders(newHeaders, false);
    }
    
    private List<EslHeaderEntry> filterHeaders(List<EslHeaderEntry> headers) {
        Set<String> filter = new HashSet<>();
        
        List<EslHeaderEntry> result = new LinkedList<>();
        for (EslHeaderEntry header : headers) {
            if (!filter.contains(header.getNormalizedName())) {
                filter.add(header.getNormalizedName());
                result.add(header);
            }
        }
        
        return Collections.unmodifiableList(result);
    }

    public List<EslHeaderEntry> asList() {
        return headers;
    }
    
    public String toString() {
        String str = "";
        for (EslHeaderEntry header : headers) {
            str += header.toString() + "\n";
        }
        return str;
    }
}
