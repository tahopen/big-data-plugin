/*!
* Copyright 2010 - 2020 Hitachi Vantara.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/
package org.pentaho.s3n.vfs;

import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.provider.FileNameParser;
import org.apache.commons.vfs2.provider.VfsComponentContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


/**
 * Unit tests for S3FileNameParser
 */
public class S3NFileNameParserTest {

  FileNameParser parser;

  @Before
  public void setUp() throws Exception {
    parser = S3NFileNameParser.getInstance();
  }

  @Test
  public void testParseUri() throws Exception {
    VfsComponentContext context = mock( VfsComponentContext.class );
    FileName fileName = mock( FileName.class );
    String uri = "s3n://bucket/file";
    FileName noBaseFile = parser.parseUri( context, null, uri );
    assertNotNull( noBaseFile );
    assertEquals( "bucket", ( (S3NFileName) noBaseFile ).getBucketId() );
    FileName withBaseFile = parser.parseUri( context, fileName, uri );
    assertNotNull( withBaseFile );
    assertEquals( "bucket", ( (S3NFileName) withBaseFile ).getBucketId() );

    // assumption is that the whole URL is valid until it comes time to resolve to S3 objects
    uri = "s3n://s3n/bucket/file";
    withBaseFile = parser.parseUri( context, fileName, uri );
    assertEquals( "s3n", ( (S3NFileName)withBaseFile ).getBucketId() );

    //with credentials
    uri = "s3n://ThiSiSA+PossibleAcce/ssK3y:PossiblES3cre+K3y@s3n/bucket/file";
    withBaseFile = parser.parseUri( context, fileName, uri );
    assertEquals( "ThiSiSA+PossibleAcce/ssK3y:PossiblES3cre+K3y@s3n", ( (S3NFileName)withBaseFile ).getBucketId() );
  }
}
