package com.lcaohoanq.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileModel {
    private String name;
    private String absolutePath;
    private long length;
    private String extension;
}
