import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A simple file archive format.
 */
public class FileArchive {

    public byte[] data;
    public int fileCount;
    public int[] fileHash;
    public int[] fileSizeInflated;
    public int[] fileSizeDeflated;
    public int[] fileOffset;
    public boolean unpacked;

    public FileArchive(byte[] src) throws IOException {
        load(src);
    }

    public void load(byte[] src) throws IOException {
        Buffer buffer = new Buffer(src);

        int unpackedSize = buffer.read24();
        int packedSize = buffer.read24();

        if (packedSize != unpackedSize) {
            data = new byte[unpackedSize];
            Bzip2Decompressor.decompress(data, unpackedSize, src, packedSize, 6);
            buffer = new Buffer(data);
            unpacked = true;
        } else {
            data = src;
            unpacked = false;
        }

        fileCount = buffer.readU16();
        fileHash = new int[fileCount];
        fileSizeInflated = new int[fileCount];
        fileSizeDeflated = new int[fileCount];
        fileOffset = new int[fileCount];

        int offset = buffer.position + (fileCount * 10);

        for (int file = 0; file < fileCount; file++) {
            fileHash[file] = buffer.read32();
            fileSizeInflated[file] = buffer.read24();
            fileSizeDeflated[file] = buffer.read24();
            fileOffset[file] = offset;
            offset += fileSizeDeflated[file];
        }
    }

    public void export(String s, Path dst) throws IOException {
        byte[] data = read(s);
        if (data == null) {
            throw new FileNotFoundException(s);
        }
        try (OutputStream out = Files.newOutputStream(dst)) {
            out.write(data);
        }
    }

    public byte[] read(String s) throws IOException {
        int hash = 0;
        s = s.toUpperCase();
        for (int j = 0; j < s.length(); j++) {
            hash = ((hash * 61) + s.charAt(j)) - 32;
        }

        for (int file = 0; file < fileCount; file++) {
            if (fileHash[file] != hash) {
                continue;
            }

            byte[] dst = new byte[fileSizeInflated[file]];

            if (!unpacked) {
                Bzip2Decompressor.decompress(dst, fileSizeInflated[file], data, fileSizeDeflated[file], fileOffset[file]);
            } else {
                System.arraycopy(data, fileOffset[file], dst, 0, fileSizeInflated[file]);
            }
            return dst;
        }
        return null;
    }

}
