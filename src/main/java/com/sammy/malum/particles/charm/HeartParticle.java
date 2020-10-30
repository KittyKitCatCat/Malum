package com.sammy.malum.particles.charm;

import com.sammy.malum.particles.MalumParticle;
import com.sammy.malum.particles.ParticlePhase;
import com.sammy.malum.particles.ScalePhase;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HeartParticle extends MalumParticle
{
    protected HeartParticle(ClientWorld world, double xSpeed, double ySpeed, double zSpeed, double x, double y, double z, float scale, IAnimatedSprite spriteSet)
    {
        super(world, xSpeed, ySpeed, zSpeed, x, y, z, spriteSet, new ScalePhase(2, 20, 0, scale, true), new ParticlePhase(2, 20, 0), new ScalePhase(2, 20, 0, scale, false));
        //0-11 entrance
        //12-32 animation
    }
    
    @Override
    public void tick()
    {
        super.tick();
        motionX *= 0.9f;
        motionY *= 0.9f;
        motionZ *= 0.9f;
    }
    
    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<HeartParticleData>
    {
        private final IAnimatedSprite spriteSet;
        
        public Factory(IAnimatedSprite spriteSet)
        {
            this.spriteSet = spriteSet;
        }
        
        @Override
        public Particle makeParticle(HeartParticleData data, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            return new HeartParticle(world, xSpeed, ySpeed, zSpeed, x, y, z, data.scale, spriteSet);
        }
    }
}