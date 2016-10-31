package org.typelibrary.freeswitch.esl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * 
 * Names
 * <ul>
 * <li>Delimited by start of line and colon character ':' - therefore can contain 0 characters,
 * leading and/or trailing whitespace or even just whitespace. (esl.c/esl_recv_event)
 * </ul>
 *  
 *  Values
 *  <ul>
 *  <li>Leading spaces and tabs are stripped for "main" header (esl.c/esl_recv_event)
 *  <li>Leading spaces are stripped for "event" header (esl.c/esl_recv_event)
 *  <li>Trailing whitespace is not stripped (esl.c/esl_recv_event)
 *  </ul>
 */
public final class EslHeaderEntry {

    private final static int ARRAY_PREFIX_LEN = EslConstants.ARRAY_PREFIX.length();
    
    private final String name;
    private final String normalizedName;
    private final int hashCode;
    private final int normalizedHashCode;
    private final String value;
    private final List<String> values;

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
        value = stripLeadingSpacesAndTabs(value);
        if (value.startsWith(EslConstants.ARRAY_PREFIX)) {
            String[] array = value.substring(ARRAY_PREFIX_LEN).split("\\|\\:");
            this.values = Arrays.asList(array);
            this.value = null;
        } else {
            this.value = value;
            this.values = null;
        }
    }

    public EslHeaderEntry(String name, List<String> values) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        if (name.indexOf(':') != -1)
            throw new IllegalArgumentException("Name cannot have ':' character");
        if (values == null)
            throw new IllegalArgumentException("Value list cannot be null");
        this.name = name;
        this.normalizedName = name.toLowerCase(Locale.ROOT);
        this.hashCode = name.hashCode();
        this.normalizedHashCode = normalizedName.hashCode();
        this.values = Collections.unmodifiableList(new ArrayList<>(values));
        this.value = null;
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

    public List<String> getArray() {
        return values;
    }

    public boolean isValue() {
        return value != null;
    }

    public boolean isArray() {
        return values != null;
    }

    public String toString() {
        return name + ": " + value;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((normalizedName == null) ? 0 : normalizedName.hashCode());
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
        if (normalizedName == null) {
            if (other.normalizedName != null)
                return false;
        } else if (!normalizedName.equals(other.normalizedName))
            return false;
        return true;
    }

    private static String stripLeadingSpacesAndTabs(String str) {
        int pos = 0, max = str.length();
        while (pos < max && (str.charAt(pos) == ' ' || str.charAt(pos) == '\t')) {
            ++pos;
        }
        if (pos > 0) {
            return str.substring(pos);
        } else {
            return str;
        }
    }
}
