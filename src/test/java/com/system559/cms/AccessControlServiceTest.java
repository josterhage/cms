package com.system559.cms;

import com.system559.cms.security.data.AccessControlEntry;
import com.system559.cms.security.data.Permission;
import com.system559.cms.security.repository.AccessControlEntryRepository;
import com.system559.cms.security.service.AccessControlService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.EnumSet;
import java.util.Optional;

@SpringBootTest
public class AccessControlServiceTest {
    @Before
    public void setUp() {
        repository = Mockito.mock(AccessControlEntryRepository.class);
        accessControlService = new AccessControlService(repository);

        AccessControlEntry testEntry = new AccessControlEntry("1");
        testEntry.getGrantedPermissions().put("2", EnumSet.of(Permission.CREATE,Permission.READ,Permission.WRITE));

        when(repository.findById("1")).thenReturn(Optional.of(testEntry));
    }

    private AccessControlService accessControlService;

    private AccessControlEntryRepository repository;

    @Test
    public void userHasPermissions() {
        String userId = "1";
        String objectId = "2";

        assertThat(accessControlService.hasPermission(userId,objectId,Permission.CREATE)).isTrue();
        assertThat(accessControlService.hasPermission(userId,objectId,Permission.READ)).isTrue();
        assertThat(accessControlService.hasPermission(userId,objectId,Permission.WRITE)).isTrue();
    }
}
