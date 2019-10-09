package com.sut.sa.g13.Repository;

import com.sut.sa.g13.Entity.PackageType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PackageTypeRepository extends JpaRepository<PackageType, Long> {
	PackageType findByPackagetypeid(Long packagetypeid);
}