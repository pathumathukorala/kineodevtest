package com.e3.test.exception;


public enum ErrorCode {
    ERR_001("ERR_001", "Unable to delete company as it contains active employees."),
    ERR_002("ERR_002", "Unable to find a company matching the given Company ID."),
    ERR_003("ERR_003", "Unable to find an employee matching the given Employee ID."),
    ERR_004("ERR_004", "Unable to delete the employee from the records. "),
    ERR_005("ERR_005", "Request validation error"),
    ERR_006("ERR_006", "Unable to create employee. Possible duplicate entry."),
    ERR_UNKNOWN("ERR_UNKNOWN", "Unknown error, please contact support.");

    public final String code;
    public final String errorMessage;

    private ErrorCode(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public static ErrorCode findByCode(String code) {
        for (ErrorCode e : values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }

        return ERR_UNKNOWN;
    }

}
