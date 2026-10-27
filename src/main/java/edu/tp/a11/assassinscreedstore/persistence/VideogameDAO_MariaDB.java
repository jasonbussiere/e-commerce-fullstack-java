package edu.tp.a11.assassinscreedstore.persistence;


import edu.tp.a11.assassinscreedstore.model.Videogame;
import edu.tp.a11.assassinscreedstore.repository.Database;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//javadoc todo
public class VideogameDAO_MariaDB
        extends AbstractCrudDAO<Videogame, String>
        implements VideogameDAO {

    //SQL CONSTANTS
    private static final String INSERT_SQL = "INSERT INTO videogames " +
            "(gameId, title, genre, platform, releaseDate, unitPrice, description, imageURL, wikiUrl) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE videogames SET " +
            "title = ?, genre = ?, platform = ?, releaseDate = ?, unitPrice = ?, description = ?, imageURL = ?, wikiUrl = ? " +
            "WHERE gameId = ?";

    private static final String FIND_BY_TITLE =
            "SELECT * FROM videogames WHERE title LIKE ?";

    private static final String FIND_BY_GENRE =
            "SELECT * FROM videogames WHERE genre=?";

    private static final String FIND_BY_PLATFORM =
            "SELECT * FROM videogames WHERE platform=?";

    private static final String FIND_BY_RELEASE_DATE =
            "SELECT * FROM videogames WHERE releaseDate BETWEEN ? AND ?";

    private static final String FIND_BY_UNIT_PRICE =
            "SELECT * FROM videogames WHERE unitPrice=?";

    private static final String FIND_BY_DESCRIPTION =
            "SELECT * FROM videogames WHERE description LIKE ?";

    private static final String FIND_BY_KEYWORD =
            "SELECT * FROM videogames WHERE LOWER(title) LIKE ? OR LOWER(description) LIKE ?";

    public VideogameDAO_MariaDB(Database database) {
        super(database);
    }

    //ABSTRACT CRUD IMPLEMENTATION
    @Override
    protected String getTableName() {
        return "videogames";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "gameId";
    }

    @Override
    protected String getPrimaryKeyValue(Videogame entity) {
        return entity.getGameId();
    }

    @Override
    protected String getInsertSQL() {
        return INSERT_SQL;
    }

    @Override
    protected String getUpdateSQL() {
        return UPDATE_SQL;
    }

    @Override
    public Videogame mapRow(ResultSet rs) throws SQLException {
        Videogame game = new Videogame();
        game.setGameId(rs.getString("gameId"));
        game.setTitle(rs.getString("title"));
        game.setGenre(rs.getString("genre"));
        game.setPlatform(rs.getString("platform"));
        game.setUnitPrice(rs.getBigDecimal("unitPrice"));
        game.setDescription(rs.getString("description"));
        game.setImageUrl(rs.getString("imageURL"));
        game.setWikiUrl(rs.getString("wikiUrl"));
        Date release = rs.getDate("releaseDate");
        if (release != null) game.setReleaseDate(release.toLocalDate());

        return game;
    }

    @Override
    protected void fillInsertStatement(PreparedStatement ps, Videogame entity) throws SQLException {
        String newId = UUID.randomUUID().toString();
        entity.setGameId(newId);

        ps.setString(1, entity.getGameId());
        ps.setString(2, entity.getTitle());
        ps.setString(3, entity.getGenre());
        ps.setString(4, entity.getPlatform());
        ps.setDate(5, Date.valueOf(entity.getReleaseDate()));
        ps.setBigDecimal(6, entity.getUnitPrice());
        ps.setString(7, entity.getDescription());
        ps.setString(8, entity.getImageUrl());
        ps.setString(9, entity.getWikiUrl());
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement ps, Videogame entity) throws SQLException {
        ps.setString(1, entity.getTitle());
        ps.setString(2, entity.getGenre());
        ps.setString(3, entity.getPlatform());
        ps.setDate(4, Date.valueOf(entity.getReleaseDate()));
        ps.setBigDecimal(5, entity.getUnitPrice());
        ps.setString(6, entity.getDescription());
        ps.setString(7, entity.getImageUrl());
        ps.setString(8, entity.getGameId());
        ps.setString(9, entity.getWikiUrl());
    }

    //CONCRETE CRUD IMPLEMENTATION
    @Override
    public Videogame findByTitle(String keyword) {
        try(Connection conn = Database.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_TITLE)) {

            ps.setString(1, '%'+keyword+'%');

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByTitle", e);
        }
        return null;
    }

    @Override
    public List<Videogame> findByGenre(String genre) {
        List<Videogame> videogames = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_GENRE)) {

            ps.setString(1, genre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videogames.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByGenre", e);
        }
        return videogames;
    }

    @Override
    public List<Videogame> findByPlatform(String platform) {
        List<Videogame> videogames = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_PLATFORM)) {

            ps.setString(1, platform);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videogames.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByPlatform", e);
        }
        return videogames;
    }

    @Override
    public List<Videogame> findByReleaseDate(LocalDate start, LocalDate end) {
        List<Videogame> videogames = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_RELEASE_DATE)) {

            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videogames.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByReleaseDate", e);
        }
        return videogames;
    }

    @Override
    public List<Videogame> findByUnitPrice(BigDecimal price) {
        List<Videogame> videogames = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_UNIT_PRICE)) {

            ps.setBigDecimal(1, price);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videogames.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByMsrp", e);
        }
        return videogames;
    }

    @Override
    public List<Videogame> findByDescription(String description) {
        List<Videogame> videogames = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_DESCRIPTION)) {

            ps.setString(1, "%" + description + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videogames.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByDescription", e);
        }
        return videogames;
    }

    public List<Videogame> findByKeyword(String keyword) {
        List<Videogame> videogames = new ArrayList<>();
        try(Connection conn = Database.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_KEYWORD)) {

            String kw = "%"+keyword.trim().toLowerCase()+"%";

            ps.setString(1, kw);
            ps.setString(2, kw);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    videogames.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByKeyword", e);
        }
        return videogames;
    }
}
