package uk.gov.dhsc.htbhf.logging;

public class ExceptionDetailGenerator {

    /**
     * Generates a single line Exception stack trace detail to aid viewing all the information available in a single
     * line when viewed in Logit.
     * @param exception The exception used to build a string
     * @return The built String.
     */
    public static String constructExceptionDetail(Exception exception) {
        StringBuilder detail = new StringBuilder();
        Throwable ex = exception;
        detail.append(getThrowableDetail(ex));
        while (ex.getCause() != null && ex.getCause() != ex) {
            ex = ex.getCause();
            detail.append(", wraps: ");
            detail.append(getThrowableDetail(ex));
        }
        return detail.toString().replace("\n", " ");
    }

    private static String getThrowableDetail(Throwable t) {
        StringBuilder detail = new StringBuilder();
        detail.append(t.getMessage());
        StackTraceElement[] trace = t.getStackTrace();
        if (trace != null && trace.length > 0) {
            detail.append(" (at " + trace[0] + ")");
        }
        return detail.toString();
    }

}
