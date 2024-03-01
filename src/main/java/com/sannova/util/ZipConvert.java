package com.sannova.util;

import com.sannova.dto.ZipFormattedFiles;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipConvert {
    public static byte[] zipBytes(List<ZipFormattedFiles> zipFormattedFiles) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (ZipFormattedFiles file : zipFormattedFiles) {
                ZipEntry entry = new ZipEntry(file.getFileName());
                entry.setSize(file.getFileByte().length);
                zos.putNextEntry(entry);
                zos.write(file.getFileByte());
            }
        }
        return baos.toByteArray();
    }

    public static ZipFormattedFiles formattedZipArrayOfFiles(String fileName,byte[] bytes){
        return ZipFormattedFiles.builder()
                .fileName(fileName)
                .fileByte(bytes)
                .build();

    }
}
