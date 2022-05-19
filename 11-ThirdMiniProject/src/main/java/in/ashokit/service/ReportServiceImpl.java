package in.ashokit.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.EligibilityEntity;
import in.ashokit.repository.EligibilityDtlsRepo;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private EligibilityDtlsRepo repository;

	@Override
	public List<String> getPlanNames() {
		return repository.getUniquePlanNames();
	}

	@Override
	public List<String> getplanStatus() {
		return repository.getUniquePlanStatus();
	} 

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		List<EligibilityEntity> eligiRecords=null;
	if(isSearchRequestEmpty(request)) {
      eligiRecords=repository.findAll();
	}
	else {
		  
		String planName=request.getPlanName();
		String planStatus=request.getPlanStatus();
		LocalDate startDate=request.getStartDate();
		LocalDate endDate=request.getEndDate();
		
		EligibilityEntity entity=new EligibilityEntity();
		
		if(planName!=null && !planName.equals("")){
			entity.setPlanName(planName);
		}
		if(planStatus!=null && planStatus.equals("")) {
			entity.setPlanStatus(planStatus);
		}
		
		if(startDate!=null & endDate!=null) {
			entity.setStartDate(startDate);
			entity.setEndDate(endDate);
		}
		
		Example<EligibilityEntity> ex=Example.of(entity);
		eligiRecords=repository.findAll();
	}
	
	List<SearchResponse> response=new ArrayList<SearchResponse>();
	for(EligibilityEntity eligirecord:eligiRecords) {
		SearchResponse sr=new SearchResponse();
		BeanUtils.copyProperties(eligirecord, sr);
		response.add(sr);
	}
		
	return response;
	}
	
	private boolean isSearchRequestEmpty(SearchRequest request) {
		
      if(request.getPlanName()!=null && !request.getPlanName().equals("")) {
    	  return false;
      }
      if(request.getPlanStatus()!=null && !request.getPlanStatus().equals("")) {
    	  return false;
      }
      if(request.getStartDate()!=null && !request.getStartDate().equals("")) {
    	  return false;
      }
      if(request.getEndDate()!=null && !request.getEndDate().equals("")) {
    	  return false;
      }
		return true;
	}
	
}
