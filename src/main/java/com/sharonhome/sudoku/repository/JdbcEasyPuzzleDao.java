package com.sharonhome.sudoku.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;


public class JdbcEasyPuzzleDao extends SimpleJdbcDaoSupport implements
		PuzzleDao {

	public String getNewPuzzleByID(String id) {
		String puzzle = getSimpleJdbcTemplate().queryForObject("select puzzle from easypuzzle where id = ?", new PuzzleMapper(), id);
		return puzzle;
	}
	
	private static class PuzzleMapper implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("puzzle");
		}
	}

}
