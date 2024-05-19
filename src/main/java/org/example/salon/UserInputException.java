package org.example.salon;

public class UserInputException extends Exception {
    public UserInputException(String message) {
        super(message);
    }
}

class UsernameNotFoundException extends UserInputException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}

class PhoneNumberException extends UserInputException {
    public PhoneNumberException(String message) {
        super(message);
    }
}

class EmailFormatException extends UserInputException {
    public EmailFormatException(String message) {
        super(message);
    }
}
class BlankEmailField extends EmailFormatException {
    public BlankEmailField(String message) {
        super(message);
    }
}

class AbsenceofGmailSuffix extends EmailFormatException {
    public AbsenceofGmailSuffix(String message) {
        super(message);
    }
}

class NotProperlyFormatedEmailPrefix extends EmailFormatException {
    public NotProperlyFormatedEmailPrefix(String message) {
        super(message);
    }
}

class PasswordFormatException extends UserInputException {
    public PasswordFormatException(String message) {
        super(message);
    }
}

class AddressFormatException extends UserInputException {
    public AddressFormatException(String message) {
        super(message);
    }
}

class AppointmentTimeException extends UserInputException {
    public AppointmentTimeException(String message) {
        super(message);
    }
}

class ServiceNotFoundException extends UserInputException {
    public ServiceNotFoundException(String message) {
        super(message);
    }
}

class InsufficientPointsException extends UserInputException {
    public InsufficientPointsException(String message) {
        super(message);
    }
}

class InvalidPointsException extends UserInputException {
    public InvalidPointsException(String message) {
        super(message);
    }
}

class InvalidDateException extends UserInputException {
    public InvalidDateException(String message) {
        super(message);
    }
}

class InvalidTimeException extends UserInputException {
    public InvalidTimeException(String message) {
        super(message);
    }
}
