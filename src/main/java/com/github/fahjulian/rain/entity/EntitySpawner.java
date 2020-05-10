package com.github.fahjulian.rain.entity;

import java.util.ArrayList;
import java.util.List;

import com.github.fahjulian.rain.level.Level;

public final class EntitySpawner extends Entity {

    private List<Entity> entities = new ArrayList<Entity>();

    public enum Type {
        MOB, PARTICLE;
    }

    public EntitySpawner(Class<? extends Entity> type, Entity.Specs specs, int amount) {
        for (int i = 0; i < amount; i++) {
            try {
                Entity e = type.getConstructor(specs.getClass()).newInstance(specs);
                entities.add(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (e.isRemoved()) {
                entities.remove(i);
            }
        }

        if (entities.size() == 0) remove();
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        
        for (Entity e: entities) 
            level.add(e);
    }
}
