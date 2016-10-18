package cashregister.domain;

public enum Constraint {
    REQUIRED, POSITIVE, UNIQUE, EXISTS;

    public String toCapitalizedString() {
        String constraintName = this.name();
        return constraintName.charAt(0) + constraintName.substring(1).toLowerCase();
    }
}
