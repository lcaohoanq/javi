package com.lcaohoanq.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FolderModel {
    private String name;
    private String absolutePath;
}
