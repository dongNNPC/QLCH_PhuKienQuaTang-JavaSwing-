/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.DAO;

import java.util.List;

/**
 *
 * @author asus
 */
public abstract class QLPK<Entity, Key> {

    abstract public void insert(Entity entity);

    abstract public void update(Entity entity);

    abstract public void delete(Key key);

    abstract public List<Entity> selectAll();

    abstract public Entity selectByid(Key key);

    abstract protected List<Entity> selectBySql(String sql, Object... args);
}
