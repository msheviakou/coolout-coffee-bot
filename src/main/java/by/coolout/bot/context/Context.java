package by.coolout.bot.context;

import java.util.HashMap;
import java.util.Map;

public class Context {

    public static final String CTX_STEP="step";
    public static final String CTX_DRINK="drink";
    public static final String CTX_VOLUME="volume";
    public static final String CTX_SYRUP="syrup";
    public static final String CTX_WISHES="wishes";
    public static final String CTX_TIME="time";
    public static final String CTX_PLACE="place";
    public static final String CTX_PRICE="price";
    public static final String CTX_USERNAME="username";
    public static final String CTX_LOGIN="login";

    private Map<String, Object> attributes = new HashMap<>();

    public void put(String attribute, Object value) { attributes.put(attribute, value); }

    public String getStringAttribute(String attribute) { return (String) attributes.get(attribute); }

    public Integer getIntegerAttribute(String attribute) { return (Integer) attributes.get(attribute); }
}
