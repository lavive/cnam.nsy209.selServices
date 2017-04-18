package server.service.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import server.dao.MemberDao;
import server.dao.entity.MemberEntity;
import server.service.ManageMember;
import server.service.exception.MemberCriteriaNullException;
import server.service.transform.DtoToEntity;
import server.service.transform.EntityToDto;
import shared.criteria.MemberCriteria;
import shared.dto.MemberDto;


public class ManageMemberBean implements ManageMember {
	
	@Inject
	private MemberDao memberDao;

	@Override
	public List<MemberDto> getLastMembers(int amountMember) {
		List<MemberDto> members = new ArrayList<MemberDto>();
		for(MemberEntity member:memberDao.getListLastMember(amountMember)){
			members.add(EntityToDto.memberEntityToDto(member));
		}
		
		return members;
	}

	@Override
	public List<MemberDto> retrieveMembers(MemberCriteria memberCriteria) throws MemberCriteriaNullException{
		if(memberCriteria == null || memberCriteria.isEmpty())
			throw new MemberCriteriaNullException("Un des critères de recherche d'un membre au moins ne doit pas être null!");
		if(memberCriteria.getName() != null && memberCriteria.getForname() != null){
			List<MemberDto> members = new ArrayList<MemberDto>();
			for(MemberEntity member:memberDao.getMembersByNameAndForname(memberCriteria.getName(),memberCriteria.getForname())){
				members.add(EntityToDto.memberEntityToDto(member));
			}
			
			return members;
		}
		else if(memberCriteria.getName() != null){
			List<MemberDto> members = new ArrayList<MemberDto>();
			for(MemberEntity member:memberDao.getMembersByName(memberCriteria.getName())){
				members.add(EntityToDto.memberEntityToDto(member));
			}
			
			return members;
		}
		else if(memberCriteria.getTown() != null){
			List<MemberDto> members = new ArrayList<MemberDto>();
			for(MemberEntity member:memberDao.getMembersByTown(memberCriteria.getTown())){
				members.add(EntityToDto.memberEntityToDto(member));
			}
			
			return members;			
		}
		else{
			throw new MemberCriteriaNullException("les critères sélectionnés ne permettent pas de récupérer des membres!");
			
		}
	}

	@Override
	public MemberDto retrieveMember(int id) {
		
		return EntityToDto.memberEntityToDto(memberDao.getMemberById(id));
	}

	@Override
	public void updateMember(MemberDto memberDto) {
		
		memberDao.update(DtoToEntity.memberDtoToEntity(memberDto));

	}

	@Override
	public void addMember(MemberDto memberDto) {
		
		memberDao.create(DtoToEntity.memberDtoToEntity(memberDto));
		
	}

	@Override
	public void deleteMember(int memberId) {
		
		memberDao.delete(memberDao.getMemberById(memberId));
		
	}

}
