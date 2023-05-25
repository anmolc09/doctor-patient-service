package com.learning.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InvoiceConstants {

    FILE_NAME("invoice Id: %s .pdf"),
    BUILDER_TYPE("attachment");



    private final String InvoiceMessage;
}
