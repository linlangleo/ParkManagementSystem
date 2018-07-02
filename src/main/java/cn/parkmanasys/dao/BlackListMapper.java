package cn.parkmanasys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.BlackList;


@Repository
public interface BlackListMapper extends JpaRepository<BlackList, Integer>{
	@Query("from #{#entityName} p where p.id = :id")
	public BlackList getBlackListById(@Param("id") Integer id);
}
