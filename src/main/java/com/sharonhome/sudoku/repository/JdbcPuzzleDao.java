package com.sharonhome.sudoku.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.sharonhome.sudoku.generator.RandomNumberGen;
import com.sharonhome.sudoku.generator.RandomNumberGenbyRandom;


public class JdbcPuzzleDao extends SimpleJdbcDaoSupport implements
		PuzzleDao {
	private RandomNumberGen rand = new RandomNumberGenbyRandom();
	
	public void setRand(RandomNumberGen rand){
		this.rand = rand;
	}
	
	public String getPuzzle(String level) {
		String getCntSql = "SELECT COUNT(*) FROM " + level +"puzzle";
		int count = getSimpleJdbcTemplate().queryForInt(getCntSql);
		int id = rand.nextInt(count);
		String sql = "select puzzle from " + level + "puzzle where id = ?";
		String puzzle = getSimpleJdbcTemplate().queryForObject(sql, new PuzzleMapper(), id);
		return puzzle;
	}
	
	public void insertPuzzle(String level, String puzzle) {
		String getCntSql = "SELECT COUNT(*) FROM " + level +"puzzle";
		int count = getSimpleJdbcTemplate().queryForInt(getCntSql);
		String insertSql = "INSERT INTO " + level + "puzzle (id, puzzle) values(?,?)";
		getSimpleJdbcTemplate().update(insertSql, new Object[]{count, puzzle});
	}
	
	private static class PuzzleMapper implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("puzzle");
		}
	}

}
