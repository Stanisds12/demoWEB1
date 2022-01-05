package com.example.demoWEB1.dao;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoWEB1.model.Teacher;


@Repository
@Transactional
public class TeacherDao implements Dao<Teacher> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger log=LoggerFactory.getLogger(TeacherDao.class);

	protected RowMapper<Teacher> rowMapper=(rs,rowNum)->{
		Teacher teacher=new Teacher();
		teacher.setId(rs.getInt("id"));
		teacher.setName(rs.getString("name"));
		teacher.setExpertise(rs.getString("expertise"));
		
		return teacher;
	};
	

	
	@Override
	public List<Teacher> list() {
		
		String sql="SELECT id,name,expertise FROM teacher ORDER BY id";
		
		return jdbcTemplate.query(sql, rowMapper);
	}
	@Override
	public void create(Teacher t) {
		
		String sql="INSERT INTO teacher(id,name,expertise) VALUES(?,?,?)";
		int insert= jdbcTemplate.update(sql,t.getId(),t.getName(),t.getExpertise());
		if(insert==1) log.info("Insert Sucessful"+ t.toString());
	}
	//@SuppressWarnings("deprecation")
	@Override
	public Optional<Teacher> get(int id) {
		String sql = "SELECT id,name,expertise FROM teacher WHERE id=?";
		List<Teacher> teacher = null;
		teacher = jdbcTemplate.query(sql, rowMapper,id);
		return teacher.stream().findFirst();

	}

	/*public Optional<Teacher> get(int id) {
		
		String sql="SELECT id,name,experise FROM teacher WHERE id=?"; 
		Teacher teacher=null;
		try {
			 teacher= jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
			
		}catch(DataAccessException ex) {
			log.info("Not Found:"+ id);
		}
		
	
		return Optional.ofNullable(teacher);
	}*/
	@Override
	public void update(Teacher t, int id) {

		String sql="UPDATE teacher SET name=?, expertise=? WHERE id=?";
		int update= jdbcTemplate.update(sql,t.getName(),t.getExpertise(),t.getId());
		if(update==1) log.info("Update Sucessful"+ t.toString());
		
	}
	@Override
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM teacher WHERE id=?",id);
		
	}


}
