package com.teammacc.client.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.teammacc.client.entity.Client

@Repository
interface ClientRepository : JpaRepository<Client, Long>{
}