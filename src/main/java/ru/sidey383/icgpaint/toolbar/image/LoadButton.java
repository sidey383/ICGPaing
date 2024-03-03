package ru.sidey383.icgpaint.toolbar.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.toolbar.BufferedImageIconSetter;
import ru.sidey383.icgpaint.toolbar.PaintToolbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadButton  extends JButton implements ActionListener {

    private final Dimension size = new Dimension(40, 40);

    @NotNull
    private final ImageHolder imageHolder;

    private JFileChooser chooser;

    protected LoadButton(@NotNull ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
        addActionListener(this);
        setToolTipText("Load image from file");
        new BufferedImageIconSetter(this,  PaintToolbar.loadIcon("/icon/load.png"));
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
                ImageEditGroup.showErrorDialog("Can't load image");
                return;
            }
            imageHolder.setImage(image);
        } catch (IOException e) {
            ImageEditGroup.showErrorDialog(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (chooser != null)
            return;
        chooser = createFileChooser();
        chooser.setDialogTitle("Choose image for loading");
        int result = chooser.showDialog(this, "Load image");
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (f != null)
                applyFile(f);
        }
        chooser = null;
    }

    @Override
    public Dimension getPreferredSize() {
        return size;
    }

    @Override
    public Dimension getMaximumSize() {
        return size;
    }

    @Override
    public Dimension getMinimumSize() {
        return size;
    }

}
