package edu.tp.a11.assassinscreedstore.persistence;


import edu.tp.a11.assassinscreedstore.repository.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCrudDAO<T,ID> implements CrudDAO<T, ID> {

    protected final Database database;

    public AbstractCrudDAO(Database database) {
        this.database = database;
    }

    protected abstract String getTableName();

    protected abstract String getPrimaryKeyColumn();

    protected abstract ID getPrimaryKeyValue(T entity);

    protected abstract String getInsertSQL();

    protected abstract String getUpdateSQL();

    protected abstract T mapRow(ResultSet rs) throws SQLException;

    protected abstract void fillInsertStatement(PreparedStatement ps, T entity) throws SQLException;

    protected abstract void fillUpdateStatement(PreparedStatement ps, T entity) throws SQLException;

    @SuppressWarnings("SqlSourceToSinkFlow")

    @Override
    public T findById(ID id) {
        String sql = "SELECT * FROM " + getTableName()
                + " WHERE " + getPrimaryKeyColumn() + " = ?";

        try (Connection conn = database.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();

        String sql = "SELECT * FROM " + getTableName();

        try (Connection conn = database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findALL", e);
        }
        return list;
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    @Override
    public T update(T entity) {
        try (Connection conn = database.getConnection();
             PreparedStatement ps = conn.prepareStatement(getUpdateSQL())) {

            fillUpdateStatement(ps, entity);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erreur avec update", e);
        }
        return entity;
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    @Override
    public boolean delete(ID id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec delete", e);
        }
    }

    @SuppressWarnings({"unchecked", "SqlSourceToSinkFlow"})
    @Override
    public T create(T entity) {
        try (Connection conn = database.getConnection();
             PreparedStatement ps = conn.prepareStatement(getInsertSQL())) {

            fillInsertStatement(ps, entity);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec insert", e);
        }
        return findById(getPrimaryKeyValue(entity));
    }

}



