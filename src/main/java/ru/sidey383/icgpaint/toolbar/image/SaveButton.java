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

public class SaveButton extends JButton implements ActionListener {

    private final Dimension size = new Dimension(40, 40);

    @NotNull
    private final ImageHolder imageHolder;

    private JFileChooser chooser;

    protected SaveButton(@NotNull ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
        addActionListener(this);
        setToolTipText("Save image to file");
        new BufferedImageIconSetter(this,  PaintToolbar.loadIcon("/icon/save.png"));
    }


    protected JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new SaveFileFilter("png", ".png"));
        fileChooser.addChoosableFileFilter(new SaveFileFilter("jpeg", ".jpg", ".jpeg"));
        fileChooser.addChoosableFileFilter(new SaveFileFilter("bmp", ".bmp"));
        fileChooser.addChoosableFileFilter(new SaveFileFilter("gif", ".gif"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        return fileChooser;
    }

    protected void applyFile(File f, String format) {
        try {
            BufferedImage image = imageHolder.getImage();
            ImageIO.write(image, format, f);
        } catch (IOException e) {
            ImageEditGroup.showErrorDialog(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (chooser != null)
            return;
        chooser = createFileChooser();
        chooser.setDialogTitle("Choose save location");
        int result = chooser.showSaveDialog(this);
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