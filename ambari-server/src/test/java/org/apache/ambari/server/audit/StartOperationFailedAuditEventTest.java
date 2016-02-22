/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.server.audit;

import org.joda.time.DateTime;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StartOperationFailedAuditEventTest {

  @Test
  public void testAuditMessage() throws Exception {
    // Given
    String testUserName = "USER1";
    String testRemoteIp = "127.0.0.1";
    String testRequestDetails = "{ \"key\": \"value\"}";
    String testReason = "Parse exception";


    StartOperationFailedAuditEvent evnt = StartOperationFailedAuditEvent.builder()
      .withTimestamp(DateTime.now())
      .withRemoteIp(testRemoteIp)
      .withUserName(testUserName)
      .withOperation(testRequestDetails)
      .withReason(testReason)
      .build();

    // When
    String actualAuditMessage = evnt.getAuditMessage();

    // Then
    String expectedAuditMessage = String.format("User(%s), RemoteIp(%s), Operation(%s), Status(Request failed to be processed !), Reason(%s)", testUserName, testRemoteIp, testRequestDetails, testReason);

    assertThat(actualAuditMessage, equalTo(expectedAuditMessage));
  }

  @Test
  public void testTimestamp() throws Exception {
    // Given
    DateTime testTimestamp = DateTime.now();
    StartOperationFailedAuditEvent evnt = StartOperationFailedAuditEvent.builder()
      .withTimestamp(testTimestamp)
      .build();

    // When
    DateTime actualTimestamp = evnt.getTimestamp();

    // Then
    assertThat(actualTimestamp, equalTo(testTimestamp));

  }

  @Test
  public void testEquals() throws Exception {
    EqualsVerifier.forClass(StartOperationFailedAuditEvent.class)
      .verify();
  }
}