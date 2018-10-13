package mchorse.blockbuster.client.gui.dashboard.panels.recording_editor.actions;

import java.util.Map.Entry;

import mchorse.blockbuster.recording.ActionRegistry;
import mchorse.blockbuster.recording.actions.Action;
import mchorse.mclib.client.gui.framework.GuiTooltip;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public abstract class GuiActionPanel<T extends Action> extends GuiElement
{
    public T action;
    private String title = "";
    private String description = "";

    public GuiActionPanel(Minecraft mc)
    {
        super(mc);
        this.createChildren();
    }

    public void fill(T action)
    {
        this.action = action;

        for (Entry<String, Class<? extends Action>> entry : ActionRegistry.NAME_TO_CLASS.entrySet())
        {
            if (entry.getValue() == action.getClass())
            {
                this.setKey(entry.getKey());

                break;
            }
        }
    }

    public void appear()
    {}

    public void setKey(String key)
    {
        this.title = I18n.format("blockbuster.gui.record_editor.actions." + key + ".title");
        this.description = I18n.format("blockbuster.gui.record_editor.actions." + key + ".desc");
    }

    @Override
    public void draw(GuiTooltip tooltip, int mouseX, int mouseY, float partialTicks)
    {
        super.draw(tooltip, mouseX, mouseY, partialTicks);

        if (!this.title.isEmpty())
        {
            this.font.drawStringWithShadow(this.title, this.area.x + 10, this.area.y + 10, 0xffffff);
            this.font.drawSplitString(this.description, this.area.x + 10, this.area.y + 30, this.area.w / 3, 0xcccccc);
        }
    }
}