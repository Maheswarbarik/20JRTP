package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.EligibilityEntity;
import in.ashokit.response.SearchResponse;

public interface EligibilityDtlsRepo extends JpaRepository<EligibilityEntity, Integer>,JpaSpecificationExecutor<EligibilityEntity> {
    @Query("select distinct(planName) from EligibilityEntity")
	public List<String> getUniquePlanNames();
    @Query("select distinct(planStatus) from EligibilityEntity")
	public List<String> getUniquePlanStatus();
    
}
