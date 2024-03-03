package ru.sidey383.icgpaint.toolbar;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.BaseDrawTools;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.iteraction.UserInteractions;
import ru.sidey383.icgpaint.toolbar.color.ColorButtonGroup;
import ru.sidey383.icgpaint.toolbar.image.ImageEditGroup;
import ru.sidey383.icgpaint.toolbar.tools.ToolButtonGroup;
import ru.sidey383.icgpaint.toolbar.tools.ToolSettingsButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class PaintToolbar extends JToolBar {

    public PaintToolbar(@NotNull UserInteractions interactions, @NotNull BaseDrawTools drawToolContext, @NotNull ImageHolder imageHolder) {
        @NotNull ImageEditGroup imageEditGroup = new ImageEditGroup(imageHolder);
        imageEditGroup.getButtons().forEach(this::add);
        @NotNull ToolButtonGroup toolButtonGroup = new ToolButtonGroup(drawToolContext);
        toolButtonGroup.getButtons().forEach(this::add);
        @NotNull ColorButtonGroup colorButtonGroup = new ColorButtonGroup(interactions.getColorInteractions());
        colorButtonGroup.getButtons().forEach(this::add);
        add(colorButtonGroup.getStaticColorButtons());
        add(new ToolSettingsButton(drawToolContext, Objects.requireNonNull(loadIcon("/icon/settings.png"))));
    }

    public static BufferedImage loadIcon(String path) {
        try {
            URL url = PaintToolbar.class.getResource(path);
            if (url == null) {
                return null;
            }
            return ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Image load error " + path);
            return null;
        }
    }

}
