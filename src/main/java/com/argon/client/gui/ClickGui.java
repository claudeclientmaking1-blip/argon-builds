package com.argon.client.gui;
import com.argon.client.module.Module;
import com.argon.client.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
public class ClickGui extends Screen {
    public ClickGui() { super(Text.literal("Argon")); }
    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        super.render(ctx, mx, my, delta);
        ctx.fill(10,10,160,25+ModuleManager.getModules().size()*14,0xCC000000);
        ctx.drawText(textRenderer,"§bArgon §7Client",15,13,0xFFFFFF,true);
        int y=27;
        for(Module m:ModuleManager.getModules()){
            ctx.drawText(textRenderer,m.getName(),15,y,m.isEnabled()?0xFF55FF55:0xFFAAAAAA,true);
            y+=14;
        }
    }
    @Override
    public boolean mouseClicked(double mx,double my,int btn){
        int y=27;
        for(Module m:ModuleManager.getModules()){
            if(mx>=15&&mx<=155&&my>=y&&my<=y+12){m.toggle();return true;}
            y+=14;
        }
        return super.mouseClicked(mx,my,btn);
    }
    @Override public boolean shouldPause(){return false;}
}