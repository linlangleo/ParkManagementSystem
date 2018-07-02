package cn.parkmanasys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.SharingPlatformApplication;
import cn.parkmanasys.entity.SharingPlatformAuthorization;

@Repository
public interface SharingPlatformApplicationMapper extends JpaRepository<SharingPlatformApplication, Integer>{

}
