package com.hijackermax.kofprocessor;

import com.hijackermax.utils.lang.StringUtils;

import java.util.stream.Stream;

enum InstructionType {
    ADMINISTRATIVE("01", "-01 OOOOOOOOOOOO DDMMYYYY VVV KKKKKKK KKKK $RVAllllllll OOOOOOOOOOOO"),
    COORDINATES("05", "-05 PPPPPPPPPP KKKKKKKK XXXXXXXX.XXX YYYYYYY.YYY ZZZZ.ZZZ Bk MMMMMMM"),
    UNKNOWN("KOF-N/A", StringUtils.EMPTY);

    private final String value;
    private final String template;
    private final int blockLength;

    InstructionType(String value, String template) {
        this.value = value;
        this.template = template;
        this.blockLength = template.length();
    }

    public static InstructionType findByValue(String value) {
        return Stream.of(values())
                .filter(type -> StringUtils.equalsIgnoreCase(value, type.value))
                .findFirst()
                .orElse(UNKNOWN);
    }

    public String getValue() {
        return value;
    }

    public String getTemplate() {
        return template;
    }

    public int getBlockLength() {
        return blockLength;
    }
}
