package server.service;

import java.util.List;

import server.service.exception.MemberCriteriaNullException;
import shared.criteria.MemberCriteria;
import shared.dto.MemberDto;



public interface ManageMember{
	
	public List<MemberDto> getLastMembers(int amountMember);
	
	public void addMember(MemberDto memberDto);
	
	public List<MemberDto> retrieveMembers(MemberCriteria memberCriteria) throws MemberCriteriaNullException;
	
	public MemberDto retrieveMember(int id);
	
	public void deleteMember(int memberId);
	
	public void updateMember(MemberDto memberDto);

}
