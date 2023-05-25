package com.system559.cms;

import com.system559.cms.security.data.AccessControlList;
import com.system559.cms.security.data.Permission;
import com.system559.cms.security.repository.AccessControlListRepository;
import com.system559.cms.security.authorization.AccessControlService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class AccessControlServiceTest {
    @Before
    public void setUp() {
        repository = Mockito.mock(AccessControlListRepository.class);
        accessControlService = new AccessControlService(repository);

        AccessControlList testEntry =
                createTestEntry("1",
                        Map.of("2", EnumSet.of(Permission.CREATE, Permission.READ, Permission.WRITE),
                                "3",EnumSet.noneOf(Permission.class)));

        when(repository.findById("1")).thenReturn(Optional.of(testEntry));
    }

    private AccessControlService accessControlService;

    private AccessControlListRepository repository;

    private AccessControlList createTestEntry(String objectId, Map<String, EnumSet<Permission>> grantedPermissions) {
        AccessControlList newAccessControlList = new AccessControlList();
        newAccessControlList.setObjectId(objectId);
        newAccessControlList.setGrantedPermissions(grantedPermissions);
        return newAccessControlList;
    }

    @Test
    public void userHasPermissions() {
        String objectId = "1";
        String userId = "2";

        assertThat(accessControlService.hasPermission(objectId, userId, Permission.CREATE)).isTrue();
        assertThat(accessControlService.hasPermission(objectId, userId, Permission.READ)).isTrue();
        assertThat(accessControlService.hasPermission(objectId, userId, Permission.WRITE)).isTrue();
    }

    @Test
    public void userDoesNotHavePermissions() {
        String objectId="1";
        String userId = "3";

        assertThat(accessControlService.hasPermission(objectId,userId,Permission.CREATE)).isFalse();
    }

    @Test
    public void userNotOnACL() {
        String objectId="1";
        String userId="4";

        assertThat(accessControlService.hasPermission(objectId,userId,Permission.CREATE)).isFalse();
    }
}
