package ru.sidey383.icgpaint.toolbar.image;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SaveFileFilter extends FileFilter {

    private final String[] formats;

    private final String baseFormat;

    public SaveFileFilter(String baseFormat, String... acceptedFormats) {
        this.baseFormat = baseFormat;
        this.formats = acceptedFormats;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        } else {
            String filename = f.getName().toLowerCase();
            for (String format : formats) {
                if (filename.endsWith(format))
                    return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Images " + String.join(" ", formats);
    }

    public String getBaseFormat() {
        return baseFormat;
    }


    public File normalize(File f) {
        String filename = f.getName().toLowerCase();
        for (String format : formats) {
            if (filename.endsWith(format))
                return f;
        }
        File parent = f.getParentFile();
        return new File(parent, filename + "." + baseFormat);
    }

}
