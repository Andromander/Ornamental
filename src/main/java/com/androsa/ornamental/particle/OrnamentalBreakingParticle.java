package com.androsa.ornamental.particle;

import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OrnamentalBreakingParticle extends BreakingParticle {

    protected OrnamentalBreakingParticle(ClientWorld world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    @OnlyIn(Dist.CLIENT)
    public static class LapisFactory implements IParticleFactory<BasicParticleType> {
        public Particle makeParticle(BasicParticleType particle, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.LAPIS_LAZULI));
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class RedstoneFactory implements IParticleFactory<BasicParticleType> {
        public Particle makeParticle(BasicParticleType particle, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.REDSTONE));
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class BrickFactory implements IParticleFactory<BasicParticleType> {
        public Particle makeParticle(BasicParticleType particle, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.BRICK));
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class NetherBrickFactory implements IParticleFactory<BasicParticleType> {
        public Particle makeParticle(BasicParticleType particle, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.NETHER_BRICK));
        }
    }
}