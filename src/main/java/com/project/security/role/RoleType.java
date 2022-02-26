package com.project.security.role;

import java.util.Arrays;
import java.util.List;

public enum RoleType {
	USER(2L, "USER",
			Arrays.asList(RolePermissionType.READ_SELF, RolePermissionType.WRITE_SELF,
					RolePermissionType.UPDDATE_SELF)),
	DIRECTOR(3L, "DIRECTOR",
			Arrays.asList(RolePermissionType.READ_ALL, RolePermissionType.WRITE_SELF, RolePermissionType.UPDDATE_ALL)),
	ADMIN(1L, "ADMIN", Arrays.asList(RolePermissionType.READ_ALL, RolePermissionType.WRITE_ALL,
			RolePermissionType.UPDDATE_ALL, RolePermissionType.DELETE));

	private final Long id;
	private final String name;
	private final List<RolePermissionType> permissions;

	private RoleType(Long id, String type, List<RolePermissionType> permissions) {
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

	public List<RolePermissionType> getPermissions() {
		return permissions;
	}

}
