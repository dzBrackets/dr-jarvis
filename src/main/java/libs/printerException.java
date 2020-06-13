package libs;

public class printerException extends NullPointerException {

    public printerException(String default_printer_not_found) {
        super(default_printer_not_found);
    }

}
