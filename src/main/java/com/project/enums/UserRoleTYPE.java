package com.project.enums;

import java.util.Arrays;
import java.util.List;

public enum UserRoleTYPE {
	USER(2L, "USER",
			Arrays.asList(UserPermissionTYPE.READ_SELF, UserPermissionTYPE.WRITE_SELF,
					UserPermissionTYPE.UPDDATE_SELF)),
	DIRECTOR(3L, "DIRECTOR",
			Arrays.asList(UserPermissionTYPE.READ_ALL, UserPermissionTYPE.WRITE_SELF, UserPermissionTYPE.UPDDATE_ALL)),
	ADMIN(1L, "ADMIN", Arrays.asList(UserPermissionTYPE.READ_ALL, UserPermissionTYPE.WRITE_ALL,
			UserPermissionTYPE.UPDDATE_ALL, UserPermissionTYPE.DELETE));

	private final Long id;
	private final String name;
	private final List<UserPermissionTYPE> permissions;

	private UserRoleTYPE(Long id, String type, List<UserPermissionTYPE> permissions) {
		this.name = type;
		this.id = id;
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public List<UserPermissionTYPE> getPermissions() {
		return permissions;
	}

}
