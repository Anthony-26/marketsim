package fr.marketsim.application.utilities;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class ApplicationConstants {

    /** BigDecimal Values **/
    public static final int QUANTITY_SCALE = 10;
    public static final int QUANTITY_PRECISION = 19;
    public static final int PRICE_SCALE = 4;
    public static final int PRICE_PRECISION = 19;

    /** Default Application Values **/
    public static final BigDecimal INITIAL_BALANCE = new BigDecimal("10000.00");

    /** Key constants **/
    public static final String TRACE_ID_KEY = "traceId";
    public static final String TRACE_ID_HEADER_KEY = "X-Trace-Id";

    /** Business Code Error **/
    public static final String EMAIL_ALREADY_EXISTS = "EMAIL_ALREADY_EXISTS";
    public static final String EMAIL_DOES_NOT_EXIST = "EMAIL_DOES_NOT_EXIST";
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";

}
