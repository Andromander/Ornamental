package com.androsa.ornamental.blocks;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public enum PoleType implements IStringSerializable {
    TL_CORNER("topleft_corner"     , Shape.CORNER, true, false, false, false),
    TR_CORNER("topright_corner"    , Shape.CORNER, false, true, false, false),
    BL_CORNER("bottomleft_corner"  , Shape.CORNER, false, false, true, false),
    BR_CORNER("bottomright_corner" , Shape.CORNER, false, false, false, true),
    T_HALF   ("top_half"           , Shape.HALF,   true, true, false, false),
    L_HALF   ("left_half"          , Shape.HALF,   true, false, true, false),
    R_HALF   ("right_half"         , Shape.HALF,   false, true, false, true),
    B_HALF   ("bottom_half"        , Shape.HALF,   false, false, true, true),
    TL_BR    ("topleft_bottomright", Shape.CROSS,  true, false, false, true),
    TR_BL    ("topright_bottomleft", Shape.CROSS,  false, true, true, false),
    TL_FILL  ("topleft_fill"       , Shape.FILL,   true, true, true, false),
    TR_FILL  ("topright_fill"      , Shape.FILL,   true, true, false, true),
    BL_FILL  ("bottomleft_fill"    , Shape.FILL,   true, false, true, true),
    BR_FILL  ("bottomright_fill"   , Shape.FILL,   false, true, true, true),
    FULL     ("full"               , Shape.BLOCK,  true, true, true, true);

    private final String name;
    private final Shape shape;
    private final boolean[] corners;

    PoleType(String name, Shape shape, boolean tl, boolean tr, boolean bl, boolean br) {
        this.name = name;
        this.shape = shape;
        this.corners = new boolean[] { tl, tr, bl, br };
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    @Nonnull
    public String getString() {
        return name;
    }

    public Shape getShape() {
        return shape;
    }

    public boolean[] getCorners() {
        return corners;
    }

    public enum Shape {
        CORNER,
        HALF,
        CROSS,
        FILL,
        BLOCK
    }
}
