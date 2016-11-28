package org.typelibrary.freeswitch.esl;

import java.util.Locale;

/**
 * An individual key - value pair within an ESL header.
 * 
 * Names
 * <ul>
 * <li>Delimited by start of line and colon character ':' - therefore can contain 0 characters,
 * leading and/or trailing whitespace or even just whitespace. (esl.c/esl_recv_event)
 * </ul>
 *  
 * Values
 * <ul>
 * <li>Leading spaces and tabs are stripped for "main" header (esl.c/esl_recv_event)
 * <li>Leading spaces are stripped for "event" header (esl.c/esl_recv_event)
 * <li>Trailing whitespace is not stripped (esl.c/esl_recv_event)
 * </ul>
 */
public final class EslHeaderEntry {

    private final String name;
    private final String normalizedName;
    private final int hashCode;
    private final int normalizedHashCode;
    private final String value;

    public EslHeaderEntry(String name, String value) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        if (name.indexOf(':') != -1)
            throw new IllegalArgumentException("Name cannot have ':' character");
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");
        this.name = name;
        this.normalizedName = name.toLowerCase(Locale.ROOT);
        this.hashCode = name.hashCode();
        this.normalizedHashCode = normalizedName.hashCode();
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public int getHashCode() {
        return hashCode;
    }

    public int getNormalizedHashCode() {
        return normalizedHashCode;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return name + ": " + value;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + normalizedName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EslHeaderEntry other = (EslHeaderEntry) obj;
        return normalizedName.equals(other.normalizedName);
    }

}
