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
	
	public void insertTemplate(String puzzle, String table, int holeCount){
		String getCntSql = "SELECT COUNT(*) FROM puzzletemplates";
		int count = getSimpleJdbcTemplate().queryForInt(getCntSql);
		String insertSql = "INSERT INTO puzzletemplates (id, puzzle, solution, holecount) values(?,?, ?, ?)";
		getSimpleJdbcTemplate().update(insertSql, new Object[]{count, 
															   puzzle,
															   table,
															   holeCount});
	}
	
	public String getTemplate(int id){
		String sql = "select puzzle from puzzletemplates where id = ?";
		String puzzle = getSimpleJdbcTemplate().queryForObject(sql, new PuzzleMapper(), id);
		return puzzle;

	}
	
	public String getSolution(int id){
		String sql = "select solution from puzzletemplates where id = ?";
		String solution = getSimpleJdbcTemplate().queryForObject(sql, new PuzzleMapper(), id);
		return solution;
	}
	
	private static class PuzzleMapper implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("puzzle");
		}
	}

	public int numberOfPuzzle(String level) {
		String getCntSql = "SELECT COUNT(*) FROM " + level +"puzzle";
		return getSimpleJdbcTemplate().queryForInt(getCntSql);
	}
}
