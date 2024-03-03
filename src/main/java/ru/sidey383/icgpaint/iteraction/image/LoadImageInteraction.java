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

public class LoadImageInteraction {

    @NotNull
    private final ImageHolder imageHolder;

    private JFileChooser chooser;

    public LoadImageInteraction(@NotNull ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
    }

    protected JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileFilter() {

            public String getDescription() {
                return "Images";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".bmp") || filename.endsWith(".gif") ;
                }
            }
        });
        return fileChooser;
    }

    protected void applyFile(File f) {
        try {
            BufferedImage image = ImageIO.read(f);
            if (image == null) {
                ImageInteractions.showErrorDialog("Can't load image");
                return;
            }
            imageHolder.setImage(image);
        } catch (IOException e) {
            ImageInteractions.showErrorDialog(e.getMessage());
        }
    }

    public void apply(Component parent) {
        if (chooser != null)
            return;
        chooser = createFileChooser();
        chooser.setDialogTitle("Choose image for loading");
        int result = chooser.showDialog(parent, "Load image");
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (f != null)
                applyFile(f);
        }
        chooser = null;
    }

}
