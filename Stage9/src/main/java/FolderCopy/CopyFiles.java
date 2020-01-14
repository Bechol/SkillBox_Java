package FolderCopy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@Data
@AllArgsConstructor
@Log4j2
public class CopyFiles extends SimpleFileVisitor<Path> {

    private Path source;
    private Path destination;

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
        Path newDes = destination.resolve(source.relativize(path));
        log.info("file copy source.relativize:" + source.relativize(path) + " and newDes=" + newDes);
        try {
            Files.copy(path, newDes, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        Path newDes = destination.resolve(source.relativize(path));
        log.info("file copy source.relativize:" + source.relativize(path) + " and newDes=" + newDes);
        try {
            Files.copy(path, newDes, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }
}
