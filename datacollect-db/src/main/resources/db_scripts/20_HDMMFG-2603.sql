/* -- DC_VIEW_ACTION -- */
UPDATE 
	DCOLLECTDB.DC_VIEW_ACTION 
SET 
	SHORT_NAME='change_status',FRIENDLY_NAME='Change Status' 
WHERE 
	ID=(
		SELECT va.ID
		FROM DCOLLECTDB.DC_VIEW_ACTION va JOIN DCOLLECTDB.DC_VIEW v ON va.DC_VIEW_ID=v.ID
		WHERE va.SHORT_NAME='delete' AND v.NAME='dealers'
	)