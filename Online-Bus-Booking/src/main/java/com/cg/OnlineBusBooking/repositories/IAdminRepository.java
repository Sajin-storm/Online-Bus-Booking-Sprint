package com.cg.OnlineBusBooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.OnlineBusBooking.entities.AdminUser;

@Repository
public interface IAdminRepository extends JpaRepository<AdminUser,Integer>{

}
