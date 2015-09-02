/**
 * Copyright 2015 DuraSpace, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.auth.webac;

import static org.fcrepo.auth.common.FedoraAuthorizationDelegate.FEDORA_USER_PRINCIPAL;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modeshape.jcr.api.Session;

/**
 * Unit test for the WebAC Authorization Delegate.
 *
 * @author Peter Eichman
 * @since Aug 24, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class WebACAuthorizationDelegateTest {

    private WebACAuthorizationDelegate webacAD;

    @Mock
    private Session mockSession;

    @Mock
    private Principal mockPrincipal;

    private static final String USER = "fakeUser";
    private static final String PATH = "/fake/path";

    @Before
    public void setUp() {
        when(mockSession.getAttribute(FEDORA_USER_PRINCIPAL)).thenReturn(mockPrincipal);
        when(mockPrincipal.getName()).thenReturn(USER);
        webacAD = new WebACAuthorizationDelegate();
    }


    @Test
    public void test() {
        assertFalse(webacAD.rolesHavePermission(mockSession, PATH, getFakeActions(), getFakeRoles()));
    }

    private static String[] getFakeActions() {
        final String[] fakeActions =  new String[2];
        fakeActions[0] = "fakeAction1";
        fakeActions[1] = "fakeAction2";
        return fakeActions;
    }

    private static Set<String> getFakeRoles() {
        final Set<String> fakeRoles = new HashSet<>();
        fakeRoles.add("fakeRole1");
        fakeRoles.add("fakeRole2");
        return fakeRoles;
    }
}
