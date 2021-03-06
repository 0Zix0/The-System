package com.system.xpreader;

/**
 * Created by bison on 02-01-2016.
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

public class CompressionUtils {

    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer); // returns the generated code... index
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();
        /*
        LOG.debug("Original: " + data.length / 1024 + " Kb");
        LOG.debug("Compressed: " + output.length / 1024 + " Kb");
        */
        return output;
    }
    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();
        /*
        LOG.debug("Original: " + data.length);
        LOG.debug("Compressed: " + output.length);
        */
        return output;
    }

    public static byte[] gzipDecodeByteArray(byte[] data) {
        GZIPInputStream gzipInputStream = null;
        try {
            gzipInputStream = new GZIPInputStream(
                    new ByteArrayInputStream(data));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            byte[] buffer = new byte[1024];
            while (gzipInputStream.available() > 0) {
                int count = gzipInputStream.read(buffer, 0, 1024);
                if(count > 0) {
                    //System.out.println("Read " + count + " bytes");
                    outputStream.write(buffer, 0, count);
                }
            }

            outputStream.close();
            gzipInputStream.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
