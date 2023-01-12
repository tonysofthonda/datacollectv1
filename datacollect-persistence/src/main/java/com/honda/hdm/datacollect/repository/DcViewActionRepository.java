package com.honda.hdm.datacollect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.honda.hdm.datacollect.model.entity.DcViewAction;
import com.honda.hdm.datacollect.repository.base.IBaseRepository;

@Repository
public interface DcViewActionRepository extends IBaseRepository<DcViewAction, Long> {
	
	@Query(value="SELECT va.*, var.*, r.*, lgr.*, lg.* \n" + 
			"FROM DCOLLECTDB.DC_VIEW_ACTION va \n" + 
			"JOIN DCOLLECTDB.DC_VACTION_X_ROLE var ON va.ID=var.DC_VIEW_ACTION_ID \n" + 
			"JOIN DCOLLECTDB.DC_ROLE r ON var.DC_ROLE_ID=r.ID \n" + 
			"JOIN DCOLLECTDB.DC_LDAPG_X_ROLE lgr ON r.ID=lgr.DC_ROLE_ID \n" + 
			"JOIN DCOLLECTDB.DC_LDAP_GROUP lg ON lgr.DC_LDAP_GROUP_ID=lg.ID \n" +
			"WHERE lg.ldap_id IN :ldapGroups", nativeQuery=true)
	public List<DcViewAction> findAllByLdapGroups(@Param("ldapGroups") List<String> ldapGroup);
	
	@Query(value="SELECT va.*, var.*, r.*, lgr.*, lg.* \n" + 
			"FROM DCOLLECTDB.DC_VIEW_ACTION va \n" + 
			"JOIN DCOLLECTDB.DC_VACTION_X_ROLE var ON va.ID=var.DC_VIEW_ACTION_ID \n" + 
			"JOIN DCOLLECTDB.DC_ROLE r ON var.DC_ROLE_ID=r.ID \n" + 
			"JOIN DCOLLECTDB.DC_LDAPG_X_ROLE lgr ON r.ID=lgr.DC_ROLE_ID \n" + 
			"JOIN DCOLLECTDB.DC_LDAP_GROUP lg ON lgr.DC_LDAP_GROUP_ID=lg.ID \n" +
			"WHERE va.SHORT_NAME = :actionName AND lg.ldap_id IN :ldapGroups", nativeQuery=true)
	public List<DcViewAction> findAllByActionNameAndLdapGroups(@Param("actionName") String actionName, @Param("ldapGroups") List<String> ldapGroup);

}
