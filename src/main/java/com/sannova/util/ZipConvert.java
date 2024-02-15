package com.sannova.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipConvert {
    public static byte[] zipBytes(String filename, List<byte[]> input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        ZipEntry entry = new ZipEntry(filename);
        for(byte[] byteFile:input) {
            entry.setSize(byteFile.length);
            zos.putNextEntry(entry);
            zos.write(byteFile);
        }
        zos.closeEntry();
        zos.close();
        return baos.toByteArray();
    }
}
