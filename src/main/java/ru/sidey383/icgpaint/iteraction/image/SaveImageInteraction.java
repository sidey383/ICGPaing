package ru.sidey383.icgpaint.iteraction.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveImageInteraction {

    @NotNull
    private final ImageHolder imageHolder;

    private JFileChooser chooser;

    public SaveImageInteraction(@NotNull ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
    }

    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new SaveFileFilter("png", ".png"));
        fileChooser.addChoosableFileFilter(new SaveFileFilter("jpeg", ".jpg", ".jpeg"));
        fileChooser.addChoosableFileFilter(new SaveFileFilter("bmp", ".bmp"));
        fileChooser.addChoosableFileFilter(new SaveFileFilter("gif", ".gif"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        return fileChooser;
    }

    private void applyFile(File f, String format) {
        try {
            BufferedImage image = imageHolder.getImage();
            ImageIO.write(image, format, f);
        } catch (IOException e) {
            ImageInteractions.showErrorDialog(e.getMessage());
        }
    }

    public void apply(Component parent) {
        if (chooser != null)
            return;
        chooser = createFileChooser();
        chooser.setDialogTitle("Choose save location");
        int result = chooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            FileFilter filter = chooser.getFileFilter();
            final String format;
            if (filter instanceof SaveFileFilter sf) {
                format = sf.getBaseFormat();
                f = sf.normalize(f);
            } else {
                format = "png";
            }
            if (f != null)
                applyFile(f, format);
        }
        chooser = null;
    }

}
