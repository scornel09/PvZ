package com.epf;

public enum SlowType {
    NORMAL("normal"),
    SLOW_LOW("slow low"),
    SLOW_MEDIUM("slow medium"),
    SLOW_STOP("slow stop");

    private final String label;

    SlowType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static SlowType fromString(String text) {
        for (SlowType type : SlowType.values()) {
            if (type.label.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Aucun type de ralentissement correspondant à : " + text);
    }
}