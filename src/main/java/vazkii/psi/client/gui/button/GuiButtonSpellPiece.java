/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.client.gui.button;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.text.StringTextComponent;

import vazkii.psi.api.spell.SpellGrid;
import vazkii.psi.api.spell.SpellPiece;
import vazkii.psi.client.gui.GuiProgrammer;

public class GuiButtonSpellPiece extends Button {
	public SpellPiece piece;
	final GuiProgrammer gui;

	public GuiButtonSpellPiece(GuiProgrammer gui, SpellPiece piece, int x, int y) {
		super(x, y, 16, 16, StringTextComponent.EMPTY, button -> {});
		this.gui = gui;
		this.piece = piece;
	}

	public GuiButtonSpellPiece(GuiProgrammer gui, SpellPiece piece, int x, int y, Button.IPressable pressable) {
		super(x, y, 16, 16, StringTextComponent.EMPTY, pressable);
		this.gui = gui;
		this.piece = piece;
	}

	@Override
	public void renderButton(MatrixStack ms, int mouseX, int mouseY, float pTicks) {
		if (active && visible) {
			boolean hover = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

			IRenderTypeBuffer.Impl buffers = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
			ms.push();
			ms.translate(x, y, 0);
			piece.draw(ms, buffers, 0xF000F0);
			buffers.finish();
			ms.pop();
			Minecraft.getInstance().getTextureManager().bindTexture(GuiProgrammer.texture);
			if (hover) {
				piece.getTooltip(gui.tooltip);
				blit(ms, x, y, 16, gui.ySize, 16, 16, 94 + SpellGrid.GRID_SIZE * 18, 94 + SpellGrid.GRID_SIZE * 18);
			}

		}
	}

	public SpellPiece getPiece() {
		return piece;
	}

	public String getPieceSortingName() {
		return piece.getSortingName();
	}

	public void setPiece(SpellPiece piece) {
		this.piece = piece;
	}
}
